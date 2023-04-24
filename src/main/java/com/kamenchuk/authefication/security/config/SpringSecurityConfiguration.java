package com.kamenchuk.authefication.security.config;


import com.kamenchuk.authefication.security.jwt.JwtFilter;
import com.kamenchuk.authefication.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final JwtFilter filter;
    private final UserServiceImpl service;


    @Autowired
    public SpringSecurityConfiguration(JwtFilter filter,
                                       UserServiceImpl service) {
        this.filter = filter;
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/user/create/**").permitAll()
//                .antMatchers("/car/admin/**").hasRole("ADMIN")
//                .antMatchers("/order/admin/**").hasRole("ADMIN")
//                .antMatchers("/role/**").hasRole("ADMIN")
//                .antMatchers("/user/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
////                .antMatchers("/user/create/**").permitAll()
////                .antMatchers("/car/admin/**").hasRole("ADMIN")
////                .antMatchers("/order/admin/**").hasRole("ADMIN")
////                .antMatchers("/role/**").hasRole("ADMIN")
////                .antMatchers("/user/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated();
//                .antMatchers("/user/**").authenticated();
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.userDetailsService(service);
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
}
