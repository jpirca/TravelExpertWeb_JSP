package com.travelexperts.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.travelexperts.model.Customer;
import com.travelexperts.service.DBConnection;

@Service
public class CustomerService {
	
	//get All customers
	public ArrayList<Customer> getAll(){
		
		@SuppressWarnings("unchecked")
		ArrayList<Customer> custList = new ArrayList();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM customers";
			
	        Statement st = conn.createStatement();
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery(query);
	
	        // iterate through the java resultset
	        while (rs.next())
	        {
	        	Customer customer = new Customer();
	        	
	        	customer.setCustomerId(rs.getInt("CustomerId"));
	        	customer.setCustFirstName(rs.getString("CustFirstName"));
	            customer.setCustLastName(rs.getString("CustLastName"));
	            customer.setCustAddress(rs.getString("CustAddress"));
	            customer.setCustCity(rs.getString("CustCity"));
	            customer.setCustProv(rs.getString("CustProv"));
	            customer.setCustPostal(rs.getString("CustPostal"));
	            customer.setCustCountry(rs.getString("CustCountry"));
	            customer.setCustHomePhone(rs.getString("CustHomePhone"));
	            customer.setCustBusPhone(rs.getString("CustBusPhone"));
	            customer.setCustEmail(rs.getString("CustEmail"));
	            customer.setAgentId(rs.getInt("AgentId"));
	
	            // add created instance to the list
	            custList.add(customer);
	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return custList;
	}
	
	// get specific customer based on id
	public Customer getCustomer(String CustId) {
		Customer selectedCust = new Customer();
		int customerId = Integer.valueOf(CustId);
		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL SELECT query.
			String query = "SELECT * FROM customers WHERE CustomerId = ? ";
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setInt(1,customerId);
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery();
	
	        // iterate through the java resultset
	        while (rs.next())
	        {    		        	
	        	selectedCust.setCustomerId(rs.getInt("CustomerId"));
	        	selectedCust.setCustFirstName(rs.getString("CustFirstName"));
	        	selectedCust.setCustLastName(rs.getString("CustLastName"));
	        	selectedCust.setCustAddress(rs.getString("CustAddress"));
	        	selectedCust.setCustCity(rs.getString("CustCity"));
	        	selectedCust.setCustProv(rs.getString("CustProv"));
	        	selectedCust.setCustPostal(rs.getString("CustPostal"));
	        	selectedCust.setCustCountry(rs.getString("CustCountry"));
	        	selectedCust.setCustHomePhone(rs.getString("CustHomePhone"));
	        	selectedCust.setCustBusPhone(rs.getString("CustBusPhone"));
	        	selectedCust.setCustEmail(rs.getString("CustEmail"));
	        	selectedCust.setAgentId(rs.getInt("AgentId"));
	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return selectedCust;
	}
	
	// insert new customer
	public int insertCustomer(Customer cust) {

		int rowsInserted = 0;

		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL INSERT query.
			String query = "INSERT INTO customers " + 
					"(`CustFirstName`, `CustLastName`, `CustAddress`, `CustCity`, `CustProv`, `CustPostal`, "
					+ "`CustCountry`, `CustHomePhone`, `CustBusPhone`, `CustEmail`) " + 
					"VALUES (?,?,?,?,?,?,?,?,?,?) ";
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setString(1,cust.getCustFirstName());
	        st.setString(2,cust.getCustLastName());
	        st.setString(3,cust.getCustAddress());
	        st.setString(4,cust.getCustCity());
	        st.setString(5,cust.getCustProv());
	        st.setString(6,cust.getCustPostal());
	        st.setString(7,cust.getCustCountry());
	        st.setString(8,cust.getCustBusPhone());
	        st.setString(9,cust.getCustBusPhone());
	        st.setString(10,cust.getCustEmail());
	
	        // execute the query, and get a java resultset
	        //ResultSet rs = st.executeQuery();
	        rowsInserted = st.executeUpdate();
	        //System.out.println("Rows inserted - "+rowsInserted);

	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return rowsInserted;
	}

	// insert new customer
	public int updateCustomer(Customer cust) {
		
		int rowsUpdated = 0;

		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL INSERT query.
			String query = "UPDATE customers " + 
					"SET `CustFirstName`=?, `CustLastName`=?, `CustAddress`=?, `CustCity`=?, `CustProv`=?, "
					+ "`CustPostal`=?,`CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, `CustEmail`=? " + 
					"WHERE `CustomerId`=? ";
			
			//System.out.println("First name - "+cust.getCustFirstName());
			//System.out.println("Last name - "+cust.getCustLastName());
			//System.out.println("Address - "+cust.getCustAddress());
			//System.out.println("Cisty - "+cust.getCustCity());
			//System.out.println("Province - "+cust.getCustProv());
			//System.out.println("Postal - "+cust.getCustPostal());
			//System.out.println("Coutry - "+cust.getCustCountry());
			//System.out.println("Home phone - "+cust.getCustHomePhone());
			//System.out.println("Bus phone - "+cust.getCustBusPhone());
			//System.out.println("Email - "+cust.getCustEmail());
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setString(1,cust.getCustFirstName());
	        st.setString(2,cust.getCustLastName());
	        st.setString(3,cust.getCustAddress());
	        st.setString(4,cust.getCustCity());
	        st.setString(5,cust.getCustProv());
	        st.setString(6,cust.getCustPostal());
	        st.setString(7,cust.getCustCountry());
	        st.setString(8,cust.getCustBusPhone());
	        st.setString(9,cust.getCustBusPhone());
	        st.setString(10,cust.getCustEmail());
	        // Set condition
	        st.setInt(11,cust.getCustomerId());
	
	        // execute the query, and get a java resultset
	        //ResultSet rs = st.executeQuery();
	        rowsUpdated = st.executeUpdate();
	        System.out.println("Rows inserted - "+rowsUpdated);

	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return rowsUpdated;
	}
} // end of the class