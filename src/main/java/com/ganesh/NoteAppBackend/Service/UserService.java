package com.ganesh.NoteAppBackend.Service;

import com.ganesh.NoteAppBackend.Model.UserPrincipal;
import com.ganesh.NoteAppBackend.Model.Users;
import com.ganesh.NoteAppBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public Optional<Users> login(String username, String password) {
            Optional<Users> user = userRepo.findByUsername(username);

            if (user.isPresent() && encoder.matches(password,user.get().getPassword())){
                return user;
            }
            return Optional.empty();

    }

    public Users saveUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUsername(username);
        if (user.isPresent()){
            return new UserPrincipal(user.get());
        }
        throw  new UsernameNotFoundException("user not found");
    }
}
