package com.example.hw27.Service;

import com.example.hw27.Model.User;
import com.example.hw27.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void register(User user){
        String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");
        authRepository.save(user);
    }

}
