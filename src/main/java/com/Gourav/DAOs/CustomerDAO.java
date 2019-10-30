package com.Gourav.DAOs;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Gourav.models.Customer;

@Component
public interface CustomerDAO
{
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(String email);
	public Customer updateCustomer(Customer customer);
	public Customer getCustomerbyId(String email);
	public boolean verifyCustomer(String email, String password);
	public List<Customer> getAllCustomers();
	
}
