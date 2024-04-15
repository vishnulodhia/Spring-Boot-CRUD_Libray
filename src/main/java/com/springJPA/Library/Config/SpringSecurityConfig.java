package com.springJPA.Library.Config;

import com.springJPA.Library.repo.Userrepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig {

    private final Userrepo userRepository;
    public SpringSecurityConfig(Userrepo userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return (userName)
                -> {
            com.springJPA.Library.Modal.UserDetail user = userRepository.findByuserName(userName)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles("ADMIN")
                    .build();
        };
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username("admin")
//                    .password("admin")
//                    .roles("ADMIN")
//                    .build();
//        };
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }




}
