package com.codenation.centralerrosapi.model;

import com.codenation.centralerrosapi.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "logs")
@EntityListeners(AuditingEntityListener.class)
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @NotBlank
    private String description;

    private String origin;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long qteEnvents;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Logs(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getQteEnvents() {
        return qteEnvents;
    }

    public void setQteEnvents(Long qteEnvents) {
        this.qteEnvents = qteEnvents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Logs)) return false;
        Logs logs = (Logs) o;
        return Objects.equals(id, logs.id) &&
                level == logs.level &&
                Objects.equals(description, logs.description) &&
                Objects.equals(origin, logs.origin) &&
                Objects.equals(createAt, logs.createAt) &&
                Objects.equals(date, logs.date) &&
                Objects.equals(qteEnvents, logs.qteEnvents) &&
                Objects.equals(user, logs.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, description, origin, createAt, date, qteEnvents, user);
    }
}
