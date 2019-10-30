package com.Gourav.DAOImplementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAOs.CartDAO;
import com.Gourav.DAOs.ProductDAO;
import com.Gourav.models.Cart;
import com.Gourav.models.Product;
import com.Gourav.models.WishList;

@Component
public class CartDAOImplementation implements CartDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Cart addItemToCart(Cart cart) throws Exception
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(cart);
		tr.commit();
		session.close();
		
		return cart;
	}
	

	@Override
	public Cart updateCartItem(Cart cart)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(cart);
		tr.commit();
		session.close();
		
		return cart;
	}

	@Override
	public Cart getCartItemById(String email, String productId)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from Cart where email = :x and productId = :y";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    hql.setParameter("y",productId);
	    
	    try
	    {
	    	Cart cart = (Cart) hql.getSingleResult();
			session.close();
			
			return cart;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get Single Cart Item Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public List<Cart> getAllCartItems(String email)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from Cart where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	List<Cart> items = hql.getResultList();
			session.close();
			
			return items;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get All items Cart Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public Cart removeCartItemById(String email, String productId)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "from Cart where email = :x and productId = :y";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    hql.setParameter("y",productId);
	    
	    try
	    {
	    	Cart cart = (Cart) hql.getSingleResult();
	    	session.remove(cart);
			tr.commit();
			session.close();
			
			return cart;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove Single Cart item Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public void removeAllCartItems(String email)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "from Cart where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	List<Cart> items = hql.getResultList();
	    	
	    	for(Cart item:items)
			{
				session.remove(item);
			}
			
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove All Cart items Error : " + ex);
	    }
		
	    tr.commit();
	    session.close();
	}

	@Override
	public long getCartItemsTotal(String email)
	{
		long total = 0;
		int price = 0;
		
		Session session = sessionFactory.openSession();
		
		String query = "from Cart where email = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    
	    try
	    {
	    	List<Cart> items = hql.getResultList();
	    	
	    	for(Cart item:items)
			{
				price = productDAO.getProductbyId(item.getProductId()).getProductPrice();
				total += (price * item.getQuantity());
			}
	    	
	    	session.close();
			return total;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove All Cart items Error : " + ex);
	    }
		
	    return 0;
	}

	@Override
	public long getCartItemsCount(String email)
	{
		Session session = sessionFactory.openSession();
	
		String query = "Select count(email) from Cart where email = :x";
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
	    	System.out.println("Cart Items Count Error : " + ex);
	    }
	    
	    return 0;
	}


	@Override
	public List<String> getAllCartItemsName(List<Cart> items)
	{
		List<String> names = new ArrayList<String>();
		
		for(Cart item:items)
		{
			Product product = productDAO.getProductbyId(item.getProductId());
			names.add(""+product.getProductName());
		}
		
		return names;
	}
	
	@Override
	public void removeAllCartItemsByProductId(String productId)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "from Cart where productId = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",productId);
	    
	    try
	    {
	    	List<Cart> items = hql.getResultList();
	    	
	    	for(Cart item:items)
			{
				session.remove(item);
			}
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Remove All Cart items By ProductID Error : " + ex);
	    }
		
	    tr.commit();
	    session.close();
	}
}
