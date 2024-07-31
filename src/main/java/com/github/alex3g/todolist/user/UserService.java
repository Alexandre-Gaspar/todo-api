package com.github.alex3g.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    } // DI

    public UserModel createUser(UserDTO dto) {
        // get username from database if exists
        var user = this.repository.findByUsername(dto.username());
        if (user != null) {
            throw new IllegalArgumentException("This username already exists.");
        }

        var newUser = new UserModel(dto);
        this.validateUser(newUser);

        var passwordHash = BCrypt
                .withDefaults()
                .hashToString(12, newUser.getPassword().toCharArray());
        newUser.setPassword(passwordHash);
        return this.repository.save(newUser);
    }

    private void validateUser(UserModel userToCreate) {
        if (userToCreate.getName().equals(null) || userToCreate.getName().equals("")) { // throw IllegalArgumentException if
            throw new IllegalArgumentException("Name field can't be null.");
        }
        if (userToCreate.getPassword().equals(null) || userToCreate.getPassword().equals("")) { // throw IllegalArgumentException if
            throw new IllegalArgumentException("Password field can't be null.");
        }
    }
}
