package com.codenation.centralerrosapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Level {
    ERROR("Erro"), WARNING("Alerta"), INFO("Informação");

    private String type;

    Level(String erro) {
    }
}
