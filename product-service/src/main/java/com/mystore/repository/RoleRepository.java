package com.mystore.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystore.entity.Role;
import com.mystore.enumeration.ERole;


@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {

    // Method to find a role by name
    Optional<Role> findByName(ERole name);

    // Method to check if a role exists by its name
    boolean existsByName(ERole name);

}
