package com.github.alex3g.todolist.user;

import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull(message = "This field cant't be null")
        String name,
        String password,
        String username) {
}
