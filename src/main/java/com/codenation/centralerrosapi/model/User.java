package com.codenation.centralerrosapi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @ApiModelProperty(value = "Código do Usuario", notes = "O banco de Dados Gera o Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo.")
    @NotBlank(message = "Nome não pode ser em branco")
    @Size(min = 3 ,max = 50)
    @ApiModelProperty(value = "Nome o Usuario", notes = "Nome do Usuario")
    private String nome;

    @Email(message = "Email deve ser Valido")
    @Column(unique = true)
    @ApiModelProperty(value = "E-mail do Usuario", notes = "E-mail do Usuario")
    private String email;

    @NotNull(message = "Senha nao pode ser Nulo.")
    @NotBlank(message = "Senha nao pode ser em Branco")
    @ApiModelProperty(value = "Senha do Usuario")
    private String password;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "Data e Hora da cadastro", notes = "O sistemas gera a Data e hora de inclusao do registro")
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Logs> logs;

     public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(nome, user.nome) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(logs, user.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, password, createdAt, logs);
    }
}
