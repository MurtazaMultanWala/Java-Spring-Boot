package com.rst.webservices.restfulwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	/*
	 The first two methods are basic way to implement versions in spring boot by making two different URI.
	 ALso called URI type version 
	*/
	
	@GetMapping("version1/person")
	public PersonVersion1 personVersion1() {
		return new PersonVersion1("Murtaza Multan");
	}
	
	@GetMapping("version2/person")
	public PersonVersion2 personVersion2() {
		return new PersonVersion2(new Name("Murtaza", "Multan"));
	}
	
	
	/*
	 The Below two methods are another way to implement versions in spring boot by passing param i.e 
	 version value in URI Get request with same base URI. Also called Request Parameter versioning.   
	*/
	
	@GetMapping(value="/person/param", params = "version=1")
	public PersonVersion1 paramVersion1() {
		return new PersonVersion1("Murtaza Multan");
	}
	
	@GetMapping(value="/person/param", params = "version=2")
	public PersonVersion2 paramVersion2() {
		return new PersonVersion2(new Name("Murtaza", "Multan"));
	}

	
	/*
	 The Below two methods are another way to implement versions in spring boot by passing version in header 
	 i.e version value in Get request "Header" with same base URI. Also called header type version   
	*/
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=1")
	public PersonVersion1 headerVersion1() {
		return new PersonVersion1("Murtaza Multan");
	}
	
	@GetMapping(value="/person/header", headers = "X-API-VERSION=2")
	public PersonVersion2 headerVersion2() {
		return new PersonVersion2(new Name("Murtaza", "Multan"));
	}
	
	
	/*
	 The Below two methods are another way to implement versions in spring boot by passing version in header 
	 i.e version value in Get request "Accept" parameter with same base URI. ALso called Accept or Media Type 
	 versioning.
	*/
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-version1+json")
	public PersonVersion1 producesVersion1() {
		return new PersonVersion1("Murtaza Multan");
	}
	
	@GetMapping(value="/person/produces", produces = "application/vnd.company.app-version2+json")
	public PersonVersion2 producesVersion2() {
		return new PersonVersion2(new Name("Murtaza", "Multan"));
	}
}
