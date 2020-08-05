package com.smart.dashboard.producer;

import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Configuration
@EnableScheduling
public class Sender {

  private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

  @Autowired
  private KafkaTemplate<String, ?> kafkaTemplate;
  
  
	@Scheduled(cron ="0/5 * * * * ?")
	void datapointMaker() {
		
		System.out.println("Running....");
		  //  System.out.println("Sending message...");
			int temprandom = ThreadLocalRandom.current().nextInt(0, 40 + 1);
			int humidityrandom = ThreadLocalRandom.current().nextInt(50, 90 + 1);
			
			JSONObject jo = new JSONObject();
			jo.put("t", temprandom);
			jo.put("h", humidityrandom);
			
			

//			JsonParser jsonParser = new JsonParser();
//			JsonObject jo1 = (JsonObject)jsonParser.parse(jo);
	
			System.out.println(jo.toString());
			send("test",jo.toString());
		
		
	}

  public void send(String topic, Object payload) {
    LOGGER.info("sending payload='{}' to topic='{}'", payload.toString(), topic);
    kafkaTemplate
        .send(MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.TOPIC, topic).build());
  }
}
