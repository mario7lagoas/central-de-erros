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
    private String[] levels;
    private Level level;

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String[] getLevels() {
        return levels;
    }

    public Long getEnvents() {
        return envents;
    }

    public LocalDate getLogsDe() {
        return logsDe;
    }


    public LocalDate getLogsAte() {
        return logsAte;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setEnvents(Long envents) {
        this.envents = envents;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setLevels(String[] levels) {
        this.levels = levels;
    }

    public void setLogsDe(LocalDate logsDe) {
        this.logsDe = logsDe;
    }

    public void setLogsAte(LocalDate logsAte) {
        this.logsAte = logsAte;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
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
