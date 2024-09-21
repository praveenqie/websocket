package com.example.websocket.repository;

import com.example.websocket.entity.UserNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNoteRepository extends JpaRepository<UserNote, Long> {

    // Find all UserNote entries for a specific user
    List<UserNote> findByUserId(String userId);
}
