package com.example.demo.config;

import com.example.demo.component.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by ksb on 2017. 8. 19..
 */
@EnableWebSecurity
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private AuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomCsrfHeaderFilter customCsrfHeaderFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationProcessingFilter statelessLoginFilter = new CustomAuthenticationProcessingFilter("/login", customAuthenticationProvider);
        statelessLoginFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        statelessLoginFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        final SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
        socialConfigurer.addObjectPostProcessor(new ObjectPostProcessor<SocialAuthenticationFilter>() {
            @Override
            public <O extends SocialAuthenticationFilter> O postProcess(O socialAuthenticationFilter) {
                socialAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
                return socialAuthenticationFilter;
            }
        });

        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index", "/form", "/login", "/social", "/auth/**", "/websocket/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .authenticationProvider(customAuthenticationProvider)
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(customLogoutSuccessHandler)
            .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
            .and()
                .apply(socialConfigurer)
            .and()
                .addFilterBefore(statelessLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(customCsrfHeaderFilter, SessionManagementFilter.class);
    }


//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
