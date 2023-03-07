package com.example.demo.dao;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Employee;
import com.example.demo.repo.KafkaConsumerRepo;

@Service
public class KafkaConsumerDao {

	@Autowired
	private KafkaConsumerRepo repo;
	
	public Employee findById(String id)
	{
		return this.repo.findById(id).get();
	}
	
//	public ResponseDto findById(String id)
//	{
//		Employee employee = this.repo.findById(id).orElseThrow(()->new RuntimeException("Element Not Found"));
//		
//		ResponseDto responseDto = new ResponseDto();
//		if(employee!=null)
//		{
//			responseDto.setResponse("Verified");
//		}
//		else
//		{
//			responseDto.setResponse("Not Verified");
//		}
//		
//		return responseDto;
//	}
}
