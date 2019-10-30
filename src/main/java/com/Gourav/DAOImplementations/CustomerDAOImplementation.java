package com.Gourav.DAOImplementations;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Gourav.DAOs.CustomerDAO;
import com.Gourav.models.Customer;

@Component
public class CustomerDAOImplementation implements CustomerDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Customer addCustomer(Customer customer) 
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.save(customer);
		tr.commit();
		session.close();
		
		return customer;
	}

	@Override
	public Customer removeCustomer(String email) 
	{
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class,email);
		Transaction tr = session.beginTransaction();
		
		session.remove(customer);
		tr.commit();
		session.close();
		
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer)
	{
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		
		session.update(customer);
		tr.commit();
		session.close();
		
		return customer;
	}

	@Override
	public Customer getCustomerbyId(String email)
	{
		
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class,email);
		session.close();
		
		return customer;
	}

	@Override
	public boolean verifyCustomer(String email, String password) 
	{
		Session session = sessionFactory.openSession();
		Customer customer = session.get(Customer.class,email);
		session.close();
		
		if(customer != null)	
		{
			if(customer.getPassword().contentEquals(password))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<Customer> getAllCustomers()
	{
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		List<Customer> customers = cr.list();
		session.close();
		
		return customers;
	}
}
