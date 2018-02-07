package edu.mum.repository;


import org.springframework.data.repository.CrudRepository;  //.jpa
import org.springframework.stereotype.Repository;

import edu.mum.domain.User;



//@Repository("userRepository") //indicates that an annotated class is a repository, which is an abstraction of data access and storage.
@Repository
public interface UserRepository1 extends CrudRepository<User, Long> {
	 User findByEmail(String email);
	 User findByConfirmationToken(String confirmationToken);
}

/*
   CrudRepository is the pre-defined core repository class (in Spring Data Commons) 
   enabling the basic CRUD functions on a repository
  renemed
*/

