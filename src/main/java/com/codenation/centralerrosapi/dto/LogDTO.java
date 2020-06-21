package com.codenation.centralerrosapi.dto;

import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class LogDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String origin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;
    private Long qteEnvents;


    public LogDTO(Logs log){
        this.id = log.getId();
        this.level = log.getLevel();
        this.origin = log.getOrigin();
        this.createAt = log.getCreateAt();
        this.qteEnvents = log.getQteEnvents();
    }

}
