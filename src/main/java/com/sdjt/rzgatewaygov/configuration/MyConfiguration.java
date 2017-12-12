package com.sdjt.rzgatewaygov.configuration;


import com.sdjt.rzgatewaygov.http.MyFastJsonHttpMessageConverter;
import com.sdjt.rzgatewaygov.http.MyMappingJackson2HttpMessageConverter;
import com.sdjt.rzgatewaygov.http.MyStringHttpMessageConverter;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Arrays;

@Component
@Configuration
@EnableConfigurationProperties({GatewayConfiguration.class})
public class MyConfiguration {

//    @Autowired
//    OracleConfiguration oracleConfiguration;

//    @Bean
//    DataSource dataSource() throws SQLException {
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setUser(oracleConfiguration.getUsername());
//        dataSource.setPassword(oracleConfiguration.getPassword());
//        dataSource.setURL(oracleConfiguration.getUrl());
//        dataSource.setImplicitCachingEnabled(true);
//        dataSource.setFastConnectionFailoverEnabled(true);
//        return dataSource;
//    }
    @Bean
    MyMappingJackson2HttpMessageConverter myMappingJackson2HttpMessageConverter(){
        MyMappingJackson2HttpMessageConverter converter = new MyMappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        return converter;
    }

    @Bean
    MyStringHttpMessageConverter myStringHttpMessageConverter(){
        MyStringHttpMessageConverter converter = new MyStringHttpMessageConverter(Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        return converter;
    }

    @Bean
    @Autowired
    RestTemplate restTemplate(RestTemplateBuilder builder, MyStringHttpMessageConverter myStringHttpMessageConverter){
        RestTemplate restTemplate = builder.build();
        restTemplate.getMessageConverters().add(0,new MyFastJsonHttpMessageConverter());
        restTemplate.getMessageConverters().add(myStringHttpMessageConverter);
        return restTemplate;
    }

}
