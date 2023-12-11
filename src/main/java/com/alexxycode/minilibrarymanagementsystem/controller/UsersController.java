package com.alexxycode.minilibrarymanagementsystem.controller;

import com.alexxycode.minilibrarymanagementsystem.model.Users;
import com.alexxycode.minilibrarymanagementsystem.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    @GetMapping("/getallusers")
    public List<Users> AllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Users userById(@PathVariable int id) {
        return usersService.findById(id).getBody();
    }

    @GetMapping("/users/name/{fullName}")
    public List<Users> userByFullName(@PathVariable String fullName) {
        return usersService.findUserByFullName(fullName);
    }

    @GetMapping("/users/email/{email}")
    public Users userByEmail(@PathVariable String email) {
        return usersService.findUserByEmail(email);
    }

    @PostMapping("/users")
    public Users add(@Valid @RequestBody Users users) {
        return usersService.addUser(users);
    }

    @DeleteMapping("/users{id}")
    public String delete(@PathVariable int id) {
        return usersService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public String update(@Valid @PathVariable int id, @RequestBody Users users) {
        return usersService.updateUser(id, users);
    }

}
