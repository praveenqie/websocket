package com.example.websocket.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_notes")
public class UserNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = false)
    private Note note;

    @Column(nullable = true)
    private String userId;


    // Constructors, getters, setters

    public UserNote() {
    }

    public UserNote(Note note, String userId) {
        this.note = note;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
