package com.example.backend.config.security;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * SecurityProperties
 *
 * @author JuliusR
 */
@Validated
@ConfigurationProperties("security.web")
public class SecurityProperties {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
