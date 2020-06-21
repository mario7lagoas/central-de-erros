package com.codenation.centralerrosapi.controller;

import com.codenation.centralerrosapi.model.User;
import com.codenation.centralerrosapi.repository.UserRepository;
import com.codenation.centralerrosapi.service.advice.ResourceNotFoundException;
import com.codenation.centralerrosapi.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ApiOperation("Criar novo Usuario ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de usuario"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<User> Save(@Valid @RequestBody User user) {

        return new ResponseEntity<User>(this.userService.save(user), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um usuario"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Buscar Usuario pelo ID")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {

        return new ResponseEntity<User>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User")), HttpStatus.OK);

    }

    @GetMapping()
    @ApiOperation("Buscar todos Usuario")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta o usuario pelo ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        this.userRepository.deleteById(id);
    }
}