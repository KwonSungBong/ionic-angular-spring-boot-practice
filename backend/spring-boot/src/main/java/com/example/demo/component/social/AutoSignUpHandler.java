package com.example.demo.component.social;

import com.example.demo.entity.Authority;
import com.example.demo.entity.SocialUser;
import com.example.demo.entity.User;
import com.example.demo.entity.constant.SocialProvider;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by whilemouse on 17. 8. 22.
 */
@Component
public class AutoSignUpHandler implements ConnectionSignUp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private volatile long userCount;

    @Override
    @Transactional
    public String execute(Connection<?> connection) {
        final User user = new User();
        final List<Authority> userRoleList = new ArrayList<>();
        final Authority authority = new Authority();
        final SocialUser socialUser = new SocialUser();

//        if(connection.getKey().getProviderId().equals(SocialProvider.facebook)) {
//            Facebook facebook = (Facebook) connection.getApi();
//            String [] fields = { "id", "email",  "first_name", "last_name" };
//            org.springframework.social.facebook.api.User userProfile =
//                    facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
//
//        } else if(connection.getKey().getProviderId().equals(SocialProvider.google)) {
//
//        } else {
//            throw new AuthenticationCredentialsNotFoundException("");
//        }
//        Facebook facebook = (Facebook) connection.getApi();
//        String [] fields = { "id", "email",  "first_name", "last_name" };
//        org.springframework.social.facebook.api.User userProfile =
//                facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);

//        user.setUsername(userProfile.getFirstName() + ' ' + (userProfile.getLastName() == null ? "" : userProfile.getLastName()));
        user.setName(connection.getKey().getProviderId() + connection.getKey().getProviderUserId());
        user.setUsername(connection.getKey().getProviderId() + connection.getKey().getProviderUserId());
//        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode("bookstorage@bookstorage@" + connection.getKey().getProviderId()));
//        user.setEmail(connection.getKey().getProviderUserId() + "@" + connection.getKey().getProviderId() + ".tmp.com");

//        socialUser.setEmail(userProfile.getEmail());
        socialUser.setDisplayNm(connection.createData().getDisplayName());
        socialUser.setProviderId(SocialProvider.valueOf(connection.getKey().getProviderId()));
        socialUser.setProviderUserId(connection.getKey().getProviderUserId());
        socialUser.setAccessToken(connection.createData().getAccessToken());
        socialUser.setExpireTime(connection.createData().getExpireTime());
        socialUser.setRefreshToken(connection.createData().getRefreshToken());
        socialUser.setImageUrl(connection.createData().getImageUrl());
        socialUser.setProfileUrl(connection.createData().getProfileUrl());
        socialUser.setSecret(connection.createData().getSecret());
        socialUser.setUser(user);

        user.setSocialUser(socialUser);

        authority.setName("ROLE_USER");
//        authority.setUser(user);
//        userRoleList.add(userRole);

//        user.setUserRoleList(userRoleList);

        userRepository.saveAndFlush(user);

        return user.getUsername();
    }
}
