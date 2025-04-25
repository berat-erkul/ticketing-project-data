package com.cydeo.repository;

import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Even if we don't define any method here, we can still use all the methods from JpaRepository

    User findByUserName(String username);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleDescriptionIgnoreCase(String lowerCase);
}
