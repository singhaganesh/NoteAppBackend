package com.ganesh.NoteAppBackend.Service;

import com.ganesh.NoteAppBackend.Model.Note;
import com.ganesh.NoteAppBackend.Model.Users;
import com.ganesh.NoteAppBackend.Repository.NotesRepo;
import com.ganesh.NoteAppBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NotesRepo notesRepo;

    @Autowired
    UserRepo userRepo;

    public Note saveNote(Note note,String username) {
        Optional<Users> user = userRepo.findByUsername(username);
        if (user.isPresent()){
            note.setUser(user.get());
            return notesRepo.save(note);
        }
        return null;
    }

    public Optional<Note> getNoteById(int id) {
        Optional<Note> note = notesRepo.findById(id);
        return note;
    }

    public List<Note> getAllNotes(String username) {
        return notesRepo.findByUser_username(username);
    }

    public boolean deleteNote(int id) {

        if (notesRepo.existsById(id)){
            notesRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
