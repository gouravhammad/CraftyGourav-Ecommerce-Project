package com.Gourav.DAOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Gourav.models.Product;

@Component
public interface ProductDAO
{
    public Product addProduct(Product product);
    public Product removeProduct(String productId);
    public List<Product> getAllProducts();
    public Product getProductbyId(String productId);
	public int getCount();
	public Product updateProduct(Product product);
	public List<Product> getSearchResult(String searchKeyword);
	public List<Product> getAllProductsByFilter(String filter, String order);
}
