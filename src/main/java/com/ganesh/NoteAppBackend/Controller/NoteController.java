package com.ganesh.NoteAppBackend.Controller;

import com.ganesh.NoteAppBackend.Model.Note;
import com.ganesh.NoteAppBackend.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/addNote")
    public ResponseEntity<String> addNote(@RequestBody Note note,@RequestParam String username){

        System.out.println("Title : "+note.getTitle());
        System.out.println("Content : "+note.getContent());
        Note saveNote = noteService.saveNote(note,username);
        if (saveNote != null){
            return ResponseEntity.ok("Note saved successfully");
        }
        return ResponseEntity.status(400).body("User not found");
    }
    @GetMapping("/getNote")
    public ResponseEntity<Note> getNote(@RequestParam int id){
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }
    @GetMapping("/getAllNotes")
    public ResponseEntity<List<Note>> getAllNotes(@RequestParam String username){

        List<Note> notes = noteService.getAllNotes(username);
        if (notes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notes);
    }
    @DeleteMapping("deleteNote")
    public ResponseEntity<String> deleteNote(@RequestParam int id){

        boolean isDeleted = noteService.deleteNote(id);
        if (isDeleted){
            return ResponseEntity.ok("Note deleted successfully");
        }
        return ResponseEntity.status(404).body("Note not found");
    }
}
