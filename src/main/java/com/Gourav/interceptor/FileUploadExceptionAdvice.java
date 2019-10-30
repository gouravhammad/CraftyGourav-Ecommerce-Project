package com.Gourav.interceptor;

import org.springframework.web.servlet.ModelAndView;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Customer;
import com.Gourav.models.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttribute;

@ControllerAdvice
public class FileUploadExceptionAdvice
{
	@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(MaxUploadSizeExceededException exc, HttpServletRequest request, HttpServletResponse response, @SessionAttribute("location") String location) 
    {
    	if(location.equals("customerSignup"))
    	{
	    	Customer customer = new Customer();
			ModelAndView modelAndView = new ModelAndView("CustomerSignup");
			modelAndView.addObject("customer",customer);
			modelAndView.addObject("error","Upload Picture upto 3MB size");
			
			DataProvider dataProvider = new DataProvider();
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
			
			return modelAndView;
    	}
    	else if(location.equals("addProduct"))
    	{
			ModelAndView modelAndView = new ModelAndView("AddProduct");
	    	Product product = new Product();
	    	product.setProductId("P"+request.getSession().getAttribute("key"));
	    	
			modelAndView.addObject("product",product);
			modelAndView.addObject("error","Upload Picture upto 3MB size");
			
			DataProvider dataProvider = new DataProvider();
			List<String> categories = dataProvider.getAllCategories();
	    	modelAndView.addObject("categories",categories);
	    	
			return modelAndView;
       	}
    	else if(location.equals("addProductChanges"))
    	{
    		ModelAndView modelAndView = new ModelAndView("UpdateProduct");
	    	Product product = (Product) request.getSession().getAttribute("myProduct");
	    	
			modelAndView.addObject("product",product);
			modelAndView.addObject("error","Upload Picture upto 3MB size");
			
			DataProvider dataProvider = new DataProvider();
			List<String> categories = dataProvider.getAllCategories();
	    	modelAndView.addObject("categories",categories);
	    	
	    	return modelAndView;
    	}
    	else if(location.equals("saveCustomerChanges"))
    	{
    		Customer customer = (Customer) request.getSession().getAttribute("myCustomer");
    		
    		ModelAndView modelAndView = new ModelAndView("CustomerUpdate");
			modelAndView.addObject("customer",customer);
			modelAndView.addObject("error","Upload Picture upto 3MB size");
			
			DataProvider dataProvider = new DataProvider();
			List<String> cities = dataProvider.getAllCities();
	    	List<String> states = dataProvider.getAllStates();
	    	modelAndView.addObject("states",states);
			modelAndView.addObject("cities",cities);
			
			return modelAndView;
    	}
    	else
    	{
    		ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	modelAndView.addObject("error","Invalid Input");
		 	modelAndView.addObject("path","home");
		    return modelAndView;
    	}
    }
    
    @ExceptionHandler(ServletRequestBindingException.class)
    public ModelAndView showHTTPErrorPage()
    {
    	ModelAndView modelAndView = new ModelAndView("Home");
	    return modelAndView;
	}
}