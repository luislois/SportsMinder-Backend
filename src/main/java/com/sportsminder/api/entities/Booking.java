package com.sportsminder.api.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "Bookings")
@Getter
@Setter
public class Booking {

    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "track_id", referencedColumnName = "track_id")
    private Track track;

    @Column(name = "user_id", nullable = false)
    private String idUser;

    @Column(name = "booking_status", nullable = false)
    private String status = "reserved";

    @Column(name = "booking_date")
    private LocalDate date;

    @Column(name = "booking_start_time")
    private LocalTime startHour;

    @Column(name = "booking_end_time")
    private LocalTime endHour;

    public Booking(String bookingId, Track track, String idUser, LocalDate date, LocalTime startHour, LocalTime endHour,
            String status) {
        this.bookingId = bookingId;
        this.track = track;
        this.idUser = idUser;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.status = status;
    }
}
