package com.example.demo.dto;


public class EncryptedResponseDto 
{
	private String response;

	public EncryptedResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncryptedResponseDto(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "EncryptedResponseDto [response=" + response + "]";
	}

}
