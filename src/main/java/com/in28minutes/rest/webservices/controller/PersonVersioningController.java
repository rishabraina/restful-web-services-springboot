package com.in28minutes.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.versioning.Name;
import com.in28minutes.rest.webservices.versioning.PersonV1;
import com.in28minutes.rest.webservices.versioning.PersonV2;

@RestController
public class PersonVersioningController {
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Boris Johnson");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Sam", "Gulela"));
	}
	
	@GetMapping(value="person", params="version=1")
	public PersonV1 personParamV1() {
		return new PersonV1("Boris Johnson");
	}
	
	@GetMapping(value="person", params="version=2")
	public PersonV2 personParamV2() {
		return new PersonV2(new Name("Sam", "Gulela"));
	}
	
	@GetMapping(value="person", headers="X-API-VERSION=1")
	public PersonV1 personHeaderV1() {
		return new PersonV1("Boris Johnson");
	}
	
	@GetMapping(value="person", headers="X-API-VERSION=2")
	public PersonV2 personHeaderV2() {
		return new PersonV2(new Name("Sam", "Gulela"));
	}
	
	@GetMapping(value="person", produces="application/vnd.company.app-v1+json")
	public PersonV1 personProducesV1() {
		return new PersonV1("Boris Johnson");
	}
	
	@GetMapping(value="person", produces="application/vnd.company.app-v2+json")
	public PersonV2 personProducesV2() {
		return new PersonV2(new Name("Sam", "Gulela"));
	}

}
