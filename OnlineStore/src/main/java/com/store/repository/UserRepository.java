package com.store.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("SELECT u FROM User WHERE u.email = ?1")
	User findByEmail(String email);
	
}
