package com.example.demo.component.security;


import com.example.demo.dto.SecurityUser;
import com.example.demo.component.social.CustomSocialAndUserDetailService;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomSocialAndUserDetailService userDetailsService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Transactional
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new BadCredentialsException("사용자 정보가 없습니다.");
        }

//        if(!passwordEncoder.matches(password, user.getPassword())){
//            throw new BadCredentialsException("사용자 정보가 없습니다.");
//        }

        SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserById(user.getId());

        return new UsernamePasswordAuthenticationToken(securityUser, user.getId(), securityUser.getAuthorities());
    }

}
