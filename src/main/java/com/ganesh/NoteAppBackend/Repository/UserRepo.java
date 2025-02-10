package com.ganesh.NoteAppBackend.Repository;

import com.ganesh.NoteAppBackend.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);

}
