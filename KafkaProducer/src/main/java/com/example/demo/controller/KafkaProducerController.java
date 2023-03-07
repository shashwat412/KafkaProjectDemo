package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EncryptedRekuestDto;
import com.example.demo.dto.EncryptedResponseDto;
import com.example.demo.service.KafkaProducerService;

@RestController
public class KafkaProducerController {
	
	@Autowired
	private KafkaProducerService service;
	
	@PostMapping(path = "/createRekuest")
	public EncryptedResponseDto sendToTopic(@RequestBody EncryptedRekuestDto rekuestDto)
	{
		return this.service.sendToTopic(rekuestDto);
	}
	
	

}
