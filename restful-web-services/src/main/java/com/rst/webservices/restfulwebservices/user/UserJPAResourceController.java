package com.rst.webservices.restfulwebservices.user;


//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResourceController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	/* GET /users
	   Retrieve all users
	*/
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
	 	return userRepository.findAll();
	}
	
	
	/* GET /users
	   Retrieve Single user by id
	*/
	
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveSingleUser(@PathVariable  int id){
		Optional<User> retrievedUser = userRepository.findById(id);
		
		if(!retrievedUser.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		/*HATEOAS -> Hyper Media as the engine of application state
		 "all-users", SERVER_PATH + "/users"
		 retrieveAllUsers
		 adding links with retrieved user
		*/
//		Resource<User> resource = new Resource<User>(retrievedUser.get());
//		
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
//		
//		resource.add(linkTo.withRel("all-users"));
		 
		return retrievedUser;
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable  int id){
		userRepository.deleteById(id);		
	}
	
	
	
	
	/*Post request
	 input - details of user
	 output - Created Status and Return the Created URI*/
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		//Request Body parameter maps response to User class in JSON format in post body
		User savedUser = userRepository.save(user);
		
		/*
		 Now implementing output part i.e. 
		 * CREATED status 
		 * Created URI i.e. users/{id}  savedUser.getId()
		 
		 * ServletUriComponentsBuilder.fromCurrentRequest() used to provide current URI of user added
		   we need to append the user id of newly added user so we add that in URI using .path() which
		   helps to append the id in it and return that URI in Created type Entity Response.
		*/
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostOfAUser(@PathVariable int id){
	 	Optional<User> retrievedUser = userRepository.findById(id);
	 	
	 	if(!retrievedUser.isPresent()) {
	 		throw new UserNotFoundException("id-"+id);
	 	}
	 	
	 	return retrievedUser.get().getPosts();
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> retrievedUser = userRepository.findById(id);
	 	
	 	if(!retrievedUser.isPresent()) {
	 		throw new UserNotFoundException("id-"+id);
	 	}
	 	
	 	User user = retrievedUser.get();
	 	
	 	
		
		//Request Body parameter maps response to User class in JSON format in post body
		post.setUser(user);
		
		postRepository.save(post);
		/*
		 Now implementing output part i.e. 
		 * CREATED status 
		 * Created URI i.e. users/{id}  savedUser.getId()
		 
		 * ServletUriComponentsBuilder.fromCurrentRequest() used to provide current URI of user added
		   we need to append the user id of newly added user so we add that in URI using .path() which
		   helps to append the id in it and return that URI in Created type Entity Response.
		*/
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}


/*
<dependency>
		    <groupId>org.springframework.hateoas</groupId>
		    <artifactId>spring-hateoas</artifactId>
		    <version>0.18.0.RELEASE</version>
</dependency>
<dependency>
		    <groupId>org.springframework.hateoas</groupId>
		    <artifactId>spring-hateoas</artifactId>
		    <version>0.16.0.RELEASE</version>
</dependency>
		 
 * */
