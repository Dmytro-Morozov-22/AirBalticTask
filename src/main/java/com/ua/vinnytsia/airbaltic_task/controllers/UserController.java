package com.ua.vinnytsia.airbaltic_task.controllers;

import com.ua.vinnytsia.airbaltic_task.dto.request.UserDto;
import com.ua.vinnytsia.airbaltic_task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
