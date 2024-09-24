package fr.hb.icicafaitduspringavecboot.controller;

import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.FavoriteRepository;
import fr.hb.icicafaitduspringavecboot.repository.LodgingRepository;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final LodgingRepository lodgingRepository;
    private final FavoriteRepository favoriteRepository;

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createLodging(@RequestBody UserCreationDto userCreationDto){
        return userService.create(userCreationDto);
    }



}
