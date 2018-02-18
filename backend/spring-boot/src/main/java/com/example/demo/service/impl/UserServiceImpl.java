package com.example.demo.service.impl;

import com.example.demo.dto.SecurityUser;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto.Login getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) return null;
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return modelMapper.map(user, UserDto.Login.class);
    }

    @Override
    public User getUesr(Principal user) {
        User principalUser;
        if (user instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) user;
            principalUser = (User)((SecurityUser)(authenticationToken).getPrincipal()).getUser();
        } else if (user instanceof SocialAuthenticationToken) {
            SocialAuthenticationToken authenticationToken = (SocialAuthenticationToken) user;
            principalUser = (User)((SecurityUser)(authenticationToken).getPrincipal()).getUser();
        } else {
            throw new AuthenticationCredentialsNotFoundException("");
        }
        return principalUser;
    }

}
