package com.github.alex3g.todolist.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    } // DI

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody @Valid UserDTO dto) {
        var newUser = this.userService.createUser(dto);
        return ResponseEntity.ok(newUser.getId());
    }
}
