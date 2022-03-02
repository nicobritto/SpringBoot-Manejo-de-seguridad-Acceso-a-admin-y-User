package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //metodo para agregar nuevos usuarioas
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //con la variable vamoa a crear usuarios 
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //con este metodo vamos a indicar los pats que vamos a utilizar
                // vamos a restrigir el acceso a editar agregar eliminar
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                //solos los usuarios admin van a poder acceder a agregar eliminar editar
                     .hasRole("ADMIN")
                //indicamos quien puede acceder al pad raiz o sea e pad muestra el listado de personas
                      .antMatchers("/")
                .hasAnyRole("USER","ADMIN")
                //agregamos el formLogin que vamos a utilizar ya personalizado
                //que es el que esta templates login
                .and()
                .formLogin()
                .loginPage("/login")
        //agregamos configuracion de la paguina de eerror
                .and()
                        .exceptionHandling().accessDeniedPage("/errores/403")
                ;
        
        
    }

}
