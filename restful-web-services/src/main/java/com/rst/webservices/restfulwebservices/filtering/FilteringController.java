package com.rst.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	/*
	 Dynamic Filtering different fields to visible on different request 
	 for that MappingJacksonValue is used
	*/
	
	
	// want to return field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		
		SomeBean someBean= new SomeBean("v1", "v2", "v3");
		
		// Remove all Filters from response except
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		// adding the filters wanted to keep in filters, SomeBean filter is the name of the filter used as
		// annotation on SomeBean class showing that to apply this filter on it else no filters will be applied.
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
	// want to return field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("v1", "v2", "v3"),new SomeBean("v4", "v5", "v6"));
		
		// Remove all Filters from response except
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		// adding the filters wanted to keep in filters, SomeBean filter is the name of the filter used as
		// annotation on SomeBean class showing that to apply this filter on it else no filters will be applied.
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		
		mapping.setFilters(filters);
		
		return mapping;
		
	}
}
