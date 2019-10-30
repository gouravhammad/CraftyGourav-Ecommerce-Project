package com.Gourav.DAOImplementations;

import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.Gourav.DAOs.CommonDAO;
import com.Gourav.models.OTP;


@Component
public class CommonDAOImplementation implements CommonDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void sendMail(String email,int otpArray[])
	{
		int otp = getOTP();
		
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("OTP VERIFICATION");
		mailMessage.setText("Your OTP Code is : "+ otp + "\nEnter this code to verify your account! \nDo not share this with anyone else!!!");
		mailSender.send(mailMessage);
		
		otpArray[0] = otp;
	}
	
	@Override
	public int getOTP()
	{
		Session session = sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(OTP.class);
	    
	    List<OTP> otp = criteria.list();
	    
	    int otpNumber = 0;
	    
	    for(OTP o:otp)
	    {
	    	otpNumber = o.getOTPNumber();
	    }
	     
	    Transaction tr = session.beginTransaction();
	    
	    Random r = new Random();
		int randomNumber = r.nextInt(436251 + 1) + 216341;
        
	    String query = "update OTP set OTPNumber = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",randomNumber);
	    
	    try
	    {
	    	hql.executeUpdate();
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("OTP Fetching Error : " + ex);
	    }
	    
	    tr.commit();
	    session.close();
	    
		return otpNumber;
	}
		
}
