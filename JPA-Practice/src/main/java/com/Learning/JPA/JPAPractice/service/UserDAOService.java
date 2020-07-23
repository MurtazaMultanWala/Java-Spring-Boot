package com.Learning.JPA.JPAPractice.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.Learning.JPA.JPAPractice.entity.User;


/* 
 * userDAOService is the class used to save data to the database this
 * is indicated by using Repository annotation  which interacts with database.
 * 
 * Transactional is used to indicate the transaction i.e. before doing action on
 * database transaction is open then changes are performed and then closed.
*/


@Repository 
@Transactional
public class UserDAOService {

	/*
	 * Saved User
	 * Retrieve User form database 
	 * using Entity Manager
	*/
	
	/*
	 User jack = new User("Jack", "Admin");
	 User jill = new User("Jill", "Admin");
	 
	 used to track the changes done by jack only as its on persist by entityManager
	 entityManager.persist(jack)  
	  
	 Persistence Context -- keep track of things which are in persist of entityManager
	 
	 jack.setRole("User");
	
	 jill.setRole("User");   this will not be track because not in persist of entityManager.
	  
	*/
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
