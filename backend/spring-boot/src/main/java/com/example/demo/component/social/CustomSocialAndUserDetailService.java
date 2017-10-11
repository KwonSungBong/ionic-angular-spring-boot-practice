package com.example.demo.component.social;


import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;
/**
 * Created by whilemouse on 17. 8. 22.
 */
public interface CustomSocialAndUserDetailService extends UserDetailsService, SocialUserDetailsService
{
    UserDetails loadUserById(long id) throws UsernameNotFoundException;
    User loadUserByConnectionKey(ConnectionKey connectionKey);
    void updateUserSocial(User user);
    User findByUserId(String userId);
}
