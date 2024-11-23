package com.example.websocket.controller;

import com.example.websocket.dto.NoteMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/notes")
public class NoteController {
    private final SimpMessagingTemplate messagingTemplate;

    public NoteController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
//
//    @MessageMapping("/note")
//    public ResponseEntity<NoteMessage> createNote(@RequestBody NoteMessage noteMessage) {
//        if (noteMessage.isForEveryone()) {
//            // Send to all subscribers on the specific topic
//            messagingTemplate.convertAndSend("/topic/public", noteMessage);
//        } else {
//            noteMessage.getUserIds().forEach(user->
//            {
//                messagingTemplate.convertAndSendToUser(user,"/topic/private",noteMessage);
//            });
//        }
//        return ResponseEntity.ok(noteMessage); // Respond with the note message
//    }
//

    @MessageMapping("/public-note")
    @SendTo("/topic/public")
    public NoteMessage createPublicNote(@RequestBody NoteMessage noteMessage) {
        // Handle the public note message and return it to the public topic
        return noteMessage;
    }

    // Endpoint for private notes
    @MessageMapping("/private-note")
    public ResponseEntity<NoteMessage> createPrivateNote(@RequestBody NoteMessage noteMessage) {
        // Handle the private note message and send it to individual users
        noteMessage.getUserIds().forEach(user -> {
            messagingTemplate.convertAndSendToUser(user, "/topic/private", noteMessage);
        });
        return ResponseEntity.ok(noteMessage); // Respond with the note message
    }
}