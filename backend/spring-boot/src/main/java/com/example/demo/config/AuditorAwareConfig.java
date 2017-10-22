package com.example.demo.config;

import com.example.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ksb on 2017. 10. 21..
 */
@Component
//@EnableJpaAuditing
public class AuditorAwareConfig implements AuditorAware<User> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = new User();
//        user.setId(1L);
//        return user;
        return null;
    }

}
