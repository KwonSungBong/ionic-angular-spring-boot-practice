package com.example.demo.component.social;

import com.example.demo.entity.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by whilemouse on 17. 8. 22.
 */
public class CustomSocialUsersConnectionRepository implements UsersConnectionRepository {

    private CustomSocialAndUserDetailService userDetailService;

    private ConnectionFactoryLocator connectionFactoryLocator;
    private ConnectionSignUp connectionSignUp;

    public CustomSocialUsersConnectionRepository(CustomSocialAndUserDetailService userDetailService, ConnectionFactoryLocator connectionFactoryLocator){
        this.userDetailService = userDetailService;
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        try {
            User user = userDetailService.loadUserByConnectionKey(connection.getKey());
            user.getSocialUser().setAccessToken(connection.createData().getAccessToken());
            userDetailService.updateUserSocial(user);
            return Arrays.asList(user.getUsername());
        } catch (Exception e) {
            return Arrays.asList(connectionSignUp.execute(connection));
        }
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        Set<String> keys = new HashSet<>();
        for (String userId : providerUserIds) {
            ConnectionKey ck = new ConnectionKey(providerId, userId);
            try {
                keys.add(userDetailService.loadUserByConnectionKey(ck).getUsername());
            } catch (AuthenticationException ae) {
                //ignore
            }
        }
        return keys;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        final ConnectionRepository connectionRepository = new CustomJdbcConnectionRepository(userId, connectionFactoryLocator, userDetailService);
        final User user = userDetailService.findByUserId(userId);
        final ConnectionData connectionData = new ConnectionData(
                user.getSocialUser().getProviderId().name(),
                user.getSocialUser().getProviderUserId(),
                null, null, null,
                user.getSocialUser().getAccessToken(),
                null, null, null
        );

        final Connection connection = connectionFactoryLocator.getConnectionFactory(user.getSocialUser().getProviderId().name()).createConnection(connectionData);

        connectionRepository.addConnection(connection);
        return connectionRepository;
    }

    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}
