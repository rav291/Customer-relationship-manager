package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session factory
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by firstName", Customer.class);
 		
		// execute the query and get the result list
		
		List<Customer> customers = theQuery.getResultList();
		
		// return the results
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer .. finally
		
		currentSession.saveOrUpdate(theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve the customer from database using the primary Id
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}
	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the customer object with the primary key(iD)
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
	}
}