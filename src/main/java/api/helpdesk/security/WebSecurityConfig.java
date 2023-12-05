package api.helpdesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import api.helpdesk.services.SecurityService;

@Configuration
public class WebSecurityConfig{

    @Autowired
    private SecurityService securityService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/auth/login", "/auth/register").permitAll()
                    .requestMatchers("api/logs/**").hasAuthority("ADMIN")
                    .requestMatchers("api/**").authenticated()
                    .anyRequest().authenticated()
                ).build();
    }
}
