package com.example.websocket.service;

import com.example.websocket.dto.NoteMessage;
import com.example.websocket.entity.Note;
import com.example.websocket.entity.UserNote;
import com.example.websocket.repository.NoteRepository;
import com.example.websocket.repository.UserNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserNoteRepository userNoteRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserNoteRepository userNoteRepository) {
        this.noteRepository = noteRepository;
        this.userNoteRepository = userNoteRepository;
    }

    @Override
    public Note saveNote(NoteMessage noteMessage) {
        // Save the note to the Note table
        Note savedNote = noteRepository.save(new Note(noteMessage.getText(), noteMessage.getUserId()));

//        for (String userId : noteMessage.getUserIds()) {
//                UserNote userNote = new UserNote(savedNote, userId);
//                userNoteRepository.save(userNote);
//        }
        return savedNote;
    }

}
