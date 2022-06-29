package com.storage.control.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.control.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
