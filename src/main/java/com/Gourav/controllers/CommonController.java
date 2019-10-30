package com.Gourav.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.Gourav.DAOs.CartDAO;
import com.Gourav.DAOs.CommonDAO;
import com.Gourav.DAOs.CustomerDAO;
import com.Gourav.DAOs.MyOrderDAO;
import com.Gourav.DAOs.ProductDAO;
import com.Gourav.DAOs.WishListDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Cart;
import com.Gourav.models.Customer;
import com.Gourav.models.MyOrder;
import com.Gourav.models.Product;
import com.Gourav.models.WishList;

@SessionAttributes(names= {"email","realOTP","customer"})
@Controller
public class CommonController
{
	 @Autowired
	 private CustomerDAO customerDAO;
	 
	 @Autowired
	 private CommonDAO commonDAO;
	 
	 @Autowired
	 private ProductDAO productDAO;
	 
	 @Autowired
	 private DataProvider dataProvider;
	 
	 @Autowired
	 private JavaMailSender mailSender;
	 
	 @Autowired
	 private MyOrderDAO myOrderDAO;
	 
	 @Autowired
	 private CartDAO cartDAO;
	 
	 @Autowired
	 private WishListDAO wishListDAO;
	 
	 @RequestMapping("home")
     public String showHomePage()
     {
    	 return "Home";
     }
	 
	 @RequestMapping("logoutConfirmationCustomer")
	 public ModelAndView logoutConfirmCustomer(@SessionAttribute("email") String email)
     { 
		 if(email.equalsIgnoreCase("NULL"))
		 {
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		 }
		 
		 ModelAndView modelAndView = new ModelAndView("LogoutConfirmationCustomer");
	 	 return modelAndView;
	 }
	 
	 @RequestMapping("logoutConfirmationAdmin")
	 public ModelAndView logoutConfirmAdmin(@SessionAttribute("email") String email)
     { 
		 if(email.equalsIgnoreCase("NULL"))
		 {
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		 }
		 
		 ModelAndView modelAndView = new ModelAndView("LogoutConfirmationAdmin");
	 	 return modelAndView;
	 }

     @RequestMapping("logout")
     public ModelAndView showHomeAfterLogout(HttpServletRequest request,HttpServletResponse response)
     {  
    	  ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	  modelAndView.addObject("error","Successfully Logged Out");
	 	  modelAndView.addObject("path","home");
	 	  modelAndView.addObject("email","NULL");
	 	  return modelAndView;
	 }
	 
	 @RequestMapping("resendOTP")
     public ModelAndView resendOTP(@SessionAttribute("customer") Customer customer, HttpServletRequest request)
     {
		 try
			{
				int otpArray[] = new int[1];
		    	commonDAO.sendMail(customer.getEmail(),otpArray);
		    	
		    	ModelAndView modelAndView = new ModelAndView("OTPFormCustomer");
		    	HttpSession session = request.getSession();
				session.removeAttribute("realOTP");
		    	modelAndView.addObject("realOTP",otpArray[0]);
		    	
		    	return modelAndView;
			}
			catch(Exception Ex)
			{
				ModelAndView modelAndView = new ModelAndView("redirect:CustomerSignup");
				
				return modelAndView;
			}
     }
	 
	 @RequestMapping("otpHome")
	 public String showHome(HttpServletRequest request)
	 {
		    HttpSession session = request.getSession();
		    session.removeAttribute("email");
		    session.removeAttribute("customer");
		    session.removeAttribute("realOTP");
		    
		    return "Home";
	 }
	
	@RequestMapping("checkOTPCustomer")
	public ModelAndView verifyOTPCustomer(@RequestParam("OTPCode") int OTPCode, @SessionAttribute("email") String email, @SessionAttribute("realOTP") int realOTP, @SessionAttribute("customer") Customer customer, HttpServletRequest request)
	{ 
		if(OTPCode == realOTP)
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
			mailMessage.setBcc(email);
			mailMessage.setSubject("ACCOUNT SUCCESSFULLY CREATED");
			mailMessage.setText("Account Verified of Customer! Thanks for joining us.");
			mailSender.send(mailMessage);
			
			customerDAO.addCustomer(customer);
		    
		    HttpSession session = request.getSession();
		    session.removeAttribute("email");
		    session.removeAttribute("customer");
		    session.removeAttribute("realOTP");
		    
		    ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Successfully verified!!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
		else
		{
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(email);
			mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
			mailMessage.setBcc(email);
			mailMessage.setSubject("ACCOUNT NOT VERIFIED");
			mailMessage.setText("Account Not Verified of Customer. Invalid OTP Entered! Please Register again :)");
			mailSender.send(mailMessage);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Account Not Verified! Please try again later!");
	 	    modelAndView.addObject("path","home");
	 	    return modelAndView;
		}
	}
	
	@RequestMapping("addToCart")
	public ModelAndView addToCart(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{	
		Cart cart = new Cart();
		cart.setEmail(email);
		cart.setProductId(productId);
		cart.setQuantity(1);
		
		try
		{
			cartDAO.addItemToCart(cart);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Added to Cart");
	 	    modelAndView.addObject("path","showAllProducts");
	 	    return modelAndView;
		}
		catch(Exception Ex)
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	    modelAndView.addObject("error","Already Added");
	 	    modelAndView.addObject("path","showAllProducts");
	 	    return modelAndView;
		}
	}
	
	@RequestMapping("viewCart")
	public ModelAndView showCart(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<Cart> items = cartDAO.getAllCartItems(email);
		List<String> names = cartDAO.getAllCartItemsName(items);
		
		if(items.size() == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageCart");
			modelAndView.addObject("errorEmpty","No items added to Cart");
			return modelAndView;
		}
		
		long total = cartDAO.getCartItemsTotal(email);
		long itemsCount = cartDAO.getCartItemsCount(email);
		
		ModelAndView modelAndView = new ModelAndView("Cart");
		modelAndView.addObject("items",items);
		modelAndView.addObject("total",total);
		modelAndView.addObject("itemsCount",itemsCount);
		modelAndView.addObject("names",names);
		
		return modelAndView;
	}
	
	@RequestMapping("removeCartItem")
	public ModelAndView removeItemFromCart(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{
		cartDAO.removeCartItemById(email,productId);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
 	    modelAndView.addObject("error","Item Removed from Cart");
 	    modelAndView.addObject("path","viewCart");
 	    return modelAndView;
	}
	
	@RequestMapping("clearCart")
	public ModelAndView clearCart(@SessionAttribute("email") String email)
	{
		cartDAO.removeAllCartItems(email);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
 	    modelAndView.addObject("error","All items Removed");
 	    modelAndView.addObject("path","viewCart");
 	    return modelAndView;
	}
	
	@RequestMapping("changeQuantity")
	public ModelAndView changeQuantity(@SessionAttribute("email") String email, @RequestParam("productId") String productId, @RequestParam("quantity") int quantity)
	{
		Cart cart = cartDAO.getCartItemById(email,productId);
		cart.setQuantity(quantity);
		
		cartDAO.updateCartItem(cart);
		
		ModelAndView modelAndView = new ModelAndView("redirect:viewCart");
 	    return modelAndView;
	}
	
	@RequestMapping("buyNowSingle")
	public ModelAndView buyNowSingle(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{ 
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<String> paymentModes = dataProvider.getAllPaymentModes();
		List<String> cities = dataProvider.getAllCities();
		List<String> states = dataProvider.getAllStates();
		
		Customer customer = customerDAO.getCustomerbyId(email);
		Product product = productDAO.getProductbyId(productId);
		long total = (long) product.getProductPrice();
		
		MyOrder order = new MyOrder();
		order.setQuantity(1);
		order.setProductId(productId);
		order.setMobileNumber(customer.getMobileNumber());
		order.setRecipientAddress(customer.getAddress());
		order.setRecipientName(customer.getUserName());
		order.setRecipientCity(customer.getCity());
		order.setRecipientState(customer.getState());
		order.setOrderStatus("Ordered");
		order.setTotal(total);
		
		ModelAndView modelAndView = new ModelAndView("OrderSingle");
		modelAndView.addObject("order",order);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("states",states);
		modelAndView.addObject("paymentModes",paymentModes);
		
 	    return modelAndView;
	}
	
	@RequestMapping("buyNowAll")
	public ModelAndView buyNowAll(@SessionAttribute("email") String email, @RequestParam("total") long total)
	{ 
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<String> paymentModes = dataProvider.getAllPaymentModes();
		List<String> cities = dataProvider.getAllCities();
		List<String> states = dataProvider.getAllStates();
		
		Customer customer = customerDAO.getCustomerbyId(email);	
		MyOrder order = new MyOrder();
		order.setMobileNumber(customer.getMobileNumber());
		order.setRecipientAddress(customer.getAddress());
		order.setRecipientName(customer.getUserName());
		order.setRecipientCity(customer.getCity());
		order.setRecipientState(customer.getState());
		order.setOrderStatus("Ordered");
		order.setTotal(total);
		
		ModelAndView modelAndView = new ModelAndView("OrderAll");
		modelAndView.addObject("order",order);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("states",states);
		modelAndView.addObject("paymentModes",paymentModes);
		
 	    return modelAndView;
	}
	
	@RequestMapping("changeQuantitySingle")
	public ModelAndView changeQuantitySingle(@SessionAttribute("email") String email, @RequestParam("productId") String productId, @RequestParam("quantity") int quantity)
	{
		List<String> paymentModes = dataProvider.getAllPaymentModes();
		List<String> cities = dataProvider.getAllCities();
		List<String> states = dataProvider.getAllStates();
		
		Customer customer = customerDAO.getCustomerbyId(email);
		Product product = productDAO.getProductbyId(productId);
		long productPrice = (long) product.getProductPrice();
		long total = productPrice * quantity;
		
		MyOrder order = new MyOrder();
		order.setQuantity(quantity);
		order.setProductId(productId);
		order.setMobileNumber(customer.getMobileNumber());
		order.setRecipientAddress(customer.getAddress());
		order.setRecipientName(customer.getUserName());
		order.setRecipientCity(customer.getCity());
		order.setRecipientState(customer.getState());
		order.setOrderStatus("Ordered");
		order.setTotal(total);
		
		ModelAndView modelAndView = new ModelAndView("OrderSingle");
		modelAndView.addObject("order",order);
		modelAndView.addObject("cities",cities);
		modelAndView.addObject("states",states);
		modelAndView.addObject("paymentModes",paymentModes);
		
 	    return modelAndView;
	}
	
	@RequestMapping("placeOrderSingle")
	public ModelAndView placeOrderSingle(@SessionAttribute("email") String email, @Valid @ModelAttribute("order") MyOrder order, BindingResult result)
	{
		if(result.hasErrors())
		{
			List<String> paymentModes = dataProvider.getAllPaymentModes();
			List<String> cities = dataProvider.getAllCities();
			List<String> states = dataProvider.getAllStates();
			
			ModelAndView modelAndView = new ModelAndView("OrderSingle");
			modelAndView.addObject("cities",cities);
			modelAndView.addObject("states",states);
			modelAndView.addObject("paymentModes",paymentModes);
			
			return modelAndView;
		}
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
	    
		long orderId = myOrderDAO.getOrderId();
		order.setOrderId(orderId);
		order.setDateAndTime(sqlTime);
		order.setEmail(email);
		
		myOrderDAO.addOrder(order);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
		mailMessage.setBcc(email);
		mailMessage.setSubject("ORDER PLACED");
		mailMessage.setText("Your order has been placed Successfully. Our team will contact you soon... Continue Shopping :)");
		mailSender.send(mailMessage);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","Order Successfully Placed");
		modelAndView.addObject("path","customerHome");
		
		return modelAndView;
	}
	
	@RequestMapping("placeOrderAll")
	public ModelAndView placeOrderAll(@SessionAttribute("email") String email, @Valid @ModelAttribute("order") MyOrder order, BindingResult result)
	{
		if(result.getErrorCount() > 1)
		{
			List<String> paymentModes = dataProvider.getAllPaymentModes();
			List<String> cities = dataProvider.getAllCities();
			List<String> states = dataProvider.getAllStates();
			
			ModelAndView modelAndView = new ModelAndView("OrderAll");
			modelAndView.addObject("cities",cities);
			modelAndView.addObject("states",states);
			modelAndView.addObject("paymentModes",paymentModes);
			
			return modelAndView;
		}
		
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
	    
		List<Cart> items = cartDAO.getAllCartItems(email);
		
		for(Cart item:items)
		{
			Product product = productDAO.getProductbyId(item.getProductId());
			long productPrice = (long) product.getProductPrice();
			long total = (productPrice * item.getQuantity());
			
			order.setOrderId(myOrderDAO.getOrderId());
			order.setProductId(item.getProductId());
			order.setQuantity(item.getQuantity());
			order.setDateAndTime(sqlTime);
			order.setOrderStatus("Ordered");
			order.setEmail(email);
			order.setTotal(total);
			
			myOrderDAO.addOrder(order);
		}
		
		cartDAO.removeAllCartItems(email);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
		mailMessage.setBcc(email);
		mailMessage.setSubject("ORDER PLACED");
		mailMessage.setText("Your order has been placed Successfully. Our team will contact you soon... Continue Shopping :)");
		mailSender.send(mailMessage);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","Order Successfully Placed");
		modelAndView.addObject("path","customerHome");
		
		return modelAndView;
	}
	
	@RequestMapping("cancelOrderConfirmation")
	public ModelAndView showCancelOrderConfirmation(@SessionAttribute("email") String email, @RequestParam("orderId") String orderId)
	{ 
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("OrderCancelConfirmation");
		modelAndView.addObject("orderId",orderId);
		
		return modelAndView;
	}
	
	@RequestMapping("cancelOrder")
	public ModelAndView cancelOrder(@SessionAttribute("email") String email, @RequestParam("orderId") long orderId)
	{
		MyOrder order = myOrderDAO.getOrderById(orderId);
		order.setOrderStatus("Canceled");
		myOrderDAO.updateOrder(order);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setFrom("CRAFTYGOURAV<gourav.hammad.sdbc@gmail.com>");
		mailMessage.setBcc(email);
		mailMessage.setSubject("ORDER CANCELED");
		mailMessage.setText("Your order has been Canceled. Continue Shopping and see other products too :)");
		mailSender.send(mailMessage);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","Order Canceled");
		modelAndView.addObject("path","viewMyOrders");
		
		return modelAndView;
	}
	
	@RequestMapping("viewMyOrders")
	public ModelAndView viewMyOrders(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<MyOrder> canceledOrders = myOrderDAO.getAllOrders(email,"Canceled");
		List<MyOrder> orderedOrders = myOrderDAO.getAllOrders(email,"Ordered");
		List<MyOrder> deliveredOrders = myOrderDAO.getAllOrders(email,"Delivered");
		
		int cancelSize = canceledOrders.size();
		int orderedSize = orderedOrders.size();
		int deliveredSize = deliveredOrders.size();
		
		if(cancelSize == 0 && orderedSize == 0 && deliveredSize == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageMyOrder");
			modelAndView.addObject("errorEmpty","No Order Placed");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("MyOrders");
		modelAndView.addObject("canceledOrders",canceledOrders);
		modelAndView.addObject("orderedOrders",orderedOrders);
		modelAndView.addObject("deliveredOrders",deliveredOrders);
		
		return modelAndView;
	}
	
	@RequestMapping("searchProductAdmin")
	public ModelAndView searchProductAdmin(@SessionAttribute("email") String email, @RequestParam("searchKeyword") String searchKeyword)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<Product> products = productDAO.getSearchResult(searchKeyword);
		
		if(products.size() == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageSearchResultAdmin");
			modelAndView.addObject("errorSearchResult","Sorry, no results found! Please check the spelling or try searching for something else");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("ManageProducts");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("searchProduct")
	public ModelAndView searchProduct(@SessionAttribute("email") String email, @RequestParam("searchKeyword") String searchKeyword)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<Product> products = productDAO.getSearchResult(searchKeyword);
		
		if(products.size() == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageSearchResult");
			modelAndView.addObject("errorSearchResult","Sorry, no results found! Please check the spelling or try searching for something else");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("AllProducts");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("searchProductHome")
	public ModelAndView searchProductHome(@RequestParam("searchKeyword") String searchKeyword)
	{		
		List<Product> products = productDAO.getSearchResult(searchKeyword);
		
		if(products.size() == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageSearchResultHome");
			modelAndView.addObject("errorSearchResult","Sorry, no results found! Please check the spelling or try searching for something else");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("AllProductsHome");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("loginFirst")
	public ModelAndView showLoginFirst()
	{ 
		 ModelAndView modelAndView = new ModelAndView("CustomerLogin");
		 modelAndView.addObject("customer",new Customer());
			
		 return modelAndView; 
	}
	
	@RequestMapping("addToWishList")
	public ModelAndView addToWishList(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{ 
		WishList wishList = new WishList();
		wishList.setProductId(productId);
		wishList.setEmail(email);
		
		try
		{
			wishListDAO.addWishlistItem(wishList);
			
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Added to Wishlist");
			modelAndView.addObject("path","showAllProducts");
			return modelAndView;
		}
		catch(Exception Ex)
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Already added to Wishlist");
			modelAndView.addObject("path","showAllProducts");
			return modelAndView;
		}
	}
	
	@RequestMapping("showWishList")
	public ModelAndView showWishList(@SessionAttribute("email") String email)
	{ 
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
		List<WishList> wishListItems = wishListDAO.getAllWishListItemsById(email);
		List<Product> products = wishListDAO.getAllWishListItemsDetail(wishListItems);
		long totalCount = wishListDAO.getWishListItemsCount(email);
		
		if(products.size() == 0)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageWishList");
			modelAndView.addObject("errorWishList","No items added to Wishlist");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("WishList");
		modelAndView.addObject("products",products);
		modelAndView.addObject("totalCount",totalCount);
			
		return modelAndView; 
	}
	
	@RequestMapping("removeFromWishList")
	public ModelAndView removeItemFromWishList(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{ 
		WishList item = new WishList();
		item.setEmail(email);
		item.setProductId(productId);
		
		wishListDAO.removeWishListItemById(item);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","Item Removed from WishList");
		modelAndView.addObject("path","showWishList");
	
		return modelAndView;
	}
	
	@RequestMapping("clearWishList")
	public ModelAndView clearWishList(@SessionAttribute("email") String email)
	{ 
		wishListDAO.removeAllWishListItemsById(email);
		
		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		modelAndView.addObject("error","WishList Cleared");
		modelAndView.addObject("path","showWishList");
	
		return modelAndView;
	}
	
	@RequestMapping("showDeleteAccountConfirmation")
    public ModelAndView showDeleteAccountConfirmation(@SessionAttribute("email") String email)
    {
		if(email.equalsIgnoreCase("NULL"))
		{
			ModelAndView modelAndView = new ModelAndView("AlertMessage");
			modelAndView.addObject("error","Please Login to continue");
			modelAndView.addObject("path","home");
			return modelAndView;
		}
		
    	ModelAndView modelAndView = new ModelAndView("DeleteAccountConfirmation");
    	
	 	return modelAndView;
    }
	
	@RequestMapping("deleteAccount")
    public ModelAndView deleteAccount(@SessionAttribute("email") String email)
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
		mailMessage.setText("Your account has been deleted as per your request. Join us soon :)");
		mailSender.send(mailMessage);
    	
    	ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	modelAndView.addObject("error","Account Deleted. Comeback soon :)");
	    modelAndView.addObject("path","home");
	    
	 	return modelAndView;
    }
	
}
