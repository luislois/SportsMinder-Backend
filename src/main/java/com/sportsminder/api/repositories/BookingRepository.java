package com.sportsminder.api.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sportsminder.api.entities.Booking;
import com.sportsminder.api.entities.Track;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
	
	List<Booking> findByDateAndStartHour(LocalDate date, LocalTime startHour);
	
	List<Booking> findByDate(LocalDate date);
	
	List<Booking> findByidUser(String idUser);
	
	List<Booking> findByTrack(Track track);
	
	List<Booking> findByTrackIdAndDate(Long idTrack, LocalDate date);
	
	@Modifying
	@Query("delete from Booking t where t.id = ?1")
	void deleteById(String bookingId);
}