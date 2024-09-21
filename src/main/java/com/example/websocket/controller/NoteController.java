package com.example.websocket.controller;


import com.example.websocket.dto.NoteMessage;
import com.example.websocket.entity.Note;
import com.example.websocket.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/notes")
public class NoteController {
    private final SimpMessagingTemplate messagingTemplate;
    private final NoteService noteService; //

    public NoteController(SimpMessagingTemplate messagingTemplate, NoteService noteService) {
        this.messagingTemplate = messagingTemplate;
        this.noteService = noteService;
    }

    @MessageMapping("/note")
    public ResponseEntity<NoteMessage> createNote(@RequestBody NoteMessage noteMessage) {
        // Store the note in the database
//        System.out.print(noteMessage);
        //Note savedNote = noteService.saveNote(noteMessage);

        if (noteMessage.isForEveryone()) {
            messagingTemplate.convertAndSend("/topic/private/" +  noteMessage.getUserId(), noteMessage);
        } else {
            messagingTemplate.convertAndSend("/topic/public", noteMessage);
            System.out.print(noteMessage.toString());
        }
        return ResponseEntity.ok(noteMessage); // Respond with the saved note
    }

}
