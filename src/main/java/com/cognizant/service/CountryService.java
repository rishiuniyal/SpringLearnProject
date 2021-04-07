package com.cognizant.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.bindings.Country;
import com.cognizant.exception.CountryNotFoundException;


@Service
public class CountryService {
	
	
	public Country getCountry(String code) throws CountryNotFoundException {
		ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
		
		List<Country> list=(List<Country>) context.getBean("countryList");
		
		Country country=null;
		
		for(Country c:list) {
			if (code.equals(c.getCode())){
				country=c;
				break;
			}
			
		}
		if (country == null) {
			throw new CountryNotFoundException();
		}
		return country;
		
	
		
		
	}

	
}
