package com.upao.govench.govench.config;

import com.upao.govench.govench.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomUserDetailsService userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // TODO: Permite solicitudes CORS desde otros dominios
                .csrf(AbstractHttpConfigurer::disable) // TODO: Desactiva la protección CSRF, ya que en APIs REST no se usa (se autentica con tokens, no con cookies)

                .authorizeHttpRequests(authorize -> authorize
                        // TODO: Permitir acceso público a las rutas de login, registro y endpoints públicos como Swagger UI
                        .requestMatchers(antMatcher("/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/auth/register/participant")).permitAll()
                        .requestMatchers(antMatcher("/user/profile/**")).permitAll()
                        .requestMatchers(antMatcher("/api/v1/user/all")).permitAll()
                        .requestMatchers(antMatcher("/user/profile-photo/**")).permitAll()
                        .requestMatchers(antMatcher("/email/**")).permitAll()
                        .requestMatchers(antMatcher("/media/**")).permitAll()
                        .requestMatchers(antMatcher("/events/**")).permitAll()
                        .requestMatchers(antMatcher("/community/communities")).permitAll()
                        .requestMatchers(antMatcher("/community/search/**")).permitAll()
                        .requestMatchers(antMatcher("/payments/payment/**")).permitAll()
                        .requestMatchers(antMatcher("/payments/subscribe/**")).permitAll()
                        .requestMatchers(antMatcher("/payments/subscription/**")).permitAll()
                        .requestMatchers(antMatcher("/mail/**")).permitAll()
                        .requestMatchers(antMatcher("/notifications/**")).permitAll()
                        .requestMatchers(antMatcher("/community/**/posts")).permitAll()
                        .requestMatchers(antMatcher("/user/ratings/**")).permitAll()
                        .requestMatchers(antMatcher("/user/rated/**")).permitAll()
                        .requestMatchers(antMatcher("/inscription/user/**")).permitAll()
                        .requestMatchers(antMatcher("/inscription/event/**")).permitAll()
                        .requestMatchers(antMatcher("/post/**")).permitAll()
                        .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
                        // TODO: Cualquier otra solicitud requiere autenticación (JWT u otra autenticación configurada)
                        .anyRequest().authenticated()
                )

                // TODO: Permite la autenticación básica (para testing con Postman, por ejemplo)
                //.httpBasic(Customizer.withDefaults())
                // TODO: Desactiva el formulario de inicio de sesión predeterminado, ya que se usará JWT
                .formLogin(AbstractHttpConfigurer::disable)
                // TODO: Configura el manejo de excepciones para autenticación. Usa JwtAuthenticationEntryPoint para manejar errores 401 (no autorizado)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                // TODO: Configura la política de sesiones como "sin estado" (stateless), ya que JWT maneja la autenticación, no las sesiones de servidor
                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // TODO: Agrega la configuración para JWT en el filtro antes de los filtros predeterminados de Spring Security
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

        // TODO: Añadir el JWTFilter antes del filtro de autenticación de nombre de usuario/contraseña.
        //  Esto permite que el JWTFilter valide el token antes de la autenticación
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // TODO: Proporciona el AuthenticationManager que gestionará la autenticación basada en los detalles de usuario y contraseña
        return authenticationConfiguration.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
