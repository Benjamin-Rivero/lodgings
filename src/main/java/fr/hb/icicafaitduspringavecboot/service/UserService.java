package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.UserDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDto userDto){
        return userRepository.saveAndFlush(toEntity(userDto));
    }

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setVerified(userDto.isVerified());
        user.setBirthDate(userDto.getBirthDate());
        return user;
    }


}
