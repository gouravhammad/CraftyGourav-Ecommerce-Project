package com.Gourav.controllers;

import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.Gourav.DAOs.CartDAO;
import com.Gourav.DAOs.MyOrderDAO;
import com.Gourav.DAOs.ProductDAO;
import com.Gourav.DAOs.WishListDAO;
import com.Gourav.Utility.DataProvider;
import com.Gourav.models.Product;

@Controller
@SessionAttributes(names= {"email","location","key","myProduct"})
public class ProductController
{
	@Autowired
	private DataProvider dataProvider;
	
	@Autowired
	private WishListDAO wishListDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private MyOrderDAO myOrderDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("LoadProductImage")
	public void loadCustomerImage(@RequestParam("productId") String productId, HttpServletResponse response)
	{
		response.setContentType("image/*");
		Product product = productDAO.getProductbyId(productId);
		Blob blob = product.getProductPicture();		
		try
		{
			byte b[] = blob.getBytes(1,(int)blob.length());
			ServletOutputStream out = response.getOutputStream();
			out.write(b);
			out.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error in Load Product Picture : " + ex);	
		}
	}
	
	@RequestMapping("showAllProducts")
	public ModelAndView showAllProducts(@SessionAttribute("email") String email)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
		
		List<Product> products = productDAO.getAllProducts();
		ModelAndView modelAndView = new ModelAndView("AllProducts");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("showAllFilteredProducts")
	public ModelAndView showAllFilteredProducts(@SessionAttribute("email") String email, @RequestParam("filter") String filter, @RequestParam("order") String order)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
		
		List<Product> products = productDAO.getAllProductsByFilter(filter,order);
		ModelAndView modelAndView = new ModelAndView("AllProducts");
		modelAndView.addObject("products",products);
		
		return modelAndView;
	}
	
	@RequestMapping("showAllProductsHome")
	public ModelAndView showAllProductsHome()
	{		
		List<Product> products = productDAO.getAllProducts();
		ModelAndView modelAndView = new ModelAndView("AllProductsHome");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("showAllFilteredProductsHome")
	public ModelAndView showAllFilteredProductsHome(@RequestParam("filter") String filter, @RequestParam("order") String order)
	{		
		List<Product> products = productDAO.getAllProductsByFilter(filter,order);
		ModelAndView modelAndView = new ModelAndView("AllProductsHome");
		modelAndView.addObject("products",products);
		
		return modelAndView;
	}
	
	@RequestMapping("productDetails")
	public ModelAndView showProductDetails(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
		
		Product product = productDAO.getProductbyId(productId);
		
		if(product == null)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageProductDetails");
			modelAndView.addObject("errorEmpty","Sorry this product has been Removed");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("ProductDetails");
		modelAndView.addObject("product",product);
		return modelAndView;
	}
	
	@RequestMapping("productDetailsHome")
	public ModelAndView showProductDetailsHome(@RequestParam("productId") String productId)
	{		
		Product product = productDAO.getProductbyId(productId);
		ModelAndView modelAndView = new ModelAndView("ProductDetailsHome");
		modelAndView.addObject("product",product);
		return modelAndView;
	}
	
	@RequestMapping("productDetailsAdmin")
	public ModelAndView showProductDetailsAdmin(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
	{
		if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
		
		Product product = productDAO.getProductbyId(productId);
		
		if(product == null)
		{
			ModelAndView modelAndView = new ModelAndView("EmptyMessageProductDetailsAdmin");
			modelAndView.addObject("errorEmpty","Sorry this product has been Removed");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("ProductDetailsAdmin");
		modelAndView.addObject("product",product);
		return modelAndView;
	}
	
    @RequestMapping("showAddProduct")
    public ModelAndView showAddProductForm(@SessionAttribute("email") String email)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
    	
    	List<String> categories = dataProvider.getAllCategories();
    	
    	Product product = new Product();
    	product.setProductId("P"+productDAO.getCount());
    	
    	ModelAndView modelAndView = new ModelAndView("AddProduct");
    	modelAndView.addObject("product",product);  	
    	modelAndView.addObject("categories",categories);
    	modelAndView.addObject("location","addProduct");
    	modelAndView.addObject("key",productDAO.getCount());
    
     	return modelAndView;
    }
    
    @RequestMapping("addProduct")
    public ModelAndView addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("productPicture") MultipartFile file)
    {
        if(1 < result.getErrorCount())
		{	
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					List<String> categories = dataProvider.getAllCategories();	
		        	ModelAndView modelAndView = new ModelAndView("AddProduct");
		        	modelAndView.addObject("categories",categories);
		        	modelAndView.addObject("error","Upload your Picture");
		        
		         	return modelAndView;
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture Product : " + Ex);
			}
			
			List<String> categories = dataProvider.getAllCategories();	
        	ModelAndView modelAndView = new ModelAndView("AddProduct");
        	modelAndView.addObject("categories",categories);
        
         	return modelAndView;
		}
        
        try
		{
			byte b[] = file.getBytes();
			
			if(b.length == 0)
			{
				List<String> categories = dataProvider.getAllCategories();	
	        	ModelAndView modelAndView = new ModelAndView("AddProduct");
	        	modelAndView.addObject("categories",categories);
	        	modelAndView.addObject("error","Upload your Picture");
	        
	         	return modelAndView;
			}
			else
			{
				Blob picture = BlobProxy.generateProxy(b);
				product.setProductPicture(picture);
			}
		}
		catch(Exception Ex)
		{
			System.out.println("Error in Uploading Picture Product : " + Ex);
		}
        
        productDAO.addProduct(product);

		ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	modelAndView.addObject("error","Product Added");
	    modelAndView.addObject("path","adminHome");
	    
	    return modelAndView;
    }
    
    @RequestMapping("showRemoveProductConfirmation")
    public ModelAndView showRemoveProductConfirmation(@SessionAttribute("email") String email, @RequestParam("productId") String productId)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
    	
  	    ModelAndView modelAndView = new ModelAndView("RemoveProductConfirmation");
	    modelAndView.addObject("productId",productId);
	   
	    return modelAndView;
    }

    @RequestMapping("removeProduct")
    public ModelAndView removeProduct(@RequestParam("productId") String productId)
    {
  	  	productDAO.removeProduct(productId);
  	  	cartDAO.removeAllCartItemsByProductId(productId);
  	  	wishListDAO.removeAllWishListItemsByProductId(productId);
  	  	myOrderDAO.updateAllOrdersByProductId(productId);
  	  	
  	    ModelAndView modelAndView = new ModelAndView("AlertMessage");
	    modelAndView.addObject("error","Product Removed");
	    modelAndView.addObject("path","manageProducts");
	    return modelAndView;
    }
    
    @RequestMapping("updateProduct")
    public ModelAndView updateProduct(@RequestParam("productId") String productId, @SessionAttribute("email") String email)
    {
    	if(email.equalsIgnoreCase("NULL"))
		{
			 ModelAndView modelAndView = new ModelAndView("AlertMessage");
		 	 modelAndView.addObject("error","Please Login to continue");
			 modelAndView.addObject("path","home");
			 return modelAndView;
		}
    	
    	List<String> categories = dataProvider.getAllCategories();
    	Product product = productDAO.getProductbyId(productId);
    	
  	    ModelAndView modelAndView = new ModelAndView("UpdateProduct");
	    modelAndView.addObject("product",product);
	    modelAndView.addObject("myProduct",product);
	    modelAndView.addObject("categories",categories);
	    modelAndView.addObject("location","addProductChanges");
	    
	    return modelAndView;
    }
    
    @RequestMapping("addProductChanges")
    public ModelAndView addProductChanges(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("productPicture") MultipartFile file)
    {
        if(1 < result.getErrorCount())
		{	
			try
			{
				byte b[] = file.getBytes();
				
				if(b.length == 0)
				{
					List<String> categories = dataProvider.getAllCategories();	
		        	ModelAndView modelAndView = new ModelAndView("UpdateProduct");
		        	modelAndView.addObject("categories",categories);
		        	modelAndView.addObject("error","Upload your Picture");
		        
		         	return modelAndView;
				}
			}
			catch(Exception Ex)
			{
				System.out.println("Error in Uploading Picture Product : " + Ex);
			}
			
			List<String> categories = dataProvider.getAllCategories();	
        	ModelAndView modelAndView = new ModelAndView("UpdateProduct");
        	modelAndView.addObject("categories",categories);
        
         	return modelAndView;
		}
        
        try
		{
			byte b[] = file.getBytes();
			
			if(b.length == 0)
			{
				List<String> categories = dataProvider.getAllCategories();	
	        	ModelAndView modelAndView = new ModelAndView("UpdateProduct");
	        	modelAndView.addObject("categories",categories);
	        	modelAndView.addObject("error","Upload your Picture");
	        
	         	return modelAndView;
			}
			else
			{
				Blob picture = BlobProxy.generateProxy(b);
				product.setProductPicture(picture);
			}
		}
		catch(Exception Ex)
		{
			System.out.println("Error in Uploading Picture Product : " + Ex);
		}
        
        productDAO.updateProduct(product);

		ModelAndView modelAndView = new ModelAndView("AlertMessage");
	 	modelAndView.addObject("error","Product Updated");
	    modelAndView.addObject("path","manageProducts");
	    
	    return modelAndView;
    }
}
