package com.aitorgc.ms.subscriptions.api.internalapis.users;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author Aitor Gil Callejo
 *
 */
@ConfigurationProperties(prefix = "bookker.internal.apis.users")
public class UsersProperties {

    private String url;

    private String user;

    private String password;

    protected UsersProperties() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
