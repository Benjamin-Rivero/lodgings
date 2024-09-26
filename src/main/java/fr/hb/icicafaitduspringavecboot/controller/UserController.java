package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViews;
import fr.hb.icicafaitduspringavecboot.repository.FavoriteRepository;
import fr.hb.icicafaitduspringavecboot.repository.LodgingRepository;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.UserService;
import jakarta.validation.Valid;
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
    @JsonView(JsonViews.UserListView.class)
    public List<User> getAll(){
        return userService.list();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.UserShowView.class)
    public User show(@PathVariable String id){
        return userService.findById(id);
    }

    @PostMapping
    public User createLodging(@Valid @RequestBody UserCreationDto userCreationDto){
        return userService.create(userCreationDto);
    }



}
