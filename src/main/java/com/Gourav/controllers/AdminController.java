package com.Gourav.controllers;


import java.util.List;

import javax.validation.Valid;
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
import org.springframework.web.servlet.ModelAndView;
import com.Gourav.DAOs.AdminDAO;
import com.Gourav.DAOs.CartDAO;
import com.Gourav.DAOs.CustomerDAO;
import com.Gourav.DAOs.MyOrderDAO;
import com.Gourav.DAOs.ProductDAO;
import com.Gourav.DAOs.WishListDAO;
import com.Gourav.models.Admin;
import com.Gourav.models.Customer;
import com.Gourav.models.MyOrder;
import com.Gourav.models.Product;

@Controller
@SessionAttributes(names= {"email"})
public class AdminController
{	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private MyOrderDAO myOrderDAO;
	
	@Autowired
	private WishListDAO wishListDAO;

    @RequestMapping("adminLogin")
	public ModelAndView showAdminForm()
	{	
		ModelAndView modelAndView = new ModelAndView("AdminLogin");
		modelAndView.addObject("admin",new Admin());
		
		return modelAndView; 
	}
    
    @RequestMapping("adminHome")
	public ModelAndView showAdminHome(@SessionAttribute("email") String email)
	{	
    	 if(email.equalsIgnoreCase("NULL"))
		 {
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		 }
    	 
		 ModelAndView modelAndView = new ModelAndView("AdminHome");
		 return modelAndView; 
	}
    
    @RequestMapping("saveAdmin")
	public ModelAndView verifyAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result)
	{
    	if(result.hasErrors())
    	{
    		ModelAndView modelAndView = new ModelAndView("AdminLogin");
    		return modelAndView; 
    	}
    	
    	boolean status = adminDAO.verifyAdmin(admin);
    	
    	if(status)
    	{
    		ModelAndView modelAndView = new ModelAndView("redirect:adminHome");
    		modelAndView.addObject("email",admin.getEmail());
    		return modelAndView; 
    	}
    	else
    	{
    	   ModelAndView modelAndView = new ModelAndView("AlertMessage");
   		   modelAndView.addObject("error","Unauthorised Access");
   		   modelAndView.addObject("path","home");
   	       return modelAndView;
    	}
	}
    
    @RequestMapping("manageCustomers")
    public ModelAndView manageAllCustomer(@SessionAttribute("email") String email)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
		 	 modelAndView.addObject("path","home");
		 	 return modelAndView;
		}
    	
    	List<Customer> customers = customerDAO.getAllCustomers();
    	ModelAndView modelAndView = new ModelAndView("ManageCustomers");
    	modelAndView.addObject("customers",customers);
    	
    	return modelAndView;
    }
    
    @RequestMapping("showRemoveCustomerConfirmation")
    public ModelAndView manageAllCustomer(@SessionAttribute("email") String email, @RequestParam("delEmail") String customerEmail)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
		 	 modelAndView.addObject("path","home");
		 	 return modelAndView;
		}
    	
    	ModelAndView modelAndView = new ModelAndView("DeleteAccountConfirmationAdmin");
    	modelAndView.addObject("delEmail",customerEmail);
    	
    	return modelAndView;
    }
    
    @RequestMapping("removeCustomer")
    public ModelAndView removeCustomer(@RequestParam("delEmail") String email)
    {
    	
    	cartDAO.removeAllCartItems(email);
    	customerDAO.removeCustomer(email);
    	wishListDAO.removeAllWishListItemsById(email);
    	List<MyOrder> orders = myOrderDAO.getAllOrders(email,"Ordered");
    	
    	for(MyOrder order:orders)
    	{
    		order.setOrderStatus("Canceled");
    		myOrderDAO.updateOrder(order);
    	}
    	
    	SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
		mailMessage.setBcc(email);
		mailMessage.setSubject("ACCOUNT DELETED");
		mailMessage.setText("Your account has been deleted by our team. You did not followed the guidelines. Next time don't do such things. Stay happy :)");
		mailSender.send(mailMessage);
    	
    	ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	modelAndView.addObject("error","Customer Removed");
	    modelAndView.addObject("path","adminHome");
	    
	 	return modelAndView;
    }
    
    @RequestMapping("manageProducts")
	public ModelAndView manageAllProducts(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
		
		List<Product> products = productDAO.getAllProducts();
		ModelAndView modelAndView = new ModelAndView("ManageProducts");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
    
    @RequestMapping("manageOrders")
   	public ModelAndView manageOrders(@SessionAttribute("email") String email)
   	{
    	if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<MyOrder> canceledOrders = myOrderDAO.getAllOrdersAdmin("Canceled");
		List<MyOrder> orderedOrders = myOrderDAO.getAllOrdersAdmin("Ordered");
		List<MyOrder> deliveredOrders = myOrderDAO.getAllOrdersAdmin("Delivered");
		
		int cancelSize = canceledOrders.size();
		int orderedSize = orderedOrders.size();
		int deliveredSize = deliveredOrders.size();
		
		if(cancelSize == 0 && orderedSize == 0 && deliveredSize == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageAllOrders");
			modelAndView.addObject("errorEmpty","No Order Placed");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("ManageOrders");
		modelAndView.addObject("canceledOrders",canceledOrders);
		modelAndView.addObject("orderedOrders",orderedOrders);
		modelAndView.addObject("deliveredOrders",deliveredOrders);
		
		return modelAndView;
   	}
    
    @RequestMapping("showSetOrderStatus")
   	public ModelAndView showSetOrderStatus(@SessionAttribute("email") String email, @RequestParam("orderId") long orderId)
   	{
   		if(email.equalsIgnoreCase("NULL"))
   		{
   			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
   		 	 modelAndView.addObject("error","Please Login to continue");
   			 modelAndView.addObject("path","home");
   			 return modelAndView;
   		}
   		
   		ModelAndView modelAndView = new ModelAndView("SetOrderStatus");
   		modelAndView.addObject("orderId",orderId);
   		
   		return modelAndView;
   	}
    
    @RequestMapping("setOrderStatus")
	public ModelAndView setOrderStatus(@SessionAttribute("email") String email, @RequestParam("status") String status, @RequestParam("orderId") long orderId)
	{
		MyOrder order = myOrderDAO.getOrderById(orderId);
		order.setOrderStatus(status);
		myOrderDAO.updateOrder(order);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
		mailMessage.setBcc(email);
		mailMessage.setSubject("ORDER " + status);
		mailMessage.setText("Your order has been " + status + " by our Team. Continue shopping and see other products too :)");
		mailSender.send(mailMessage);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","Order Status Changed");
		modelAndView.addObject("path","manageOrders");
		
		return modelAndView;
	}
    
}
