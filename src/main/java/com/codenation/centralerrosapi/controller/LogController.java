package com.codenation.centralerrosapi.controller;

import com.codenation.centralerrosapi.dto.LogDTO;
import com.codenation.centralerrosapi.dto.LogDetailDTO;
import com.codenation.centralerrosapi.model.Logs;
import com.codenation.centralerrosapi.repository.filter.LogFilter;
import com.codenation.centralerrosapi.service.advice.ResourceNotFoundException;
import com.codenation.centralerrosapi.service.impl.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping()
    @ApiOperation("Filtrar Logs.")
    public ResponseEntity<Page<LogDTO>> pesquisar(LogFilter logFilter,
                                                  @PageableDefault(page = 0, size = 3, sort = "createAt", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Logs> listLogs = logService.findPage(logFilter, pageable);

        Page<LogDTO> logDOTS = listLogs.map(log -> new LogDTO(log));

        return new ResponseEntity<>(logDOTS, HttpStatus.OK);

    }

    @ApiOperation(value = "Buscar o Log pelo ID.")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um Log"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<LogDetailDTO> findById(@PathVariable("id") Long id) {

        Logs logs = this.logService.findById(id).get();
        LogDetailDTO logDetailDTO = new LogDetailDTO(logs);

        return logs != null ? ResponseEntity.ok(logDetailDTO) : ResponseEntity.notFound().build();

    }

    @PostMapping
    @ApiOperation("Incliur um novo Log")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Novo Usuario"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<List<LogDTO>> save(@Valid @RequestBody List<Logs> logs) {

        logs.stream().forEach(l -> l.setOrigin(request.getRemoteAddr()));

        List<Logs> logReturn = logService.save(logs);
        List<LogDTO> logDTOS = new ArrayList<>();

        for (Logs logs1 : logReturn) {
            if (logs1 != null) {
                logDTOS.add(new LogDTO(logs1));
            }
        }

        return new ResponseEntity<List<LogDTO>>(logDTOS, HttpStatus.CREATED);
      
    }

}
