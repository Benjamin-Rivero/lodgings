package fr.hb.icicafaitduspringavecboot.security;

import fr.hb.icicafaitduspringavecboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
				.securityMatcher("/api/**")
				.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth ->
						auth
								.requestMatchers(HttpMethod.POST, "/api/login", "/api/register").permitAll()
								.requestMatchers(
										antMatcher("/v3/api-docs/**"),
										antMatcher("/swagger-ui/**"),
										antMatcher("/api/user/validate"),
										antMatcher(HttpMethod.GET, "/api/lodging"),
										antMatcher(HttpMethod.GET, "/api/lodging/**")
								).permitAll()
								.requestMatchers(
										antMatcher(HttpMethod.POST, "/api/favorite"),
										antMatcher(HttpMethod.PUT, "/api/user/**"),
										antMatcher(HttpMethod.GET, "/api/user/**"),
										antMatcher(HttpMethod.DELETE, "/api/user/**"),
										antMatcher("/api/favorite/**"),
										antMatcher(HttpMethod.POST, "/api/address"),
										antMatcher(HttpMethod.POST, "/api/booking"),
										antMatcher(HttpMethod.POST, "/api/review"),
										antMatcher( "/api/review/**")
								).authenticated()
								.requestMatchers(
										antMatcher(HttpMethod.DELETE, "/api/**"),
										antMatcher(HttpMethod.POST, "/api/lodging"),
										antMatcher(HttpMethod.POST, "/api/lodging/**"),
										antMatcher(HttpMethod.POST, "/api/media"),
										antMatcher(HttpMethod.POST, "/api/room")
								).hasAnyAuthority("ROLE_ADMIN")
								.requestMatchers(antMatcher("/api/**"))
								.hasAnyAuthority("ROLE_ADMIN")
				);
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(userService);
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
