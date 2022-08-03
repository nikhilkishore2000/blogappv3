package com.blogapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.blogapp.entity.Role;
import com.blogapp.entity.User;
import com.blogapp.repositories.RoleRepo;
import com.blogapp.repositories.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RolesTest {
	
	@Autowired private RoleRepo repo;
	
	@Autowired private UserRepo userrepo;
    
    @Test
    public void testCreateRoles() {
        Role superadmin = new Role("SUPERADMIN");
        Role admin = new Role("ADMIN");
        Role owner = new Role("OWNER");
         
        repo.saveAll(List.of(superadmin, admin, owner));
         
        long count = repo.count();
        assertEquals(3, count);
    }
    
    @Test
    public void testAssignRoleToUser() {
        Integer userId = 1;
        Integer roleId = 3;
        User user = userrepo.findById(userId).get();
        user.addRole(new Role(roleId));
        //user.set
         
        User updatedUser = userrepo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
         
    }
    
    /*@Test
    public void testAssignRoleToUser2() {
        Integer userId = 3;
        Integer roleId = 1;
        User user = userrepo.findById(userId).get();
        user.addRole(new Role(roleId));
         
        User updatedUser = userrepo.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
         
    }*/
}
