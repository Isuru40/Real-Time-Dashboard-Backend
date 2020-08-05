package com.smart.dashboard.consumer;

import java.io.IOException;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.smart.dashboard.model.UpdateMessage;
import com.smart.dashboard.repository.UpdatemessageRepository;




public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
  
	@Autowired 
	private UpdatemessageRepository updatemessageRepository;
  

	private CountDownLatch latch = new CountDownLatch(2);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.consumer}")
  public void receiveBar( UpdateMessage signal) throws IOException,NoSuchAlgorithmException {
	
	  LOGGER.info("received {}",signal.getMessage());
	 
	  updatemessageRepository.save(signal);

    latch.countDown();
  }

  

}









