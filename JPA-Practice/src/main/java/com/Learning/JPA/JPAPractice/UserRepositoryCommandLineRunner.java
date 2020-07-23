package com.Learning.JPA.JPAPractice;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Learning.JPA.JPAPractice.entity.User;
import com.Learning.JPA.JPAPractice.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner{

	
	private static final Logger log =
			LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jill", "Admin");
		
//		User is created: User [id=1, name=Jack, role=Admin]		
//      http://localhost:8080/h2-console  to view database
//		jdbc url : jdbc:h2:mem:{id}  --> id from log
		
		userRepository.save(user);
		log.info("New User is created: " + user);
	
		Optional<User> userWithIdOne = userRepository.findById(1L);
		log.info("User is retrieved : " + userWithIdOne);
		
		List<User> users = userRepository.findAll();
		log.info("All User : " + users);
	}

}
