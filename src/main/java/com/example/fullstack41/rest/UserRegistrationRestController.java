package com.example.fullstack41.rest;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fullstack41.dto.UsersDTO;
import com.example.fullstack41.exceptions.CustomErrorType;
import com.example.fullstack41.repository.UserJpaRepository;


@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
        public static final Logger logger =
                LoggerFactory.getLogger(UserRegistrationRestController.class);
        private UserJpaRepository userJpaRepository;
        @Autowired
        public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
                this.userJpaRepository = userJpaRepository;
        }  
        
        @GetMapping("/")
        public ResponseEntity<List<UsersDTO>> listAllUsers() {
                List<UsersDTO> users = userJpaRepository.findAll();
                if (users.isEmpty()) {
                    return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<List<UsersDTO>>(users, HttpStatus.OK);
        }
        
        @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UsersDTO> createUser(
        		@Validated @RequestBody final UsersDTO user) {
        	    if (userJpaRepository.findByName(user.getName()) != null) {
                     return new ResponseEntity<UsersDTO>(new CustomErrorType(
                        "Unable to create new user. A User with name "
                        + user.getName() + " already exist."),HttpStatus.CONFLICT);
               }
                userJpaRepository.save(user);
                return new ResponseEntity<UsersDTO>(user, HttpStatus.CREATED);
        }
        
        
        
        @GetMapping("/{id}")
        public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") final Long id) {
                Optional<UsersDTO> user = userJpaRepository.findById(id);
                if (user.isEmpty()) {
                	return new ResponseEntity<UsersDTO>(
                			new CustomErrorType("User with id = " +id + " not found"), 
                			HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<UsersDTO>(user.get(), HttpStatus.OK);
        }
        
        
        @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UsersDTO> updateUser(
                        @PathVariable("id") final Long id, @RequestBody UsersDTO user) {
                // fetch user based on id and set it to currentUser object of type UserDTO
                Optional<UsersDTO> currentUserOp = userJpaRepository.findById(id);
                // update currentUser object data  with user object data
                if (currentUserOp.isEmpty()) {
                    return new ResponseEntity<UsersDTO>(
                                    new CustomErrorType("Unable to upate. User with id "
                                            + id + " not found."), HttpStatus.NOT_FOUND);
                }
                
                UsersDTO tmp = userJpaRepository.findByName(user.getName());
                if (tmp != null && tmp.getId() != id) {
                	return new ResponseEntity<UsersDTO>(new CustomErrorType(
                            "Unable to update user with id =  " +id +" . A User with name "
                            + user.getName() + " already exist."),HttpStatus.CONFLICT);
                }
                
                UsersDTO currentUser = currentUserOp.get();
                currentUser.setName(user.getName());
                currentUser.setAddress(user.getAddress());
                currentUser.setEmail(user.getEmail());
                // save currentUser obejct
                userJpaRepository.saveAndFlush(currentUser);
                //return ResponseEntity object
                return new ResponseEntity<UsersDTO>(currentUser, HttpStatus.OK);
        }
        
        
        @DeleteMapping("/{id}")
        public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
                Optional<UsersDTO> user = userJpaRepository.findById(id);
                if (user.isEmpty()) {
                    return new ResponseEntity<UsersDTO>(
                                    new CustomErrorType("Unable to delete. User with id "
                                            + id + " not found."), HttpStatus.NOT_FOUND);
                }
                userJpaRepository.deleteById(id);
                return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
        }
        
}
