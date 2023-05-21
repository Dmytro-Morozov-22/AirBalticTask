package com.ua.vinnytsia.airbaltic_task.services;


import com.ua.vinnytsia.airbaltic_task.dto.request.UserDto;
import com.ua.vinnytsia.airbaltic_task.dto.response.UserInfoDto;
import com.ua.vinnytsia.airbaltic_task.models.User;
import com.ua.vinnytsia.airbaltic_task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        throw new ResponseStatusException(420, "The" + userDto.getEmail() + "is already in the system", null);
    }

    private boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }


}
