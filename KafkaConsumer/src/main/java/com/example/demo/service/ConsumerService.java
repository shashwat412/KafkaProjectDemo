package com.example.demo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.dao.KafkaConsumerDao;
import com.example.demo.dto.EncryptedResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Employee;
import com.example.demo.util.AES;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;



@Service
public class ConsumerService {
	
	@Autowired
	KafkaConsumerDao consumerDao;
	
	@Autowired
	SendEncryptedService encryptedService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = {"employee-in-topic"})
    public EncryptedResponseDto onMessage(ConsumerRecord<String,String > consumerRecord)
   {
    
    ResponseDto responseDto = new ResponseDto(); 	
    	
     LOGGER.info("Consumer Record : {}",consumerRecord.value());
     
     String encryptedValue = consumerRecord.value();
     LOGGER.info("Encrypted Value : {}",encryptedValue);
     
     Gson gson = new GsonBuilder().create();
    
     String decryptedValue = AES.decrypt(encryptedValue); 
     LOGGER.info("Decrypted Value : {}",decryptedValue);
     
     Employee employee = new Employee();
	try {
		employee = gson.fromJson(decryptedValue, Employee.class);
		 LOGGER.info("Employee Object : {}", employee);
	} catch (JsonSyntaxException e) {
		LOGGER.info("Json Not Correct");
		e.printStackTrace();
	}
     
     Employee responseEmp= new Employee();
     
     try {
		responseEmp= consumerDao.findById(employee.getId());
		responseDto.setResponse("Verified");
		 LOGGER.info("Response Employee : {}", responseEmp);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		responseDto.setResponse("Not Verified");
   	    LOGGER.info("responseDto : {}", responseDto);
		e.printStackTrace();
	}
     
//     if(responseEmp!=null)
//     {
//    	 responseDto.setResponse("Verified");
//    	 LOGGER.info("responseDto : {}", responseDto);
//     }
//     else
//     {
//    	 responseDto.setResponse("Not Verified");
//    	 LOGGER.info("responseDto : {}", responseDto);
//     }
     
     
     EncryptedResponseDto encryptedResponseDto = new EncryptedResponseDto();
     encryptedResponseDto=encryptedService.sendToOutTopic(responseDto);
     LOGGER.info("Encrypted Response Dto : {}",encryptedResponseDto);
     
     return encryptedResponseDto;
   }

}
