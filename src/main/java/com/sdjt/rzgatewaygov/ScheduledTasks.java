package com.sdjt.rzgatewaygov;

import com.sdjt.rzgatewaygov.configuration.GatewayConfiguration;
import com.sdjt.rzgatewaygov.gateway.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

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

    @Scheduled(cron = "0 0 22 ? * MON-FRI")
//    @Scheduled(fixedRate = 8000)
    public void baseInfo() {
        logger.info("=== baseInfo interface invoked by schedule ===");
        List<GatewayBaseInfo> list = gatewayBaseInfoRepository.findByIsSuccess("false");
        if (list.size() > 0) {

            String baseInfoUrl = gatewayConfiguration.getUrl1();
            logger.info(baseInfoUrl);
            logger.info("=== baseinfo interface begin ===");

            list.stream().forEach(param -> {
                logger.info("=== sid = " + param.getSID() + " ===");
                logger.info(param.toString());

                HttpHeaders headers = new HttpHeaders();
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("APPID", param.getAPPID());
                paramMap.put("APPLICANT", param.getAPPLICANT());
                paramMap.put("APPPWD", param.getAPPPWD());
                paramMap.put("APPLICANTADDRESS", param.getAPPLICANTADDRESS());
                paramMap.put("APPLICANTCOMPANY", param.getAPPLICANTCOMPANY());
                paramMap.put("APPLICANTDOB", param.getAPPLICANTDOB());
                paramMap.put("APPLICANTIDCARD", param.getAPPLICANTIDCARD());
                paramMap.put("APPLICANTMOBILE", param.getAPPLICANTMOBILE());
                paramMap.put("APPLICANTNATION", param.getAPPLICANTNATION());
                paramMap.put("APPLICANTSEX", param.getAPPLICANTSEX());
                paramMap.put("BJSJ", param.getBJSJ());
                paramMap.put("BLSTTS", param.getBLSTTS());
                paramMap.put("CNBJSJ", param.getCNBJSJ());
                paramMap.put("ITEMCODE", param.getITEMCODE());
                paramMap.put("SID", param.getSID());
                paramMap.put("SLR", param.getSLR());
                paramMap.put("SLSJ", param.getSLSJ());
                paramMap.put("SQSJ", param.getSQSJ());
                paramMap.put("YWTYPE", param.getYWTYPE());


                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setAcceptCharset(Arrays.asList(Charset.forName("utf8")));
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                StringBuilder builder = new StringBuilder();
                Set<String> collect = paramMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toSet());
                collect.forEach(p -> builder.append("&").append(p));
                String paramStr = builder.toString().substring(1);
                System.out.println("=== paramStr = " + paramStr);


                HttpEntity httpEntity = new HttpEntity(paramStr, headers);
                ResponseEntity<GatewayResponse> responseEntity = restTemplate.exchange(baseInfoUrl, HttpMethod.POST, httpEntity, GatewayResponse.class);
                GatewayResponse response = responseEntity.getBody();

                param.setIsSuccess(String.valueOf(response.isSuccess()));
                if (response.getMessage() != null) {
                    param.setMESSAGE(response.getMessage());
                }
                if (response.getBUSID() != null) {
                    param.setBUSID(response.getBUSID());
                }
                gatewayBaseInfoRepository.save(param);

                System.out.println(response.toString());
                logger.info("=== baseinfo interface over ===");

            });
        } else {
            logger.info("=== no appropriate baseinfo ===");
        }

    }


    @Scheduled(cron = "0 0 23 ? * MON-FRI")
    public void stepInfo() {

        logger.info("=== baseInfo interface invoked by schedule ===");
        List<GatewayStepInfo> list = gatewayStepInfoRepository.findByIsSuccess("false");
        if (list.size() > 0) {
            String stepInfoUrl = gatewayConfiguration.getUrl2();
            logger.info(stepInfoUrl);
            logger.info("=== stepinfo interface begin ===");

            list.stream().forEach(param -> {
                logger.info("=== sid = " + param.getSID() + " ===");
                logger.info(param.toString());

                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("ACTORNAME", param.getACTORNAME());
                paramMap.put("BUSID", param.getBUSID());
                paramMap.put("DESCR", param.getDESCR());
                paramMap.put("ENDAT", param.getENDAT());
                paramMap.put("SID", param.getSID());
                paramMap.put("STARTAT", param.getSTARTAT());
                paramMap.put("STEPNAME", param.getSTEPNAME());
                paramMap.put("YESNO", param.getYESNO());

                StringBuilder builder = new StringBuilder();
                Set<String> collect = paramMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toSet());
                collect.forEach(p -> builder.append("&").append(p));
                String paramStr = builder.toString().substring(1);
                System.out.println("=== paramStr = " + paramStr);

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
                HttpEntity<String> httpEntity = new HttpEntity<>(paramStr, headers);

                GatewayStepInfo response = restTemplate.postForObject(stepInfoUrl, httpEntity, GatewayStepInfo.class);

                System.out.println(response.toString());

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
