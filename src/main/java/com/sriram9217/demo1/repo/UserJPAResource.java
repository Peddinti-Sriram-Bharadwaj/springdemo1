package com.sriram9217.demo1.controllers;

import com.sriram9217.demo1.entity.Post;
import com.sriram9217.demo1.entity.User;
import com.sriram9217.demo1.exception.UserNotFoundException;
import com.sriram9217.demo1.repo.PostRepository;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Validated
public class UserJPAResource { // Updated naming

    @Autowired
    private userDaoService service; // Updated naming


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

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
        Link linkToAllUsers = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(linkToAllUsers);

        return resource;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePosts(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();


    }

}
