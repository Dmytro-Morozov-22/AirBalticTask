package com.ua.vinnytsia.airbaltic_task.controllers;

import com.ua.vinnytsia.airbaltic_task.dto.request.UserDto;
import com.ua.vinnytsia.airbaltic_task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@ModelAttribute UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

}
