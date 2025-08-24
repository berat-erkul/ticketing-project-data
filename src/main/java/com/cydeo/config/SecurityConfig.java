package com.cydeo.config;


import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        List<UserDetails> userList = new ArrayList<>();
//
//        User user1 = new User("mike", passwordEncoder.encode("password"),
//                List.of(new SimpleGrantedAuthority("ROLE_Admin")));
//
//        User user2 = new User("ozzy", passwordEncoder.encode("password"),
//                List.of(new SimpleGrantedAuthority("ROLE_Manager")));
//
//        userList.add(user1);
//        userList.add(user2);
//
//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").hasAuthority("Admin")
                        .requestMatchers("/project/**").hasAuthority("Manager")
                        .requestMatchers("/task/employee/**").hasAuthority("Employee")
                        .requestMatchers("/task/**").hasAuthority("Manager")
                        .requestMatchers(
                                "/",
                                "/login",
                                "/fragments/**",
                                "/assets/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
//                        .defaultSuccessUrl("/welcome")
                        .successHandler(authSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"))
                .rememberMe(remember -> remember
                        .tokenValiditySeconds(120)
                        .key("cydeo")
                        .userDetailsService(securityService))
                .build();

    }

}
