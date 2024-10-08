package fr.hb.icicafaitduspringavecboot.controller.security;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.custom_response.JwtResponse;
import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.dto.security.UserLoginDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewUser;
import fr.hb.icicafaitduspringavecboot.security.JwtAuthenticatorService;
import fr.hb.icicafaitduspringavecboot.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SecurityController {

	private final UserService userService;
	private final JwtAuthenticatorService jwtAuthenticatorService;

	@PostMapping("/register")
	@JsonView(JsonViewUser.UserShowView.class)
	public User register(@Valid @RequestBody UserCreationDto userCreationDto){
		return userService.create(userCreationDto);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody UserLoginDto userLoginDto){
		return jwtAuthenticatorService.authenticate(userLoginDto);
	}

}
