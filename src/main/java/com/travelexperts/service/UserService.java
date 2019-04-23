package com.travelexperts.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelexperts.model.User;
import com.travelexperts.security.JwtGen;
import com.travelexperts.service.EmailService;

@Service
public class UserService {
	
	@Autowired
	private JwtGen generator;
	
	@Autowired
	private EmailService eService;
	
	// insert new customer
	public String insertUser(User user) {
		// method variables
		int rowsInserted = 0;
		Integer userId=0;
		
		String token ="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuioplkjhgfdsazxcvbnm1234567890";
		String shuffledToken = shuffle(token);
		
		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL INSERT query.
			String query = "INSERT INTO user " + 
					"(`username`, `password`, `email`, `emailConfirmed`, `token`, `customerId`) " + 
					"VALUES (?,?,?,?,?,?)";		
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        st.setString(1,user.getUsername());
	        st.setString(2,user.getPassword());
	        st.setString(3,user.getEmail());
	        st.setBoolean(4,false);
	        st.setString(5,shuffledToken);
	        st.setInt(6,user.getCustomerId());

	        // execute the query, and get a java resultset
	        rowsInserted = st.executeUpdate();
	        // get ID of the last inserted element 
	        ResultSet rs = st.getGeneratedKeys();
	        if (rs.next()){
	        	userId = rs.getInt(1); //rs.getInt("CustomerId");
	        }
	        
	        //System.out.println("Rows inserted - "+rowsInserted);
	        st.close();
	        
	        // if number of rows inserted equals 1 we will sent email
	        
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		// if entry was inserted to the DataBase we send email
		if(rowsInserted==1) {
			
			try {
				user.setUserId(userId);
				user.setToken(shuffledToken);
				eService.confirmationEmail(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user.getEmail();
	}
	
	// Email confirmation
	public String emailConfirmed(String token, Integer id) {
		
		// Based on id query DB and extract token 
		String dbToken="";

		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL SELECT query.
			String query = "SELECT `token` FROM user WHERE userId = ? ";
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setInt(1,id);
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery();
	
	        // iterate through the java resultset
	        while (rs.next())
	        {    		        	

	        	dbToken = rs.getString("token");

	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		// Compare provided and extracted token
		if(token.equals(dbToken)) {
			// if success 
			try {
				Connection conn = DBConnection.getConnection();
				
				// Create our parameritized SQL INSERT query.
				String query = "UPDATE user " + 
						"SET `emailConfirmed`=?,`token`=? " + 
						"WHERE `userId`=? ";
				
				// Create statement and pass parameters 
				// set token to empty string
				// email confirmed true
		        PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		        st.setBoolean(1,true);
		        st.setString(2,"");
		        st.setInt(3,id);
		
		        // Insert row and get a number of rows inserted
		        int rowsInserted = st.executeUpdate();
		        
		        st.close();
	        }catch (Exception e) {
		        System.err.println("Got an exception! ");
		        System.err.println(e.getMessage());
		    }

			return "successconfirmation";
		} else {
			return "404";
		}
	}
	
	// Method check username and password
	public String loginUser(HttpServletRequest request, HttpServletResponse response) {
		
		// Get variables passed as http request
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");

		
		// Get password from Data Base
		String dbPassword="";
		String token="";
		String result;
		User selectedUser = new User();	
		
		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL SELECT query.
			String query = "SELECT * FROM user WHERE username = ? ";
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setString(1,username);
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery();
	
	        // iterate through the java resultset
	        while (rs.next())
	        {   
	        	// Save password separetaly
	        	dbPassword = rs.getString("password");
	        	// Save all information about selected user
	        	selectedUser.setUserId(rs.getInt("userId"));
	        	selectedUser.setUsername(username);
	        	selectedUser.setPassword(password);
	        	selectedUser.setEmailComfirmed(rs.getBoolean("emailConfirmed"));
	        	selectedUser.setCustomerId(rs.getInt("customerId"));

	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		// Check if email was confirmed
		if(!selectedUser.getEmailComfirmed()) {
			//++ create JSON response which says that email is not confirmed 
			result = "Activate email";
			
		} else {	
			// Check obtained password against provided
			if(dbPassword.equals(password)) {
			
				TokenService tokenService = new TokenService(generator);			
				token = tokenService.generate(selectedUser);
				
				result = token;
				
			}else {
				
				//++ Generate JSON which says that password doesnt match
				System.out.println("Passwords are differrent");
				result = "Login or password is wrong";
			}		
		}
		
		return result;
	}
	
	// Method is used to create token
	private String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
	
} // end of the class
