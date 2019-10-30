package com.Gourav.DAOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Gourav.models.Cart;

@Component
public interface CartDAO
{
     public Cart addItemToCart(Cart cart) throws Exception;
     public Cart updateCartItem(Cart cart);
     public Cart getCartItemById(String email, String productId);    
     public List<Cart> getAllCartItems(String email);
     public Cart removeCartItemById(String email, String productId);
     public void removeAllCartItems(String email);
     public long getCartItemsTotal(String email);
     public long getCartItemsCount(String email);
	 public List<String> getAllCartItemsName(List<Cart> items);
	 public void removeAllCartItemsByProductId(String productId);
}
