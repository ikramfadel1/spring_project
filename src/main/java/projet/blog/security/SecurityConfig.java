package projet.blog.security;

import projet.blog.entities.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authCusomizer -> authCusomizer
                        .requestMatchers("/editCustomer", "/updateCustomer","deleteCustomer","/createCustomer", "/saveCustomer","/customersList","/createArticle","/createCA","/updateCA","/deleteCA/","/saveCA","/updateArticle","/article/delete/","/saveArticle").hasRole("ADMIN")
                        .requestMatchers("/listCA","/home").hasAnyRole( "USER","ADMIN")
                        .requestMatchers("/login","/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                )
                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))
                .build();
    }
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN", "USER").build(),
                User.withUsername("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER").build(),
                User.withUsername("accountant").password(new BCryptPasswordEncoder().encode("123")).roles("USER").build()

        );
    }
}
