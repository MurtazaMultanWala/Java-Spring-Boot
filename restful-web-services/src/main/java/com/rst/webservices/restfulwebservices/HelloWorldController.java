package com.rst.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  //telling spring that its a restful controller
public class HelloWorldController {
	
	 /*
	  What things required for making web to print hello world
	  GET verb request type
	  //URI - /hello-world
	  Method = "Hello World" printing
	 */
	
	
	// creating get method with uri
	@RequestMapping(method = RequestMethod.GET, path= "/hello-world")
	public String helloWorld()
	{
		return "Hello World";  
	}

}
