package com.sportsminder.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sportsminder.api.entities.Booking;
import com.sportsminder.api.entities.Track;
import com.sportsminder.api.repositories.TrackRepository;
import com.sportsminder.api.services.BookingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private TrackRepository trackRepository;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, bookings.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/track/{trackId}/{startDate}/{endDate}/count")
    public int getAllBookingsFromDateToDate(
            @PathVariable long trackId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return bookingService.getCountByBookingsByTrackAndDateToDate(trackId, startDate, endDate);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Booking>> getBookingByidUser(@PathVariable String idUser) {
        List<Booking> bookings = bookingService.getBookingsByidUser(idUser);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/track/{idTrack}/date/{date}")
    public ResponseEntity<List<Booking>> getBookingsByidTrackAndDate(@PathVariable Long idTrack,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Booking> bookings = bookingService.getBookingsByTrackIdAndDate(idTrack, date);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/track/{idTrack}")
    public ResponseEntity<List<Booking>> getBookingsByTrackId(@PathVariable Long idTrack) {
        Optional<Track> trackOptional = trackRepository.findById(idTrack);
        if (trackOptional.isPresent()) {
            Track track = trackOptional.get();
            List<Booking> bookings = bookingService.getBookingsByTrack(track);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/track/{idTrack}/count")
    public int getCountBookingsByTrackId(@PathVariable Long idTrack) {
        return bookingService.getCountOfBookingsByTrack(idTrack);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.saveBooking(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
