package com.travelexperts.controllers;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelexperts.model.Booking;
import com.travelexperts.service.BookingService;


@CrossOrigin
@RestController
@RequestMapping("/bookings")
public class BookingController {
	private Logger logger = LoggerFactory.getLogger(CustomerController.class); 
	
	@Autowired
	BookingService bookService;

	@RequestMapping("/all")  
	public ArrayList<Booking> getAll() {
		//logger.info("all was called");
		return bookService.getAll();
	}

	@RequestMapping("{id}")
	public ArrayList<Booking> getBooking(@PathVariable("id") int id) {
		return bookService.getBooking(id);
	}
	
	@PostMapping("/addbooking")
	public void addBooking(Booking book) {
		bookService.insertBooking(book);
	}
	
	@PutMapping("/changebooking")
	public void updateBooking(Booking book) {
		bookService.updateBooking(book);
	}
}
