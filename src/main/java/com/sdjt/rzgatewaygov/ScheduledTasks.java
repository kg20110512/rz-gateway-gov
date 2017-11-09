package com.sdjt.rzgatewaygov;

import com.sdjt.rzgatewaygov.configuration.GatewayConfiguration;
import com.sdjt.rzgatewaygov.gateway.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GatewayConfiguration gatewayConfiguration;
    @Autowired
    GatewayBaseInfoRepository gatewayBaseInfoRepository;
    @Autowired
    GatewayStepInfoRepository gatewayStepInfoRepository;

//    @Scheduled(cron = "0 53 22 ? * MON-FRI")
    @Scheduled(fixedRate = 5000)
    public void baseInfo() {
        logger.info("=== baseInfo interface invoked by schedule ===");
        List<GatewayBaseInfo> list = gatewayBaseInfoRepository.findByIsSuccess("false");
        if (list.size() > 0){

            String baseInfoUrl = gatewayConfiguration.getUrl1();
            logger.info(baseInfoUrl);
            logger.info("=== stepinfo interface begin ===");

            list.stream().forEach(param -> {
            logger.info(param.getAPPID());

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAcceptCharset(Arrays.asList(Charset.forName("utf8")));
//			headers.set(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN");
//			headers.set(HttpHeaders.CONTENT_ENCODING, "utf-8");
//			headers.set(HttpHeaders.ACCEPT_ENCODING, "utf-8");
//            HttpEntity<GreetingParam> httpEntity = new HttpEntity<>(param, headers);

//            ResponseEntity<GatewayResponse> response = restTemplate.postForEntity(url1, param, GatewayResponse.class);
//            GatewayResponse response = restTemplate.postForObject(url1, param, GatewayResponse.class);
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Accept-Charset", "utf-8");
//            headers.set("Content-type", "application/json; charset=utf-8");  //header的规定
//            List<Charset> charsets = new ArrayList<>();
//            charsets.add(Charset.forName("utf-8"));
//            headers.setAcceptCharset(charsets);
//            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<GatewayBaseInfo> httpEntity = new HttpEntity<>(param, headers);

                GatewayResponse response = restTemplate.postForObject(baseInfoUrl, httpEntity, GatewayResponse.class);

                param.setIsSuccess(String.valueOf(response.isSuccess()));
                if (response.getMessage() != null) {
                    param.setMESSAGE(response.getMessage());
                }
                if (response.getBUSID() != null) {
                    param.setBUSID(response.getBUSID());
                }
                gatewayBaseInfoRepository.save(param);

                System.out.println(response.toString());
//                logger.info(response.toString());
//                logger.info(url1);
                logger.info("=== stepinfo interface over ===");
            });
        }

    }



    @Scheduled(cron = "0 55 22 ? * MON-FRI")
    public void stepInfo() {

        logger.info("=== baseInfo interface invoked by schedule ===");
        List<GatewayStepInfo> list = gatewayStepInfoRepository.findByIsSuccessIsNull();
        if (list.size() > 0) {
            String stepInfoUrl = gatewayConfiguration.getUrl2();
            logger.info(stepInfoUrl);
            logger.info("=== stepinfo interface begin ===");

            list.stream().forEach(param -> {

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAcceptCharset(Arrays.asList(Charset.forName("utf8")));
                HttpEntity<GatewayStepInfo> httpEntity = new HttpEntity<>(param, headers);

                GatewayStepInfo response = restTemplate.postForObject(stepInfoUrl, httpEntity, GatewayStepInfo.class);

                System.out.println(response.toString());
//                System.out.println(response.getIsSuccess());
//                System.out.println(response.getMESSAGE());
//                logger.info(response.getMESSAGE());
//                logger.info(url2);

                param.setIsSuccess(String.valueOf(response.getIsSuccess()));
                if (response.getMESSAGE() != null) {
                    param.setMESSAGE(response.getMESSAGE());
                }
                if (response.getBUSID() != null) {
                    param.setBUSID(response.getBUSID());
                }
                gatewayStepInfoRepository.save(param);

            });
            logger.info("=== stepinfo interface over ===");
        } else {
            logger.info("=== no appropriate stepinfo ===");
        }
    }
}
