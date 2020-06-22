package com.codenation.centralerrosapi.service.impl;

import com.codenation.centralerrosapi.model.User;
import com.codenation.centralerrosapi.repository.UserRepository;
import com.codenation.centralerrosapi.security.UserDetail;

import com.codenation.centralerrosapi.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    public static UserDetail authenticated() {

        try {
            return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //Retorna o usuario Logado no sistema
        }
        catch (Exception e) {
            return null;
        }
    }



}
