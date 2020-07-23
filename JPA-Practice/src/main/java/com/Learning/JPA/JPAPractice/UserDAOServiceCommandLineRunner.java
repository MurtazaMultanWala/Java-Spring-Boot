package com.Learning.JPA.JPAPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Learning.JPA.JPAPractice.entity.User;
import com.Learning.JPA.JPAPractice.service.UserDAOService;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{

	
	private static final Logger log =
			LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);
	
	@Autowired
	private UserDAOService userDAOService;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jack", "Admin");
		
//		User is created: User [id=1, name=Jack, role=Admin]		
//      http://localhost:8080/h2-console  to view database
//		jdbc url : jdbc:h2:mem:{id}  --> id from log
		
		long insert = userDAOService.insert(user);
		log.info("New User is created: " + user);
	
	}

}
