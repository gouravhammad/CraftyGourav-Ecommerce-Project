package com.Gourav.controllers;

import java.sql.Blob;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.Gourav.DAOs.CommonDAO;
import com.Gourav.DAOs.CustomerDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Customer;

@Controller
@SessionAttributes(names= {"email","realOTP","customer","location","myCustomer"})
public class CustomerController
{
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	private DataProvider dataProvider;
	
	@RequestMapping("customerLogin")
	public ModelAndView showCustomerLoginForm()
	{
		 ModelAndView modelAndView = new ModelAndView("CustomerLogin");
		 modelAndView.addObject("customer",new Customer());
			
		 return modelAndView; 
	}
	
	@RequestMapping("customerHome")
	public ModelAndView showCustomerHome(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
		 	 modelAndView.addObject("path","home");
		 	 return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("CustomerHome");
		return modelAndView;
	}
	
	@RequestMapping("aboutHome")
	public ModelAndView showAbout()
	{	
		ModelAndView modelAndView = new ModelAndView("About");
		return modelAndView;
	}
	
	@RequestMapping("LoadCustomerDP")
	public void loadCustomerImage(@RequestParam("email") String email, HttpServletResponse response)
	{
		response.setContentType("image/*");
		Customer customer = customerDAO.getCustomerbyId(email);
		Blob blob = customer.getProfilePicture();
		
		try
		{
			byte b[] = blob.getBytes(1,(int)blob.length());
			ServletOutputStream out = response.getOutputStream();
			out.write(b);
			out.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error in LoadCustomerDP : " + ex);	
		}
	}
    
    @RequestMapping("viewCustomer")
    public ModelAndView viewCustomer(@SessionAttribute("email") String email)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
		 	 modelAndView.addObject("path","home");
		 	 return modelAndView;
		}
    	
    	Customer customer = customerDAO.getCustomerbyId(email);
    	ModelAndView modelAndView = new ModelAndView("CustomerProfile");
    	modelAndView.addObject("customer",customer);
    	
    	return modelAndView;
    }
	
	@RequestMapping("saveCustomerLogin")
    public ModelAndView verifyCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result)
    {	
		if(result.getErrorCount() > 3)
		{
			ModelAndView modelAndView = new ModelAndView("CustomerLogin");
			modelAndView.addObject("customer",customer);
			return modelAndView;
		}
	
 	    boolean status = customerDAO.verifyCustomer(customer.getEmail(),customer.getPassword());
 	   
 	    if(status)
 	    {
 		   ModelAndView modelAndView = new ModelAndView("redirect:customerHome");
 		   modelAndView.addObject("email",customer.getEmail());
 	       return modelAndView;
 	    }
 	    else
   	    {
 		   ModelAndView modelAndView = new ModelAndView("AlertMessage");
 		   modelAndView.addObject("error","INVALID USER");
 		   modelAndView.addObject("path","customerLogin");
 	       return modelAndView;
	    }
    }
	
	@RequestMapping("updateCustomer")
	public ModelAndView showCustomerUpdateForm(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
		 	 modelAndView.addObject("path","home");
		 	 return modelAndView;
		}
		
        ModelAndView modelAndView = new ModelAndView("CustomerUpdate");
        Customer customer = customerDAO.getCustomerbyId(email);
		
		List<String> cities = dataProvider.getAllCities();
    	List<String> states = dataProvider.getAllStates();
    	modelAndView.addObject("states",states);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("customer",customer);
		modelAndView.addObject("location","saveCustomerChanges");
		modelAndView.addObject("myCustomer",customer);
		
		return modelAndView; 
	}
	
	@RequestMapping("saveCustomerChanges")
	public ModelAndView saveCustomerChanges(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, @RequestParam("profilePicture") MultipartFile file, @SessionAttribute("email") String email )
	{
		if(1 < result.getErrorCount())
		{	
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					ModelAndView modelAndView = new ModelAndView("CustomerUpdate");
					modelAndView.addObject("error","Upload your Picture");
					
					List<String> cities = dataProvider.getAllCities();
			    	List<String> states = dataProvider.getAllStates();
			    	modelAndView.addObject("states",states);
					modelAndView.addObject("cities",cities);
					
					return modelAndView;
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture : " + Ex);
			}
			
			ModelAndView modelAndView = new ModelAndView("CustomerUpdate");	
			
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
			
			return modelAndView;
		}
		else
		{		
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					ModelAndView modelAndView = new ModelAndView("CustomerUpdate");
					modelAndView.addObject("error","Upload your Picture");
					
					List<String> cities = dataProvider.getAllCities();
			    	List<String> states = dataProvider.getAllStates();
			    	modelAndView.addObject("states",states);
					modelAndView.addObject("cities",cities);
					
					return modelAndView;
				}
				else
				{
					if(!(file.getContentType().contains("image/png") || file.getContentType().contains("image/jpeg")))
					{
						ModelAndView modelAndView = new ModelAndView("CustomerUpdate");
						modelAndView.addObject("error","Upload Image of JPEG/JPG/PNG type only");
						
						List<String> cities = dataProvider.getAllCities();
				    	List<String> states = dataProvider.getAllStates();
				    	modelAndView.addObject("states",states);
						modelAndView.addObject("cities",cities);
						
						return modelAndView;
					}
					
					Blob picture = BlobProxy.generateProxy(b);
					customer.setProfilePicture(picture);
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture : " + Ex);
			}
			
			customerDAO.updateCustomer(customer);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	modelAndView.addObject("error","Details Updated");
		 	modelAndView.addObject("path","customerHome");
		 	return modelAndView;
			
		}
	}
	
	@RequestMapping("customerSignup")
	public ModelAndView showCustomerSignupForm()
	{
        ModelAndView modelAndView = new ModelAndView("CustomerSignup");
		
		List<String> cities = dataProvider.getAllCities();
    	List<String> states = dataProvider.getAllStates();
    	modelAndView.addObject("states",states);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("location","customerSignup");
		modelAndView.addObject("customer",new Customer());
		
		return modelAndView; 
	}
	
	@RequestMapping("addCustomer")
	public ModelAndView addCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, @RequestParam("profilePicture") MultipartFile file)
	{	
		if(customer.getEmail() != null)
		{
			Customer c = customerDAO.getCustomerbyId(customer.getEmail());
			
			if(c != null) 
			{
				
				ModelAndView modelAndView = new ModelAndView("CustomerSignup");
				modelAndView.addObject("statusError","User already Registered with this Email");
				
				List<String> cities = dataProvider.getAllCities();
				List<String> states = dataProvider.getAllStates();
				modelAndView.addObject("states",states);
				modelAndView.addObject("cities",cities);
				
				return modelAndView;
			}
		}
		
		
		if(1 < result.getErrorCount())
		{	
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					ModelAndView modelAndView = new ModelAndView("CustomerSignup");
					modelAndView.addObject("error","Upload your Picture");
					
					List<String> cities = dataProvider.getAllCities();
			    	List<String> states = dataProvider.getAllStates();
			    	modelAndView.addObject("states",states);
					modelAndView.addObject("cities",cities);
					
					return modelAndView;
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture : " + Ex);
			}
			
			ModelAndView modelAndView = new ModelAndView("CustomerSignup");	
			
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
			
			return modelAndView;
		}
		else
		{		
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					ModelAndView modelAndView = new ModelAndView("CustomerSignup");
					modelAndView.addObject("error","Upload your Picture");
					
					List<String> cities = dataProvider.getAllCities();
			    	List<String> states = dataProvider.getAllStates();
			    	modelAndView.addObject("states",states);
					modelAndView.addObject("cities",cities);
					
					return modelAndView;
				}
				else
				{
					if(!(file.getContentType().contains("image/png") || file.getContentType().contains("image/jpeg")))
					{
						ModelAndView modelAndView = new ModelAndView("CustomerSignup");
						modelAndView.addObject("error","Upload Image of JPEG/PNG type only");
						
						List<String> cities = dataProvider.getAllCities();
				    	List<String> states = dataProvider.getAllStates();
				    	modelAndView.addObject("states",states);
						modelAndView.addObject("cities",cities);
						
						return modelAndView;
					}
					
					Blob picture = BlobProxy.generateProxy(b);
					customer.setProfilePicture(picture);
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture : " + Ex);
			}
			
			try
			{
				int otpArray[] = new int[1];
		    	
		    	commonDAO.sendMail(customer.getEmail(),otpArray);
		    	
		    	ModelAndView modelAndView = new ModelAndView("OTPFormCustomer");
		    	modelAndView.addObject("email",customer.getEmail());
		    	modelAndView.addObject("realOTP",otpArray[0]);
		    	modelAndView.addObject("customer",customer);
		    	
		    	return modelAndView;
			}
			catch(Exception Ex)
			{
				ModelAndView modelAndView = new ModelAndView("redirect:CustomerSignup");
				
				List<String> cities = dataProvider.getAllCities();
		    	List<String> states = dataProvider.getAllStates();
		    	modelAndView.addObject("states",states);
				modelAndView.addObject("cities",cities);
				
				return modelAndView;
			}
		}
	}
	
	@RequestMapping("forgotPassword")
	public ModelAndView showForgotPasswordForm()
	{
		 ModelAndView modelAndView = new ModelAndView("ForgotPassword");
		 modelAndView.addObject("customer", new Customer());
		 return modelAndView;
    }
	
	@RequestMapping("saveForgotPassword")
	public ModelAndView saveForgotPasswordDetails(@ModelAttribute("customer") Customer customer)
	{
		 String email = customer.getEmail();
		 int emailLenght = customer.getEmail().length();
		 
		 if(emailLenght < 10 || emailLenght > 50)
		 {
			 ModelAndView modelAndView = new ModelAndView("ForgotPassword");
			 modelAndView.addObject("errorEmail","Min 10 & Max 50 chars");
			 return modelAndView;
		 }
		 else
		 {
			 Customer oldCustomer = customerDAO.getCustomerbyId(email);
			 
			 if(oldCustomer == null)
			 {
				 ModelAndView modelAndView = new ModelAndView("ForgotPassword");
				 modelAndView.addObject("errorEmail","Unregistered User");
				 return modelAndView;
			 }
			 else
			 {
				 SimpleMailMessage mailMessage=new SimpleMailMessage();
				 mailMessage.setTo(email);
				 mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
				 mailMessage.setBcc(email);
			 	 mailMessage.setSubject("YOUR PASSWORD IS : ");
				 mailMessage.setText("Heyy " + oldCustomer.getUserName() + " , your password is " + oldCustomer.getPassword() + " Do not share it with anyone else. Your security is our first priority");
				 mailSender.send(mailMessage);
				
				 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	     modelAndView.addObject("error","Password Recovered! Check out your mail");
		 	     modelAndView.addObject("path","customerLogin");
		 	     return modelAndView;
			 }
		 }
    }
}
