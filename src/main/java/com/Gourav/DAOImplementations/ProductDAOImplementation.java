package com.Gourav.DAOImplementations;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gourav.DAOs.ProductDAO;
import com.Gourav.models.Product;

@Component
public class ProductDAOImplementation implements ProductDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Product addProduct(Product product)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(product);
		tr.commit();
		session.close();
		
		return product;
	}

	@Override
	public Product updateProduct(Product product)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(product);
		tr.commit();
		session.close();
		
		return product;
	}
	
	@Override
	public Product removeProduct(String productId)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Product product = session.get(Product.class,productId);
		
		session.delete(product);
		tr.commit();
		session.close();
		
		return product;
	}

	@Override
	public List<Product> getAllProducts() 
	{
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Product.class);
		List<Product> products = cr.list();
		session.close();
		
		return products;
	}
	
	@Override
	public List<Product> getAllProductsByFilter(String filter, String order)
	{
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Product.class);
		
		if(order.contains("DESC"))
		{
			System.out.println("H1---------------");
			cr.addOrder(Order.desc(filter));
		}
		else
		{
			System.out.println("H2---------------");
			cr.addOrder(Order.asc(filter));			
		}
		
		List<Product> products = cr.list();
		session.close();
	
		return products;
	}

	@Override
	public Product getProductbyId(String productId)
	{
		Session session = sessionFactory.openSession();
		Product product = session.get(Product.class,productId);
		session.close();
		
		if(product == null)
		{
			return null;
		}
		
		return product;
	}

	@Override
	public int getCount()
	{
		int count = 0;
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		String query = "select max(productId) from Product";
		Query hql = session.createQuery(query);
		
		try
		{
			String answer = (String) hql.getSingleResult();
		    int number = Integer.parseInt(answer.substring(1)) + 1;
		    
		    tr.commit();
			session.close();
			
			return number;
		}
		catch(Exception ex)
		{
			System.out.println("Get Count Error : " + ex);
		}
		
		return count;
	}

	@Override
	public List<Product> getSearchResult(String searchKeyword)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from Product where (productName LIKE('%"+searchKeyword+"%')) OR (productCategory LIKE('%"+searchKeyword+"%'))";
		Query hql = session.createQuery(query);
		
		try
		{
			List<Product> products = hql.getResultList();
			session.close();
			
			return products;
		}
		catch(Exception ex)
		{
			System.out.println("Search Result Error : " + ex);
		}
		
		return null;
	}
}
