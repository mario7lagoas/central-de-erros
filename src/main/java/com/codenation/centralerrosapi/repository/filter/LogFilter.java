package com.codenation.centralerrosapi.repository.filter;


import com.codenation.centralerrosapi.model.enums.Level;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogFilter {

    private String descricao;
    private String origem;
    private Long envents;
    private LocalDateTime dataCriacao;
    private Level[] level;
    //private Level level;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate logsDe;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate logsAte;
    private String orderBy;
    private boolean asc = true;
    private Long user;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Long getEnvents() {
        return envents;
    }

    public void setEnvents(Long envents) {
        this.envents = envents;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Level[] getLevel() {
        return level;
    }

    public void setLevel(Level[] level) {
        this.level = level;
    }
/*
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

 */

    public LocalDate getLogsDe() {
        return logsDe;
    }

    public void setLogsDe(LocalDate logsDe) {
        this.logsDe = logsDe;
    }

    public LocalDate getLogsAte() {
        return logsAte;
    }

    public void setLogsAte(LocalDate logsAte) {
        this.logsAte = logsAte;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
