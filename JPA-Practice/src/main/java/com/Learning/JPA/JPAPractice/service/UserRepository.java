package com.Learning.JPA.JPAPractice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Learning.JPA.JPAPractice.entity.User;

/*
Motivation: Each EntityManager contains single Entity file for doing stuff
what if we need to make 100 Such services so we need to create 100 seperate classes?
for that this UserRespository is created where spring will decide at runtime what
to configure how to configure. 
*/
public interface UserRepository extends JpaRepository<User, Long>{

}
