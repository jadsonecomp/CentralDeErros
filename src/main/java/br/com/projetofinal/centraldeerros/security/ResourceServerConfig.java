package br.com.projetofinal.centraldeerros.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers(HttpMethod.POST, "/usuarios").permitAll().
                antMatchers(HttpMethod.PUT, "/usuarios").authenticated().
                antMatchers(HttpMethod.GET, "/usuarios/{id}").authenticated().
                antMatchers(HttpMethod.PUT, "/usuarios/{id}").authenticated().
                antMatchers(HttpMethod.GET, "/usuarios/lista").authenticated().
                antMatchers(HttpMethod.GET, "/usuarios/login").authenticated().
                antMatchers(HttpMethod.GET, "/usuarios/email").authenticated().
                antMatchers(HttpMethod.POST, "/evento").authenticated().
                antMatchers(HttpMethod.PUT, "/evento").authenticated().
                antMatchers(HttpMethod.DELETE, "/evento").authenticated().
                antMatchers(HttpMethod.GET, "/evento").authenticated();
    }

}
