package com.polideportivo.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sportsminder.api.repositories.BookingRepository;
import com.sportsminder.api.services.BookingService;

import static org.mockito.Mockito.*;

@SpringBootTest
class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testDeleteBooking() {
        // Arrange
        String bookingId = "2-2024-03-30-03:00";

        // Act
        bookingService.deleteBooking(bookingId);

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}

