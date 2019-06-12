package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.UserDAO;
import edu.kpi.java.mkr.model.User;
import edu.kpi.java.mkr.security.JWTProvider;
import edu.kpi.java.mkr.security.MyAuthenticationException;
import edu.kpi.java.mkr.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserDAO userDAO;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserDAO userDAO, AuthenticationManager authenticationManager,
                           JWTProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signIn(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtProvider.createToken(userDetails.getUsername());
    }

    @Override
    public String signUp(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userDAO.createUser(user);
        } catch (Exception e) {
            throw new MyAuthenticationException("error occured");
        }

        return signIn(username, password);
    }
}
