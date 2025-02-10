package com.ganesh.NoteAppBackend.Repository;

import com.ganesh.NoteAppBackend.Model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Note, Integer> {

    List<Note> findByUser_username(String username);
}
