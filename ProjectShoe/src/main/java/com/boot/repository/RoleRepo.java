package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
