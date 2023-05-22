package com.ua.vinnytsia.airbaltic_task.controllers;

import com.ua.vinnytsia.airbaltic_task.dto.request.UserDto;
import com.ua.vinnytsia.airbaltic_task.models.User;
import com.ua.vinnytsia.airbaltic_task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@ModelAttribute UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.allUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @ModelAttribute UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
