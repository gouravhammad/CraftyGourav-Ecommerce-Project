package com.Gourav.DAOImplementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAOs.ProductDAO;
import com.Gourav.DAOs.WishListDAO;
import com.Gourav.models.Cart;
import com.Gourav.models.Product;
import com.Gourav.models.WishList;

@Component
public class WishListDAOImplementation implements WishListDAO
{ 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public WishList addWishlistItem(WishList item) throws Exception
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(item);
		tr.commit();
		session.close();
		
		return item;
	}

	@Override
	public WishList removeWishListItemById(WishList item)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();		
		session.remove(item);
		tr.commit();
		session.close();
		
		return item;
	}

	@Override
	public void removeAllWishListItemsById(String email)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "from WishList where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	List<WishList> items = hql.getResultList();
	    	
	    	for(WishList item:items)
			{
				session.remove(item);
			}
			
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove All WishList items Error : " + ex);
	    }
		
	    tr.commit();
	    session.close();
	}

	@Override
	public List<WishList> getAllWishListItemsById(String email) 
	{
		Session session = sessionFactory.openSession();
		
		String query = "from WishList where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	List<WishList> items = hql.getResultList();
			session.close();
			
			return items;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get All items WishList Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public long getWishListItemsCount(String email)
	{
		Session session = sessionFactory.openSession();
		
		String query = "Select count(email) from WishList where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	long count = (long) hql.getSingleResult();
			session.close();
			
			return count;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("WishList Items Count Error : " + ex);
	    }
	    
	    return 0;
	}

	@Override
	public List<Product> getAllWishListItemsDetail(List<WishList> wishListItems)
	{
		List<Product> items = new ArrayList<Product>();
		
		for(WishList wishListItem:wishListItems)
		{
			Product product = productDAO.getProductbyId(wishListItem.getProductId());
			items.add(product);
		}
		
		return items;
	}

	@Override
	public void removeAllWishListItemsByProductId(String productId)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "from WishList where productId = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",productId);
	    
	    try
	    {
	    	List<WishList> items = hql.getResultList();
	    	
	    	for(WishList item:items)
			{
				session.remove(item);
			}
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove All WishList items By ProductID Error : " + ex);
	    }
		
	    tr.commit();
	    session.close();	
	}
}
