package com.in28minutes.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.domain.User;
import com.in28minutes.rest.webservices.exceptionhandling.NoUserPresentException;
import com.in28minutes.rest.webservices.exceptionhandling.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.UserDaoService;

@RestController
public class UserResourceController {
	
	@Autowired
	private UserDaoService userService;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		List<User> users = userService.findAll();
		if(users.size() == 0) {
			throw new NoUserPresentException();
		}
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id- "+id);
		}
		
		//HATEOAS
		EntityModel<User> entityModel = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(linkTo.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = userService.deletebyId(id);
		if(user == null) {
			throw new UserNotFoundException("id- "+id);
		}
		return user;
	}

}
