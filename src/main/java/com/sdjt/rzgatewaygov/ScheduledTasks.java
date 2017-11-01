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

    //am 8:00
//    @Scheduled(cron = "0 0 8 0 0")
//    @Scheduled(fixedRate = 5000)
    public void action1(){
        String url1 = gatewayConfiguration.getUrl1();
        logger.info(url1);
        List<GatewayBaseInfo> list = gatewayBaseInfoRepository.findByIsSuccess("false");
//                gatewayBaseInfoRepository.findAll();
        list.stream().forEach(param->{
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

//            String response = restTemplate.postForObject(url1, null, String.class);
            GatewayResponse response = restTemplate.postForObject(url1, httpEntity, GatewayResponse.class);

//            if (response.isSuccess()){
                param.setIsSuccess(String.valueOf(response.isSuccess()));
                if (response.getMessage() != null){
                    param.setMESSAGE(response.getMessage());
                }
                if (response.getBUSID() != null){
                    param.setBUSID(response.getBUSID());
                }
                gatewayBaseInfoRepository.save(param);
//            }
//            ResponseEntity<GatewayResponse> responseEntity = restTemplate.postForEntity(url1, null, GatewayResponse.class);
//            logger.info(responseEntity.getBody().getMessage());
//            try {
//                response = new String(response.getBytes(),"utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

//            restTemplate.getMessageConverters().forEach(converter->{
//                logger.info(converter.getClass().getSimpleName());
//                logger.info(converter.getSupportedMediaTypes().toString());
//            });

            System.out.println(response.toString());
            logger.info(response.toString());
            logger.info(url1);
//            logger.info(response.getMessage());
//            logger.info(response.getIsSuccess());
//            logger.info(responseEntity.getBody().toString());
//            logger.info(response.getBody().getBUSID());
            logger.info("=== action1 ===");
        });
    }

    @Scheduled(fixedRate = 5000)
    public void action2() {

        String url2 = gatewayConfiguration.getUrl2();
        logger.info(url2);
        List<GatewayStepInfo> list = gatewayStepInfoRepository.findByIsSuccessIsNull();
//                gatewayBaseInfoRepository.findAll();
        list.stream().forEach(param -> {
//            logger.info(param.APPID);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAcceptCharset(Arrays.asList(Charset.forName("utf8")));
            HttpEntity<GatewayStepInfo> httpEntity = new HttpEntity<>(param, headers);

//            String response = restTemplate.postForObject(url1, null, String.class);
            GatewayStepInfo response = restTemplate.postForObject(url2, httpEntity, GatewayStepInfo.class);


            System.out.println(response.getIsSuccess());
            System.out.println(response.getMESSAGE());
            logger.info(response.getMESSAGE());
            logger.info(url2);

            param.setIsSuccess(String.valueOf(response.getIsSuccess()));
            if (response.getMESSAGE() != null) {
                param.setMESSAGE(response.getMESSAGE());
            }
            if (response.getBUSID() != null) {
                param.setBUSID(response.getBUSID());
            }
            gatewayStepInfoRepository.save(param);



//            logger.info(response.getMessage());
//            logger.info(response.getIsSuccess());
//            logger.info(responseEntity.getBody().toString());
//            logger.info(response.getBody().getBUSID());
            logger.info("=== action2 ===");
        });
    }
}
