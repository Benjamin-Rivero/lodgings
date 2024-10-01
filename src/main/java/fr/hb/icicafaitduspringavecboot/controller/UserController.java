package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewUser;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViews;
import fr.hb.icicafaitduspringavecboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @JsonView(JsonViewUser.UserShowView.class)
    public User show(@PathVariable String id){
        return userService.findById(id);
    }

    @GetMapping("/validate")
    public User validate(@RequestParam(name = "token") String token){
        return userService.validate(token);
    }



}
