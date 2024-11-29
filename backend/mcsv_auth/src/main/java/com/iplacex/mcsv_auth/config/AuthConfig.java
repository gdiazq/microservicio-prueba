package com.iplacex.mcsv_auth.config;


import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class AuthConfig {

    //Configuracion de la cadena de filtros de seguridad
    @Bean
    SecurityFilterChain securityFilterChain(
        final HttpSecurity http) throws Exception {
        
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(STATELESS))
            .oauth2ResourceServer(server -> server
                .jwt(Customizer.withDefaults())
                .authenticationEntryPoint(
                    new BearerTokenAuthenticationEntryPoint())
                .accessDeniedHandler(
                    new BearerTokenAccessDeniedHandler())
            )
            .build();
        }
    

    //Configra el bean de AuthenticationManager
    //Este bean gestiona la autenticacion en la aplicacion
    @Bean
    AuthenticationManager authenticationManager(
        final AuthenticationConfiguration authenticationConfiguration) 
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    //Define un bean de PasswordEncoder que utiliza el algoritmo BCrypt
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}