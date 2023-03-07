package com.example.demo.dto;

public class EncryptedRekuestDto 
{
	private String encryptedRekuest;

	public EncryptedRekuestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncryptedRekuestDto(String encryptedRekuest) {
		super();
		this.encryptedRekuest = encryptedRekuest;
	}

	public String getEncryptedRekuest() {
		return encryptedRekuest;
	}

	public void setEncryptedRekuest(String encryptedRekuest) {
		this.encryptedRekuest = encryptedRekuest;
	}

	@Override
	public String toString() {
		return "EncryptedRekuestDto [encryptedRekuest=" + encryptedRekuest + "]";
	}
	
}
