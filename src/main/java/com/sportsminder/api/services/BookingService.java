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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> bookingsToUpdate = bookings.stream()
                .filter(booking -> booking.getStatus().equals("reserved") &&
                        (booking.getDate().isBefore(currentDateTime.toLocalDate()) ||
                                (booking.getDate().isEqual(currentDateTime.toLocalDate()) &&
                                        booking.getStartHour().isBefore(currentDateTime.toLocalTime()))))
                .peek(booking -> booking.setStatus("expired"))
                .collect(Collectors.toList());

        bookingRepository.saveAll(bookingsToUpdate);
    }

    // Update the status of the reserve booking to expired in current day and before
    // current hour, execute every minute
    @Scheduled(cron = "0 0 * * * *")
    public void updateStatusExpiredBookingsLastHour() {
        LocalDate today = LocalDate.now();
        LocalTime currentHour = LocalTime.now();

        List<Booking> bookings = bookingRepository.findByDate(today);
        List<Booking> bookingsToUpdate = bookings.stream()
                .filter(booking -> booking.getStatus().equals("reserved") &&
                        booking.getStartHour().isBefore(currentHour))
                .peek(booking -> booking.setStatus("expired"))
                .collect(Collectors.toList());

        bookingRepository.saveAll(bookingsToUpdate);

    }
}
