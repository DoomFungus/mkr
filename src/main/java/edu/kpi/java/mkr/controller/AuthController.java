package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.AuthRequest;
import edu.kpi.java.mkr.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody AuthRequest authRequest){
        return authService.signIn(authRequest.getUsername(), authRequest.getPassword());
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody AuthRequest authRequest){
        return authService.signUp(authRequest.getUsername(), authRequest.getPassword());
    }
}
