package com.rst.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Murtaza", new Date()));
		users.add(new User(2, "Husain", new Date()));
		users.add(new User(3, "Mustafa", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		
		if(user.getId() == null){
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id){
		
		for(User user: users) {
			if(user.getId() == id)
				return user;
		}
		
		return null;
	}
	
	
	public User deleteById(int id){
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User tempUser = iterator.next();
			
			if(tempUser.getId() == id) {
				iterator.remove();
				return tempUser;
			}
		}
		
		return null;
	}
}
