package com.example.demo.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EncryptedResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.util.AES;

@Service
public class SendEncryptedService {
	
	@Value("${employee-out-topic}")
	String outTopic;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEncryptedService.class);
	
	public EncryptedResponseDto sendToOutTopic(ResponseDto responseDto)
	{
		LOGGER.info("ResponseDto Recieved : {}",responseDto);
		
		EncryptedResponseDto encryptedResponseDto = new EncryptedResponseDto();
		
		try {
			String kafkaValue = responseDto.getResponse();
			LOGGER.info("Generated String from ResponseDto : {}",kafkaValue);
			
			String encryptedKafkaValue=AES.encrypt(kafkaValue);
			LOGGER.info("Encrypted Kafka Value : {}",encryptedKafkaValue);
			
			String kafkaKey =generateKafkaKey();
			LOGGER.info("Generated Kafka Key : {}",kafkaKey);
			
			kafkaTemplate.send(outTopic, kafkaKey, encryptedKafkaValue);
			LOGGER.info("Send To Employee Out Topic Successfully : {}", encryptedKafkaValue);
			
			encryptedResponseDto.setEncryptedResponse("Send To Employee Out Topic Successfully : " + encryptedKafkaValue );
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("Could not Send to Employee Out Topic");
	        encryptedResponseDto.setEncryptedResponse(e.getMessage());
		}
		
		return encryptedResponseDto;
	}

	private String generateKafkaKey() {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		int key= random.nextInt(100, 999);
		String keyString = String.valueOf(key);
		
		return keyString;
	}

}
