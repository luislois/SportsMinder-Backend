package com.sportsminder.api.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportsminder.api.entities.Track;
import com.sportsminder.api.repositories.TrackRepository;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;
    
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Track getTrackById(Long id) {
        return trackRepository.findById(id).orElse(null);
    }

    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }
    
    public Track updateTrack(Long id, Track updatedTrack) {
        Track existingTrack = trackRepository.findById(id).orElse(null);
        if (existingTrack == null) {
            return null;
        }
        
        existingTrack.setName(updatedTrack.getName());
        existingTrack.setSport(updatedTrack.getSport());
        existingTrack.setType(updatedTrack.getType());
        
        return trackRepository.save(existingTrack);
    }
}