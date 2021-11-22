package com.example.FinalProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Sergo")
                .password(passwordEncoder().encode("Sergo"))
                .roles("USER")
                .and()
                .withUser("Ivane")
                .password("Ivane")
                .roles("MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/app/all_**").hasRole("MANAGER")
                .antMatchers("/app/my_**", "/app/findParcels**", "/app/lugin", "/app/logout").hasRole("USER")
                .antMatchers("/app/login**", "/app/setLang**", "/app/main**", "/app/index**", "/app/registration**").anonymous()
                .antMatchers("/app/setLang**", "/app/getCity**", "/app/calculate_start**", "/app/calculate**", "/app/error**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/app/index_jsp")
                .loginProcessingUrl("/app/login")
                .defaultSuccessUrl("/app/lugin?username=Sergo",true)
                .and()
                .logout()
                .logoutUrl("/app/logout");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("password"))
//                        .roles("USER")
//                        .build()
//        );
//    }


}
