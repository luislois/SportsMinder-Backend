package com.sportsminder.api.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sportsminder.api.entities.Booking;
import com.sportsminder.api.entities.Track;
import com.sportsminder.api.repositories.BookingRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }
    
    public List<Booking> getBookingsByidUser(String idUser) {
        return bookingRepository.findByidUser(idUser);
    }
    
    public List<Booking> getBookingsByTrack(Track track) {
        return bookingRepository.findByTrack(track);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    @Transactional
    public void deleteBooking(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }
    
    public List<Booking> getBookingsByTrackIdAndDate(Long idTrack, LocalDate date) {
        return bookingRepository.findByTrackIdAndDate(idTrack, date);
    }
    
    // Update the status of the reserve booking to expired , execute on start
    @PostConstruct
    public void updateStatusExpiredBookings() {
    	LocalDate currentDate = LocalDate.now();
        LocalTime currentDateTime = LocalTime.now();
        
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> bookingsToUpdate = new ArrayList<>();
        
        for (Booking booking : bookings) {
            LocalDate bookingDate = booking.getDate();
            LocalTime bookingStartHour = booking.getStartHour();
            if (bookingDate.isBefore(currentDate) || bookingDate.isEqual(currentDate) && bookingStartHour.isBefore(currentDateTime)) {
                if ("reserved".equals(booking.getStatus())) {
                    booking.setStatus("expired");
                    bookingsToUpdate.add(booking);
                }
            }
        }
        
        bookingRepository.saveAll(bookingsToUpdate);
    }
    
    // Update the status of the reserve booking to expired in current day and before current hour, execute every minute
    @Scheduled(cron = "0 0 * * * *")
    public void updateStatusExpiredBookingsLastHour() {
    	LocalDate today = LocalDate.now();
    	LocalTime currentHour = LocalTime.now();
    	
    	List<Booking> bookings = bookingRepository.findByDate(today);
    	
        for (Booking booking : bookings) {
        	String bookingStatus = booking.getStatus();
        	LocalTime bookingStartHour = booking.getStartHour();
        	if (bookingStartHour.isBefore(currentHour) && bookingStatus.equals("reserved")) {
                booking.setStatus("expired");
                bookingRepository.save(booking);
            }
        }
    	 
    }
}
