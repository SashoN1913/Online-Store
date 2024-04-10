package com.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
	Role findByName(String name);
}
