package com.Gourav.DAOImplementations;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Gourav.DAOs.MyOrderDAO;
import com.Gourav.models.MyOrder;

@Component
public class MyOrderDAOImplementation implements MyOrderDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public MyOrder addOrder(MyOrder order) 
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(order);
		tr.commit();
		session.close();
		
		return order;
	}

	@Override
	public long getOrderId()
	{
		Session session = sessionFactory.openSession();
		
		String query = "Select count(orderId) from MyOrder";
	    Query hql = session.createQuery(query);
	    
	    try
	    {
	    	long count = (long) hql.getSingleResult();
			session.close();
			
			return count+1;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get Order Id Error : " + ex);
	    }
	    
	    return 0;
	}

	@Override
	public List<MyOrder> getAllOrders(String email, String orderStatus)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from MyOrder where email = :x and orderStatus = :y";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",email);
	    hql.setParameter("y",orderStatus);
	    
	    try
	    {
	    	List<MyOrder> orders = hql.getResultList();
			session.close();
			
			return orders;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get All My Orders Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public MyOrder updateOrder(MyOrder order)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(order);
		tr.commit();
		session.close();
		
		return order;
	}

	@Override
	public List<MyOrder> getAllOrdersAdmin(String orderStatus)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from MyOrder where orderStatus = :x";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",orderStatus);
	    
	    try
	    {
	    	List<MyOrder> orders = hql.getResultList();
			session.close();
			
			return orders;
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Get All My Orders Admin Error : " + ex);
	    }
	    
	    return null;
	}

	@Override
	public MyOrder getOrderById(long orderId)
	{
		Session session = sessionFactory.openSession();
		MyOrder order = session.get(MyOrder.class,orderId);
		session.close();
		
		return order;
	}

	@Override
	public void updateAllOrdersByProductId(String productId)
	{
		Session session = sessionFactory.openSession();
		
		String query = "from MyOrder where productId = :x and orderStatus='Ordered'";
	    Query hql = session.createQuery(query);
	    hql.setParameter("x",productId);
	    
	    try
	    {
	    	List<MyOrder> orders = hql.getResultList();
	    	
	    	for(MyOrder order:orders)
	    	{
	    	    order.setOrderStatus("Canceled");	
	    	    updateOrder(order);
	    	}
	    	
			session.close();
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Update All Orders By ProductID Error : " + ex);
	    }
	}
}
