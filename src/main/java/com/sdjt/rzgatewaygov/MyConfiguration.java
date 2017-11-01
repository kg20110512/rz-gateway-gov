package com.sdjt.rzgatewaygov;


import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;

@Component
@Configuration
@EnableConfigurationProperties({GatewayConfiguration.class, OracleConfiguration.class})
public class MyConfiguration {

//    @Autowired
//    RestTemplate restTemplate;
//    @Bean
//    GatewayConfiguration gatewayConfiguration;

    @Autowired
    OracleConfiguration oracleConfiguration;

    @Bean
//    @Primary
    DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser(oracleConfiguration.getUsername());
        dataSource.setPassword(oracleConfiguration.getPassword());
        dataSource.setURL(oracleConfiguration.getUrl());
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }

    @Bean
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
    RestTemplate restTemplate(RestTemplateBuilder builder){
//        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return builder.build();

        RestTemplate restTemplate = builder.build();
//        restTemplate.getMessageConverters().add(0,new MyMappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(0,new MyFastJsonHttpMessageConverter());
        return restTemplate;
    }

}
