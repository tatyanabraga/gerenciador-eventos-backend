package br.com.eventos.gerenciadoreventosbackend.config;

import org.springframework.beans.factory.annotation.Autowired; // Adicione esta importação
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Adicione esta importação

import br.com.eventos.gerenciadoreventosbackend.security.SecurityFilter; // Adicione esta importação

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired // 1. Injeta nosso filtro customizado
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/cadastrar", "/admin/login").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated() // Todas as outras requisições precisam de autenticação
            )
            // 2. Adiciona nosso filtro para ser executado antes do filtro padrão do Spring
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}