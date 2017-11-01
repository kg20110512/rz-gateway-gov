package com.sdjt.rzgatewaygov.configuration;

import oracle.jdbc.OracleData;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Component
//@Configuration
//@EnableConfigurationProperties(OracleConfiguration.class)
@ConfigurationProperties(prefix = "oracle")
public class OracleConfiguration {
//    @NotNull
    String username;
//    @NotNull
    String password;
//    @NotNull
    String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
