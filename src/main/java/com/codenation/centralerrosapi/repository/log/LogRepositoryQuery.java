package com.codenation.centralerrosapi.repository.log;

import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.repository.filter.LogFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LogRepositoryQuery {

    Page<Logs> filter(LogFilter logFilter, Pageable pageable);

}
