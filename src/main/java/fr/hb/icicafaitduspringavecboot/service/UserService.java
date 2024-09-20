package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String> {

    private final UserRepository userRepository;

    public User createUser(UserCreationDto userCreationDto){
        return userRepository.saveAndFlush(toEntity(userCreationDto));
    }

    public User toEntity(UserCreationDto userCreationDto){
        User user = new User();
        user.setEmail(userCreationDto.getEmail());
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setPassword(userCreationDto.getPassword());
        user.setRole(userCreationDto.getRole());
        user.setBirthDate(userCreationDto.getBirthDate());
        return user;
    }


    @Override
    public User create(User object) {
        return userRepository.saveAndFlush(object);
    }

    @Override
    public User update(User object, String id) {
        object.setId(id);
        userRepository.flush();
        return object;
    }

    @Override
    public void delete(User object) {
        if(object!=null) userRepository.delete(object);
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}
