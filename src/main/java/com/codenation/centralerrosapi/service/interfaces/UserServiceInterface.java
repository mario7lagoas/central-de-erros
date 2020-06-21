package com.codenation.centralerrosapi.service.interfaces;

import com.codenation.centralerrosapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    Optional<User> findById(Long userId);

    User save(User user);
    List<User> findAll();

}
