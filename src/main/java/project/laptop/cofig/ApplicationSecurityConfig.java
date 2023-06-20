package project.laptop.cofig;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.laptop.repository.UserRepository;
import project.laptop.service.LaptopUserDetailsService;
import project.laptop.service.OAuthSuccessHandler;

@Configuration
@EnableMethodSecurity
public class ApplicationSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {

        http.

                authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                requestMatchers( "/users/login", "/users/register","/users/login-error").anonymous().
                requestMatchers("/offers/add","/users/profile","/cart").authenticated().
                requestMatchers("/","/brands/all","/offers/search","/offers/all","/about").permitAll().
                requestMatchers("/maintenance").permitAll().
                requestMatchers("/api/**").permitAll().
                requestMatchers("/users/change-role").hasRole("ADMIN").
                anyRequest().
                authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/").
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                and().
                oauth2Login().
                loginPage("/users/login")
                .successHandler(oAuthSuccessHandler);


        return http.build();
    }

    @Primary
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new LaptopUserDetailsService(userRepository);
    }
}

