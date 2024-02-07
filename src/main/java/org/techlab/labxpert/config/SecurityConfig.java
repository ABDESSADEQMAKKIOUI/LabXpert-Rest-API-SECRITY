package org.techlab.labxpert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.techlab.labxpert.Enum.RoleUser;
import org.techlab.labxpert.security.JWTAuthenticationFilter;
import org.techlab.labxpert.security.JWTHelper;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTHelper jwtHelper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/api/v1/analyse").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/echantillon").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/norme").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/fournisseur").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/numeration").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/patient").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/outil").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/Reactif").hasRole(RoleUser.Responsable.name())
                .antMatchers("/api/v1/utilisateur").hasRole(RoleUser.Responsable.name())
                .antMatchers(POST,"/api/v1/Reactif").hasRole(RoleUser.Technicien.name())
                .antMatchers(POST,"/api/v1/numeration").hasRole(RoleUser.Technicien.name())
                .antMatchers(POST,"/api/v1/echantillon").hasRole(RoleUser.Technicien.name())
                .antMatchers(POST,"/api/v1/patient").hasRole(RoleUser.Technicien.name())
                .antMatchers(GET,"/api/v1/numeration").hasRole(RoleUser.Technicien.name())
                .antMatchers(PUT,"/api/v1/numeration").hasRole(RoleUser.Technicien.name())
                .antMatchers(DELETE,"/api/v1/numeration").hasRole(RoleUser.Technicien.name())
                .antMatchers(POST,"/api/v1/echantillon").hasRole(RoleUser.Preleveur.name())
                .antMatchers(PUT,"/api/v1/echantillon").hasRole(RoleUser.Preleveur.name())
                .antMatchers(GET,"/api/v1/echantillon").hasRole(RoleUser.Preleveur.name())
                .antMatchers(DELETE,"/api/v1/echantillon").hasRole(RoleUser.Preleveur.name())
                .anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtHelper));
//        http.addFilterBefore(new JWTAuthorizationFilter(jwtHelper), UsernamePasswordAuthenticationFilter.class);

        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
