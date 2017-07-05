package com.api.tuto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import static java.util.Collections.singletonList;

/**
 * Created by youssefguenoun on 27/06/2017.
 */
@Configuration
@EnableOAuth2Client
@EnableConfigurationProperties(Oauth2ClientProperties.class)
public class Oauth2ClientConfig {

    @Autowired
    private Oauth2ClientProperties oauth2ClientProperties;

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails(){
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(oauth2ClientProperties.getAccessTokenUri());
        details.setClientId(oauth2ClientProperties.getClientId());
        details.setClientSecret(oauth2ClientProperties.getClientSecret());
        details.setScope(oauth2ClientProperties.getScopes());

        return details;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext clientContext){
        OAuth2RestTemplate template = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), clientContext);
        AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(singletonList(new ClientCredentialsAccessTokenProvider()));
        template.setAccessTokenProvider(accessTokenProvider);

        return template;
    }
}
