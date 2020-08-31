package br.com.projetofinal.centraldeerros.security;

import br.com.projetofinal.centraldeerros.entity.Usuario;
import br.com.projetofinal.centraldeerros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/h2/**",
                "/webjars/**");
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UsuarioRepository userRepository) throws Exception {
        if (userRepository.count() == 0) {
            Usuario usuario = new Usuario("Administrador",
                                           "admin",
                                                 passwordEncoder().encode("admin"),
                                           "admin@admin.com");
            userRepository.save(usuario);
        }

        builder.userDetailsService(username -> new Usuario(userRepository.findByLogin(username).orElse(null))).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
