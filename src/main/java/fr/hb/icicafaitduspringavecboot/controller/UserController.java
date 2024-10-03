package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.UserUpdateDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewUser;
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

    @PutMapping("/{id}")
    @JsonView(JsonViewUser.UserShowView.class)
    public User update(@Valid @PathVariable String id, @RequestBody UserUpdateDto userUpdateDto){
        return userService.update(userUpdateDto,id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id){
        return userService.delete(id);
    }

    @GetMapping("/validate")
    public String validate(@RequestParam(name = "token") String token){
        if(userService.validate(token)) return "Account activated";
        else return "Problem while activating account";
    }



}
