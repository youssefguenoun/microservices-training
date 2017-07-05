package com.api.tuto.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssefguenoun on 27/06/2017.
 */
@ConfigurationProperties("security.oauth2.client.client-credentials")
@Data
@NoArgsConstructor
public class Oauth2ClientProperties {

    private String accessTokenUri;

    private String clientId;

    private String clientSecret;

    private List<String> scopes = new ArrayList<>();
}
