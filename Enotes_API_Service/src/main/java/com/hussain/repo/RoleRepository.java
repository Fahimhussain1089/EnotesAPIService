package com.hussain.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hussain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}