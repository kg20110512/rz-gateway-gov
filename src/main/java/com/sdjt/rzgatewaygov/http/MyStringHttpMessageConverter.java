package com.sdjt.rzgatewaygov.http;

import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

public class MyStringHttpMessageConverter extends StringHttpMessageConverter {
    public MyStringHttpMessageConverter(Charset defaultCharset) {
        super(defaultCharset);
    }
}
