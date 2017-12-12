package com.sdjt.rzgatewaygov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
public class RzGatewayGovApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RzGatewayGovApplication.class, args);
	}

	@Autowired
	ScheduledTasks scheduledTasks;
	@Override
	public void run(String... args) throws Exception {
		scheduledTasks.baseInfo();
		scheduledTasks.stepInfo();
	}
//
//	@Autowired
//	RestTemplate restTemplate;

//	@Override
//	public void run(String... args) throws Exception {
//		Greeting greeting = restTemplate.getForObject(new URI("http://localhost:8080/greeting"), Greeting.class);
//		System.out.println(greeting.toString());
//	}
}
