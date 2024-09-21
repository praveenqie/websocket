package com.example.websocket.service;

import com.example.websocket.dto.NoteMessage;
import com.example.websocket.entity.Note;
import com.example.websocket.entity.UserNote;

import java.util.List;

public interface NoteService {
    Note saveNote(NoteMessage note);


}
