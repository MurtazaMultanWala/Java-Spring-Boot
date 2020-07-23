package com.rst.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController  //telling spring that its a restful controller
public class HelloWorldController {
	
	@Autowired
	private  MessageSource messageSource;
	 /*
	  What things required for making web to print hello world
	  GET verb request type
	  //URI - /hello-world
	  Method = "Hello World" printing
	 */
	
	
	// creating get method with uri
//	@Mapping(method = RequestMethod.GET, path= "/hello-world")
	
	@GetMapping(path= "/hello-world")
	public String helloWorld()
	{
		return "Hello World";  
	}
	
	//hello-world-bean
	@GetMapping(path= "/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World");  
	}

	
	//hello-world/path-variable/{name} passing path variable form uri and displaying them in respond message
	@GetMapping(path= "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean("Hello World, "+ name);  
	}
	
	//Internationalization Implementation
	@GetMapping(path= "/hello-world-Internationalized")
	public String helloWorldInternationalization(@RequestHeader(name="Accept-Language", required = false) Locale locale)
	{
		return messageSource.getMessage("good.morning.message", null, locale);  
	}

}
