package com.Gourav.DAOs;

import org.springframework.stereotype.Component;

@Component
public interface CommonDAO 
{
	public void sendMail(String email,int otpArray[]);
	public int getOTP();	
}
