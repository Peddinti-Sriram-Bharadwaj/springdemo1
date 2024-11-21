package com.sriram9217.demo1.controllers;

import com.sriram9217.demo1.entity.User;
import com.sriram9217.demo1.exception.UserNotFoundException;
import com.sriram9217.demo1.repo.UserRepository;
import com.sriram9217.demo1.service.userDaoService; // Updated naming
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserJPAResource { // Updated naming

    @Autowired
    private userDaoService service; // Updated naming


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/users/{id}")
//    public User retrieveUserById(@PathVariable int id) {
//        User user = service.findById(id);
//        if (user == null)
//            throw new UserNotFoundException("id: " + id);
//        return user;
//    }

    @PostMapping("/jpa/users")
    public void createUser(@Valid @RequestBody User user) {
        userRepository.save(user);

    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

//    @GetMapping("/jpa/users/{id}")
//    public EntityModel<User> retrieveUserById(@PathVariable int id) {
//        User user = service.findById(id);
//        EntityModel<User> resource = EntityModel.of(user);
//
//        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
//        resource.add(linkTo.withRel("all-users"));
////        if (user == null)
////            throw new UserNotFoundException("id: " + id);
//        return resource;
//    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        // Fetch the user from the repository
        Optional<User> user = userRepository.findById(id);

        // Check if the user is present; if not, throw an exception
        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        // Create an EntityModel for the found user
        EntityModel<User> resource = EntityModel.of(user.get());

        // Add a link to retrieve all users
        Link linkToAllUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(linkToAllUsers);

        return resource;
    }

}
