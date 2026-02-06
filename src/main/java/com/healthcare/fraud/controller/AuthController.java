package com.healthcare.fraud.controller;

import com.healthcare.fraud.config.JwtUtil;
import com.healthcare.fraud.dto.*;
import com.healthcare.fraud.entity.Role;
import com.healthcare.fraud.entity.User;
import com.healthcare.fraud.repository.RoleRepository;
import com.healthcare.fraud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(role));

        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return new LoginResponse(token);
    }
}

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//
//    public AuthController(AuthenticationManager authenticationManager,
//                          JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//
//        String token = jwtUtil.generateToken(request.getUsername());
//        return new LoginResponse(token);
//    }
//
//    @GetMapping("/me")
//    public UserDetails me(@AuthenticationPrincipal UserDetails userDetails) {
//        return userDetails;
//    }
//}
