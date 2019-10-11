package com.example.fullstack41.exceptions;
import java.awt.TrayIcon.MessageType;

public class FieldValidationError {

	private String field;
    private String message;
    private MessageType type;
	public String getFiled() {
		return field;
	}
	public void setField(String filed) {
		this.field = filed;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
    
    
    
    
	
}
