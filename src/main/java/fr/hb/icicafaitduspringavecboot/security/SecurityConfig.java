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
								.requestMatchers( "/api/login", "/api/register").permitAll()
								.requestMatchers(
										antMatcher("/v3/api-docs/**"),
										antMatcher("/swagger-ui/**"),
										antMatcher("/api/user/validate"),
										antMatcher(HttpMethod.GET, "/api/lodging"),
										antMatcher(HttpMethod.GET, "/api/lodging/**"),
										antMatcher("/api/user/page")
								).permitAll()
								.requestMatchers(
										antMatcher( "/api/favorite"),
										antMatcher("/api/user/**"),
										antMatcher("/api/favorite/**"),
										antMatcher("/api/address"),
										antMatcher( "/api/booking"),
										antMatcher( "/api/review"),
										antMatcher( "/api/review/**"),
										antMatcher(HttpMethod.POST,"/api/address/upload")
								).authenticated()
								.requestMatchers(
										antMatcher(HttpMethod.DELETE, "/api/**"),
										antMatcher(HttpMethod.POST,"/api/admin/**")
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
