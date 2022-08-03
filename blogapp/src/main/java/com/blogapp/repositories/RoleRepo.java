package com.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entity.Role;
import com.blogapp.entity.User;


public interface RoleRepo extends JpaRepository<Role,Integer> {

}
