package com.example.demo.Auth.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Auth.dao.UserDao;
import com.example.demo.Auth.model.DAOUser;
import com.example.demo.Auth.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser save(UserDTO user) throws Exception {
        DAOUser verifUser = userDao.findByUsername(user.getUsername());
        if (verifUser == null) {
            DAOUser newUser = new DAOUser();
            newUser.setUsername(user.getUsername());
            newUser.setNom(user.getNom());
            newUser.setPrenom(user.getPrenom());
            newUser.setNum(user.getNum());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

            return userDao.save(newUser);
        } else {
            throw new Exception( user.getUsername() +" Already registered");

        }
    }

}