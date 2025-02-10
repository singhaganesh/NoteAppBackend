package com.ganesh.NoteAppBackend.Controller;

import com.ganesh.NoteAppBackend.Model.Users;
import com.ganesh.NoteAppBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){

        Optional<Users> user = userService.login(username,password);
        if (user.isPresent()){
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user){
        Users saveUser = userService.saveUser(user);
        return ResponseEntity.ok(saveUser);
    }
}
