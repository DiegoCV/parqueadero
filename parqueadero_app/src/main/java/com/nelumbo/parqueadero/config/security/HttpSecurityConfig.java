package com.nelumbo.parqueadero.config.security;

import com.nelumbo.parqueadero.config.security.filter.JwtAuthenticationFilter;
import com.nelumbo.parqueadero.util.Permission;
import com.nelumbo.parqueadero.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf( csrfConfig -> csrfConfig.disable() )
            .sessionManagement( sessionMangConfig -> sessionMangConfig
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(builderRequestMatchers())
            .logout(logout ->
                    logout.logoutUrl("/api/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );

        return http.build();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> builderRequestMatchers() {
        return authConfig -> {

            authConfig.requestMatchers(HttpMethod.POST, "/api/auth/authenticate").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/api/auth/public-access").permitAll();
            authConfig.requestMatchers("/error").permitAll();

            authConfig.requestMatchers(HttpMethod.POST, "/api/auth/create").hasRole(Role.ADMINISTRATOR.toString());
            authConfig.requestMatchers(HttpMethod.POST, "/api/parqueadero/create").hasRole(Role.ADMINISTRATOR.toString());
            authConfig.requestMatchers(HttpMethod.GET, "/api/parqueadero/listAll").hasAuthority(Permission.READ_ALL_PARKS.name());
            authConfig.requestMatchers(HttpMethod.GET, "/api/parqueadero/list").hasAuthority(Permission.READ_PARK.name());
            authConfig.requestMatchers(HttpMethod.GET, "/api/parqueadero/*").hasAuthority(Permission.READ_PARK.name());
            authConfig.requestMatchers(HttpMethod.PUT, "/api/parqueadero/update").hasRole(Role.ADMINISTRATOR.toString());
            authConfig.requestMatchers(HttpMethod.DELETE, "/api/parqueadero/delete/*").hasRole(Role.ADMINISTRATOR.toString());


            authConfig.requestMatchers(HttpMethod.POST, "/api/registro/entrada").hasAuthority(Permission.CREATE_REGISTERS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/api/registro/salida").hasAuthority(Permission.CREATE_REGISTERS.name());

            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/listarByParqueadero/*").hasAuthority(Permission.READ_VEHICULOS.name());
            authConfig.requestMatchers(HttpMethod.POST, "/api/vehiculo/enviarCorreo").hasAuthority(Permission.SEND_EMAIL.name());

            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/indicador").hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SOCIO.toString());
            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/indicadorDos/*").hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SOCIO.toString());
            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/indicadorTres").hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SOCIO.toString());
            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/indicadorCuatro/*").hasAnyRole(Role.SOCIO.toString());
            authConfig.requestMatchers(HttpMethod.GET, "/api/vehiculo/indicadorCinco/placa/*").hasAnyRole(Role.ADMINISTRATOR.toString(), Role.SOCIO.toString());


            authConfig.anyRequest().denyAll();
        };
    }
}
