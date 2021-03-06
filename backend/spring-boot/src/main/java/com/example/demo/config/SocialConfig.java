package com.example.demo.config;

import com.example.demo.component.social.CustomSocialAndUserDetailService;
import com.example.demo.component.social.CustomSocialUsersConnectionRepository;
import com.example.demo.component.social.kakao.connect.KakaoConnectionFactory;
import com.example.demo.component.social.naver.connect.NaverConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 * Created by whilemouse on 17. 8. 22.
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Value("${spring.social.google.app-id}")
    private String googleAppId;

    @Value("${spring.social.google.app-secret}")
    private String googleSecret;

    @Value("${spring.social.kakao.app-id}")
    private String kakaoAppId;

    @Value("${spring.social.naver.app-id}")
    private String naverAppId;

    @Value("${spring.social.naver.app-secret}")
    private String naverSecret;

    @Autowired
    private ConnectionSignUp autoSignUpHandler;

    @Autowired
    private CustomSocialAndUserDetailService customSocialAndUserDetailService;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(
                new KakaoConnectionFactory(kakaoAppId)
        );
        connectionFactoryConfigurer.addConnectionFactory(
                new NaverConnectionFactory(
                        naverAppId,
                        naverSecret
                )
        );
        GoogleConnectionFactory gcf = new GoogleConnectionFactory(
                googleAppId,
                googleSecret
        );
        gcf.setScope("email");
        connectionFactoryConfigurer.addConnectionFactory(gcf);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        CustomSocialUsersConnectionRepository usersConnectionRepository =
                new CustomSocialUsersConnectionRepository(customSocialAndUserDetailService, connectionFactoryLocator);

        usersConnectionRepository.setConnectionSignUp(autoSignUpHandler);

        return usersConnectionRepository;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }
}
