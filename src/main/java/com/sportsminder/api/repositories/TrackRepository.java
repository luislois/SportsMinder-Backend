package com.sportsminder.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sportsminder.api.entities.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {    
}