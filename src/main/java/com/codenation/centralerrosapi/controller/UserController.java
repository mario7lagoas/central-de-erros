package com.codenation.centralerrosapi.controller;

import com.codenation.centralerrosapi.dto.UserDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<UserDTO> Save(@Valid @RequestBody User user) {

        User usuario = this.userService.save(user);

        UserDTO userDTO = new UserDTO(usuario);

        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um usuario"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @ApiOperation("Buscar Usuario pelo ID")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {

        User user = this.userRepository.findById(id).get();

        UserDTO userDTO = new UserDTO(user);

        return user != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();

    }

    @GetMapping()
    @ApiOperation("Buscar todos Usuario")
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> users = this.userService.findAll();

        List<UserDTO> userDTOS = users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta o usuario pelo ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {

        this.userRepository.deleteById(id);
    }
}
