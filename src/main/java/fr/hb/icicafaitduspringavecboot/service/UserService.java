package fr.hb.icicafaitduspringavecboot.service;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.configuration.PasswordEncoderConfig;
import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.dto.UserUpdateDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.exceptions.EntityNotFoundException;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViews;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String,UserCreationDto, UserUpdateDto> {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig encoder;

    public User create(UserCreationDto userCreationDto){
        return userRepository.saveAndFlush(toEntity(userCreationDto));
    }

    public User toEntity(UserCreationDto userCreationDto){
        User user = new User();
        user.setEmail(userCreationDto.getEmail());
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setPassword(encoder.passwordEncoder().encode(userCreationDto.getPassword()));
        user.setRole(userCreationDto.getRole());
        user.setBirthDate(userCreationDto.getBirthDate());
        return user;
    }




    @Override
    public User update(UserUpdateDto object, String id) {
        User user = findById(id);
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setPassword(object.getPassword());
        user.setId(id);
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public void delete(User object) {
        if(object!=null) userRepository.delete(object);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("id",userId,"User"));
    }



    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}
