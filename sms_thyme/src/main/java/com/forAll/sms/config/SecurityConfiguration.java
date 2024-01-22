package com.forAll.sms.config;

import com.forAll.sms.dto.UserDto;
import com.forAll.sms.entity.User;
import com.forAll.sms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;


    @Bean
    public  static  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/staff", "/student", "/parent").authenticated()
                .requestMatchers("/register/**", "/index").permitAll()
                .requestMatchers("/user").hasRole("STAFF")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login");

//        http.csrf().disable().
//                authorizeRequests().anyRequest().authenticated()
//                .and().formLogin().loginPage("/login")
//                .and().formLogin().loginProcessingUrl("/login")
//                .and().formLogin().defaultSuccessUrl("/").permitAll()
//                .and().logout().logoutSuccessUrl("/login");
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/staff","/student", "/parent").authenticated()
//                .requestMatchers("/register/**", "index").permitAll()
//                .requestMatchers("/user").hasRole("STAFF")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin(     form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/home")
//                                .permitAll())
//                .logout(
//                        logout -> logout
//                               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                                .logoutSuccessUrl("/login"))
//
//
//                ;
////                                .sessionManagement(sessionManagement -> sessionManagement
////                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                                );


        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = (UserDetails) UserDto.builder()
//                .email("staff")
//                .password(passwordEncoder().encode("staff"))
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }


}






//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }