package com.codenation.centralerrosapi.service.impl;

import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.model.User;
import com.codenation.centralerrosapi.repository.LogRepository;
import com.codenation.centralerrosapi.repository.UserRepository;
import com.codenation.centralerrosapi.repository.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;


    public Optional<Logs> findById(Long id) {
        return this.logRepository.findById(id);
    }

    public Logs save(Logs log) {

        User user = userRepository.findByEmail(request.getRemoteUser());

        log.setUser(user);

        return this.logRepository.save(log);
    }


    public Page<Logs> findPage(LogFilter logFilter, Pageable pageable) {
        return logRepository.filter(logFilter, pageable);

    }

}
