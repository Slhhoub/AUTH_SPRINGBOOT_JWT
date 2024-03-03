package com.jwtAuth.jwtAuth.service;

import com.jwtAuth.jwtAuth.model.User;
import com.jwtAuth.jwtAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationReponse register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       String token=jwtService.generateToken(userRepository.save(user));
        return new AuthenticationReponse(token);
    }

    public AuthenticationReponse login(User user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        User getuser = userRepository.findByUsername(user.getUsername()).orElseThrow();
        String token = jwtService.generateToken(getuser);
        return new AuthenticationReponse(token);
    }
}
