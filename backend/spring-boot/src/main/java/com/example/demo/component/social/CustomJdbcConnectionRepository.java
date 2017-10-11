package com.example.demo.component.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;

import java.util.List;
/**
 * Created by whilemouse on 17. 8. 22.
 */
public class CustomJdbcConnectionRepository implements ConnectionRepository
{

    private final String userId;

    private final ConnectionFactoryLocator connectionFactoryLocator;

    private final CustomSocialAndUserDetailService userDetailService;

    public CustomJdbcConnectionRepository(String userId, ConnectionFactoryLocator connectionFactoryLocator, CustomSocialAndUserDetailService userDetailService) {
        this.userId = userId;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.userDetailService = userDetailService;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        return null;
    }

    @Override
    public List<Connection<?>> findConnections(String providerId) {
        return null;
    }

    @Override
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        return null;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        return null;
    }

    @Override
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        return null;
    }

    @Override
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        return null;
    }

    @Override
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        return null;
    }

    @Override
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        return null;
    }

    @Override
    public void addConnection(Connection<?> connection) {

    }

    @Override
    public void updateConnection(Connection<?> connection) {

    }

    @Override
    public void removeConnections(String providerId) {

    }

    @Override
    public void removeConnection(ConnectionKey connectionKey) {

    }
}
