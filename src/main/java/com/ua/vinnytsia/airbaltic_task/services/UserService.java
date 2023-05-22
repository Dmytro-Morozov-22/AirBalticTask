package com.ua.vinnytsia.airbaltic_task.services;

import com.ua.vinnytsia.airbaltic_task.dto.request.UserDto;
import com.ua.vinnytsia.airbaltic_task.dto.response.UserInfoDto;
import com.ua.vinnytsia.airbaltic_task.models.User;
import com.ua.vinnytsia.airbaltic_task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDto saveUser(UserDto userDto) {
        if(isEmailUnique(userDto.getEmail())){
            User savedUser = userRepository.save(new User(userDto.getFirstName(),
                userDto.getLastName(),userDto.getEmail()));
            return new UserInfoDto(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
        }
        throw new ResponseStatusException(420, "The " + userDto.getEmail() + " is already in the system", null);
    }

    private boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public User getUser(Long userId) {
        return userRepository.getUserById(userId)
            .orElseThrow(() -> new ResponseStatusException(404, "User with id " + userId + " not found", null));
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, UserDto userDto) {
        User user = userRepository.getUserById(userId)
            .orElseThrow(() -> new ResponseStatusException(404, "User with id " + userId + " not found", null));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    public String deleteUser(Long userId){
        userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(404, "User with id " + userId + " not found", null));
        userRepository.deleteById(userId);
        return "User successfully deleted!";
    }


}
