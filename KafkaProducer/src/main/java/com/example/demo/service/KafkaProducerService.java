package com.example.demo.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EncryptedRekuestDto;
import com.example.demo.dto.EncryptedResponseDto;

@Service
public class KafkaProducerService 
{
	@Value("${employee-in-topic}")
	String inTopic;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
	
	public EncryptedResponseDto sendToTopic(EncryptedRekuestDto rekuestDto)
	{
		LOGGER.info(rekuestDto.toString());
		
		EncryptedResponseDto encResponseDto = new EncryptedResponseDto();
		
		try {
			String kafkaValue = rekuestDto.getEncryptedRekuest();
			String kafkaKey = generateKafkaKey();
			LOGGER.info("Generated String from Request" + kafkaValue);
			
			kafkaTemplate.send(inTopic, kafkaKey, kafkaValue);
			LOGGER.info("Send To Employee In Topic Successfully : {}", kafkaValue);

			encResponseDto.setResponse("Send To Employee In Topic Successfully : " + kafkaValue );
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("Could not Send to Employee In Topic");
	        encResponseDto.setResponse(e.getMessage());
		}
		return encResponseDto;
	}

	private String generateKafkaKey() {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		int key= random.nextInt(100, 999);
		String keyString = String.valueOf(key);
		
		return keyString;
	}
	

}
