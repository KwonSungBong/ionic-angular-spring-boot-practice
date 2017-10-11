package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

/**
 * Created by whilemouse on 17. 8. 21.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @RequestMapping("/token")
    public CsrfToken main(CsrfToken token) {
        return token;
    }

    @RequestMapping("/me")
    @ResponseBody
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public User socialSignUp(WebRequest request){
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        UserProfile userProfile = connection.fetchUserProfile();

        return null;
    }

}