package com.example.demo.Auth.controller;

import com.example.demo.Auth.dao.ConfirmationTokenRepository;
import com.example.demo.Auth.dao.UserDao;
import com.example.demo.Auth.model.*;
import com.example.demo.Auth.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Auth.config.JwtTokenUtil;
import com.example.demo.Auth.service.JwtUserDetailsService;

import java.net.URI;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        try {
            userDetailsService.save(user);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        DAOUser newUser = new DAOUser();
        newUser = userDao.findByUsername(user.getUsername());
        if (newUser != null && !newUser.isEnabledUser()) {
            ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(newUser.getUsername());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("bangbang@gmail.com");
            mailMessage.setText("To confirm your account, please click here: " + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
        } else {
            throw new Exception("Users already enabled or not registered");
        }
        //return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);

        URI location = URI.create("test");
        return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");

        //return ResponseEntity.ok(userDetailsService.save(user));
    }

    // Confirmation Token by mail
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) throws Exception {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            DAOUser user = userDao.findByUsername(token.getUser().getUsername());
            user.setEnabledUser(true);
            userDao.save(user);

            URI location = URI.create("test");
            return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");

        } else {
            throw new Exception("Token confirmation problem");
        }

    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}