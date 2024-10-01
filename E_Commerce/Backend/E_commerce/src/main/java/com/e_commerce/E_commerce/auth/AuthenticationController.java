package com.e_commerce.E_commerce.auth;


import com.e_commerce.E_commerce.services.UserRequest;
import com.e_commerce.E_commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request){
        return ResponseEntity.ok(service.save(request));
    }

    /*
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }*/
}
