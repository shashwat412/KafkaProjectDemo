package com.example.demo.dto;

public class EncryptedResponseDto {

	private String encryptedResponse;

	public EncryptedResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncryptedResponseDto(String encryptedResponse) {
		super();
		this.encryptedResponse = encryptedResponse;
	}

	public String getEncryptedResponse() {
		return encryptedResponse;
	}

	public void setEncryptedResponse(String encryptedResponse) {
		this.encryptedResponse = encryptedResponse;
	}

	@Override
	public String toString() {
		return "EncryptedResponseDto [encryptedResponse=" + encryptedResponse + "]";
	}
	
}
