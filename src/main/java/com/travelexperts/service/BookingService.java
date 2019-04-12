package com.travelexperts.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.travelexperts.model.Booking;
import com.travelexperts.service.DBConnection;

@Service
public class BookingService {
	
	//get All customers
	public ArrayList<Booking> getAll(){
		
		@SuppressWarnings("unchecked")
		ArrayList<Booking> bookList = new ArrayList();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT BookingId,BookingDate, BookingNo, TravelerCount, TripTypeId, CustFirstName, "
					+ "CustLastName, packages.PackageId, PkgName, PkgBasePrice, bookings.CustomerId "
					+ "From bookings "
					+ "LEFT JOIN customers on bookings.CustomerId = customers.CustomerId "
					+ "LEFT JOIN packages on bookings.PackageId = packages.PackageId";
			
	        Statement st = conn.createStatement();
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery(query);
	
	        // iterate through the java resultset
	        while (rs.next())
	        {
	        	Booking booking = new Booking();
	        	
                booking.setBookingId(rs.getInt("BookingId"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setBookingNo(rs.getString("BookingNo"));
                booking.setTravelerCount(rs.getInt("TravelerCount"));
                booking.setTripType(rs.getString("TripTypeId"));
	        	booking.setCustFirstName(rs.getString("CustFirstName"));
                booking.setCustLastName(rs.getString("CustLastName"));
                booking.setCustomerId(rs.getInt("CustomerId"));
                booking.setPackageId(rs.getInt("PackageId"));
	            booking.setPkgName(rs.getString("PkgName"));
	            booking.setPkgBasePrice(rs.getDouble("PkgBasePrice"));
	
	            // add created instance to the list
	            bookList.add(booking);
	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return bookList;
	}
	
	// get specific customer based on id
	public ArrayList<Booking> getBooking(int CustomerId) {
		
		@SuppressWarnings("unchecked")
		ArrayList<Booking> bookList = new ArrayList();
		Booking selectedBook = new Booking();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL SELECT query.
			String query = "SELECT BookingId,BookingDate, BookingNo, TravelerCount, TripTypeId, CustFirstName, "
					+ "CustLastName, packages.PackageId, PkgName, PkgBasePrice, bookings.CustomerId "
					+ "From bookings "
					+ "LEFT JOIN customers on bookings.CustomerId = customers.CustomerId "
					+ "LEFT JOIN packages on bookings.PackageId = packages.PackageId "
					+ "WHERE bookings.CustomerId = ?";
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setInt(1,CustomerId);
	
	        // execute the query, and get a java resultset
	        ResultSet rs = st.executeQuery();
	
	        // iterate through the java resultset
	        while (rs.next())
	        {    		        	
                selectedBook.setBookingId(rs.getInt("BookingId"));
                selectedBook.setBookingDate(rs.getDate("BookingDate"));
                selectedBook.setBookingNo(rs.getString("BookingNo"));
                selectedBook.setTravelerCount(rs.getInt("TravelerCount"));
                selectedBook.setTripType(rs.getString("TripTypeId"));
                selectedBook.setCustomerId(rs.getInt("CustomerId"));
	        	selectedBook.setCustFirstName(rs.getString("CustFirstName"));
                selectedBook.setCustLastName(rs.getString("CustLastName"));
                selectedBook.setPackageId(rs.getInt("PackageId"));
	            selectedBook.setPkgName(rs.getString("PkgName"));
	            selectedBook.setPkgBasePrice(rs.getDouble("PkgBasePrice"));
	            
	            // add selected booking to the list
	            bookList.add(selectedBook);
	        }
	        st.close();
        }catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		
		return bookList;
	}
	
	// insert new customer
	public int insertBooking(Booking book) {

		int rowsInserted = 0;

		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL INSERT query.
			String query = "INSERT INTO Bookings " + 
					"(`BookingDate`, `BookingNo`, `TravelerCount`, `CustomerId`, `TripTypeId`, `PackageId`) " + 
					"VALUES (?,?,?,?,?,?) ";
			
			// Create BookingNo
			String bookingNo = "KL";
			Random random = new Random();
			String bookingDigits = String.format("%04d", random.nextInt(10000));		
			bookingNo = bookingNo.concat(bookingDigits);
			
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, book.getBookingDate());
            st.setString(2, bookingNo);
	        st.setInt(3,book.getTravelerCount());
	        st.setInt(4,book.getCustomerId());
	        st.setString(5, "L");
	        st.setInt(6,book.getPackageId());
		
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
	public int updateBooking(Booking book) {
		
		int rowsUpdated = 0;

		try {
			Connection conn = DBConnection.getConnection();
			
			// Create our parameritized SQL INSERT query.
			String query = "UPDATE bookings " + 
					"SET `BookingDate`=?, `BookingNo`=?, `TravelerCount`=?, "+
					"`CustomerId`= ?, `TripTypeId` = ?, `PackageId` = ? " + 
					"WHERE `BookingId`=? ";
					
			// Create statement and pass parameters 
	        PreparedStatement st = conn.prepareStatement(query);     
	        
	        st.setDate(1,book.getBookingDate());
	        st.setString(2, book.getBookingNo());
	        st.setInt(3,book.getTravelerCount());
	        st.setInt(4,book.getCustomerId());
	        st.setString(5, "L");
	        st.setInt(6,book.getPackageId());	        
	        st.setInt(7,book.getBookingId());
	       	
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
