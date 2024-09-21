package com.example.websocket.repository;

import com.example.websocket.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Find all public notes (where isPrivate is false)
//    List<Note> findByIsPrivateFalse();
//
//    // Find all private notes for a specific user
//    List<Note> findByIsPrivateTrueAndUserId(String userId);
}