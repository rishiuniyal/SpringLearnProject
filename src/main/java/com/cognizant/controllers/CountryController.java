package com.cognizant.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bindings.Country;
import com.cognizant.exception.CountryNotFoundException;
import com.cognizant.service.CountryService;

@RestController
public class CountryController {
	
	@Autowired
	private CountryService service;
	
	
	ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
	
	@GetMapping(
			value="/country",
			produces= {"application/json"}
			
			)
	public ResponseEntity<Country> getCountryIndia(){
		
		
		Country country=(Country) context.getBean("country");
		
	
		return new ResponseEntity<Country>(country,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(
			value="/countries",
			produces= {"application/json"}
			
			)

	public ResponseEntity<List<Country>> getAllCountries(){
		
		
		List<Country> list=(List<Country>) context.getBean("countryList");
		
		
		return new ResponseEntity<List<Country>>(list, HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping(
			value="/countries/{code}",
			produces= {"application/json"}			
			)
	
	public ResponseEntity<Country> getCountry(@PathVariable String code) throws CountryNotFoundException {
		
		
		Country coun=service.getCountry(code);
	
		return new ResponseEntity<Country>(coun,HttpStatus.OK);
	}
	
	
}
