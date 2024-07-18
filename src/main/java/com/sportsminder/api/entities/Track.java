package com.sportsminder.api.entities;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="Tracks")
@Getter @Setter
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="track_id")
    private Long id;

    @Column(name="track_name")
    private String name;

    @Column(name="track_sport")
    private String sport;

    @Column(name="track_type")
    private String type;

    @Column(name="track_start_time")
    private String startHour;

    @Column(name="track_end_time")
    private String endHour;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Track(String name, String sport, String type, String startHour, String endHour) {
        this.name = name;
        this.sport = sport;
        this.type = type;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
