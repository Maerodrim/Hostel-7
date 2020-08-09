package com.ssau.Hostel7.config;

import com.ssau.Hostel7.constants.UrlsConstants;
import com.ssau.Hostel7.helper.holders.JwtConstantsHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService profileServiceImpl;

    private final PasswordEncoder customPasswordEncoder;

    private final AuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    private final Filter jwtAuthenticationFilter;

    private final JwtConstantsHolder jwtConstants;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage(UrlsConstants.LOGIN_URL)
                    .successHandler(jwtAuthenticationSuccessHandler)
                .and()
                .logout()
                    .logoutUrl(UrlsConstants.LOGOUT_URL)
                    .deleteCookies(jwtConstants.getCookieName())
                .and()
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(profileServiceImpl).passwordEncoder(customPasswordEncoder);
    }

}
