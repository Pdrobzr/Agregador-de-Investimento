package tech.buildrun.Agregador.de.Investimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.Agregador.de.Investimento.dto.RecordLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.RecordUserDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseLoginDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseUserDTO;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody RecordUserDTO createUserDTO) {
        var userCreated = userService.createUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody RecordLoginDTO recordLoginDTO) {
        ResponseLoginDTO responseLoginDTO = userService.loginUser(recordLoginDTO);

        if(responseLoginDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseLoginDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping("/admin")
    public ResponseEntity<List<ResponseUserDTO>> listUsers() {


        List<ResponseUserDTO> users = userService.listUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody RecordUserDTO updateUserDTO) {
        userService.updateUser(id, updateUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable UUID id) {
        var user = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
