package com.Gourav.DAOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Gourav.models.Product;
import com.Gourav.models.WishList;

@Component
public interface WishListDAO
{
     public WishList addWishlistItem(WishList item) throws Exception;
     public WishList removeWishListItemById(WishList item);
     public void removeAllWishListItemsById(String email);
     public List<WishList> getAllWishListItemsById(String email);
     public long getWishListItemsCount(String email);
     public List<Product> getAllWishListItemsDetail(List<WishList> wishListItems);
	 public void removeAllWishListItemsByProductId(String productId);
} 
