package com.codenation.centralerrosapi.repository;

import com.codenation.centralerrosapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(Long userId);

    @Override
    void deleteById(Long aLong);

    User findByEmail(String email);

}
