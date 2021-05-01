package com.example.api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.api.models.Email;

@Component
public class EmailService {
	
	@Value("${services.email.host}")
	private String host;
	@Value("${services.email.port}")
	private int port;
	
	public String sendEmail(Email mail) {
		return "!email enviado para "+mail.getMailTo()+" por "+mail.getSender()+ ". \n"
				+"Titulo: "+mail.getTitle()+"\n"
				+"Mensagem: "+mail.getMenssage()+"\n"
				+"Host: "+host+"\n"
				+"Port: "+port;
	}
}
