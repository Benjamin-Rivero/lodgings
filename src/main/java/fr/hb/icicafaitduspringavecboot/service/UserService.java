package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.AddressDto;
import fr.hb.icicafaitduspringavecboot.entity.Address;
import fr.hb.icicafaitduspringavecboot.exceptions.AlreadyActiveException;
import fr.hb.icicafaitduspringavecboot.exceptions.ExpiredCodeException;
import fr.hb.icicafaitduspringavecboot.security.PasswordEncoderConfig;
import fr.hb.icicafaitduspringavecboot.dto.UserCreationDto;
import fr.hb.icicafaitduspringavecboot.dto.UserUpdateDto;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.exceptions.EntityNotFoundException;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig encoder;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public User create(UserCreationDto userCreationDto){
        User user = userRepository.save(toEntity(userCreationDto));
        try {
            emailService.sendVerificationEmail(user);
        } catch (MessagingException | UnsupportedEncodingException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public User createInit(UserCreationDto userCreationDto){
        User user = toEntity(userCreationDto);
        user.setActivationToken(null);
        user.setActivationTokenSentAt(null);
        return userRepository.save(user);
    }

    public User toEntity(UserCreationDto userCreationDto){
        User user = new User();
        user.setEmail(userCreationDto.getEmail());
        user.setFirstName(userCreationDto.getFirstName());
        user.setLastName(userCreationDto.getLastName());
        user.setPassword(encoder.passwordEncoder().encode(userCreationDto.getPassword()));
        user.setBirthDate(userCreationDto.getBirthDate());
        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        user.setActivationToken(UUID.randomUUID().toString());
        user.setActivationTokenSentAt(LocalDateTime.now());
        return user;
    }

    public User update(UserUpdateDto object, String id) {
        User user = findById(id);
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setPassword(passwordEncoder.encode(object.getPassword()));
        user.setId(id);
        userRepository.saveAndFlush(user);
        return user;
    }

    public boolean delete(String id) {
        try {
            User user = findById(id);
            user.setPhone(null);
            user.setBirthDate(null);
            user.setPhoto(null);
            user.setLastName(null);
            user.setFirstName(null);
            user.setEmail("Utilisateur supprimé");
            user.setSlug(null);
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("id",userId,"User"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();

        if(!user.isEnabled()) return null;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Collections.singletonList(role);
//        JSONArray roles = new JSONArray(role);
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (r.contains("ADMIN")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        });
        return authorities;
    }

    public User getOneRandom() {
        return userRepository.getOneRandom();
    }

    public Optional<User> findByActivationToken(String activationToken){
        return userRepository.findByActivationToken(activationToken);
    }

	public boolean validate(String activationToken) {
        User user = findByActivationToken(activationToken)
                .orElseThrow(() -> new AlreadyActiveException("Ce code d'activation n'existe pas !"));

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationTokenSentAt().plusMinutes(15))) {
            throw new ExpiredCodeException("La durée du code a expiré");
        }
        user.setActivationToken(null);
        user.setActivationTokenSentAt(null);
        userRepository.saveAndFlush(user);
        return true;
	}

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("email",email,"User"));
    }
}
