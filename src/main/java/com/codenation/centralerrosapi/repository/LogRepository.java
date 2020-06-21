package com.codenation.centralerrosapi.repository;

import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.repository.log.LogRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<Logs, Long>, LogRepositoryQuery {

}
