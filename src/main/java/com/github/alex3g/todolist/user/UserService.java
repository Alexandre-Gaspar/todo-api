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
            throw new InvalidUserException("This username already exists.");
        }

        var newUser = new UserModel(dto);
        this.validateUser(newUser); // validate user data before saving into database

        var hashedPassword = hashPassword(newUser.getPassword());
        newUser.setPassword(hashedPassword);

        return this.repository.save(newUser);
    }

    public String hashPassword(String password) {
        return BCrypt.withDefaults()
                .hashToString(12, password.toCharArray());
    }

    private void validateUser(UserModel userToCreate) {
        if (userToCreate.getUsername() == null || userToCreate.getUsername().isEmpty()) { // throw IllegalArgumentException if
            throw new InvalidUserException("Username field can't be null or empty.");
        }
        if (userToCreate.getName() == null || userToCreate.getName().isEmpty()) { // throw IllegalArgumentException if
            throw new InvalidUserException("Name field can't be null or empty.");
        }
        if (userToCreate.getPassword() == null || userToCreate.getPassword().isEmpty()) { // throw IllegalArgumentException if
            throw new InvalidUserException("Password field can't be null or empty.");
        }
    }
}
