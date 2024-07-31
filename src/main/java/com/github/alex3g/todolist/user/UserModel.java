package com.github.alex3g.todolist.user;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Timestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserModel(UserDTO data) {
        this.name = data.name();
        this.username = data.username();
        this.password = data.password();
        this.createdAt = LocalDateTime.now();
    }
}

