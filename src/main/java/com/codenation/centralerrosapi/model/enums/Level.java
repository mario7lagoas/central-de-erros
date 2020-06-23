package com.codenation.centralerrosapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Level {
    ERROR("ERROR"), WARNING("WARNING"), INFO("INFO");

    private String name;

    Level(String erro) {
        name = erro;
    }

    public String toString(){
        return name;
    }

}
