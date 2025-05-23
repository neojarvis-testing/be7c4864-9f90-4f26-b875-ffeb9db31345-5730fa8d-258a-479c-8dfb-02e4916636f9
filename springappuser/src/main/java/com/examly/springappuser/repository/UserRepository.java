package com.examly.springappuser.repository;

import org.springframework.stereotype.Repository;
import com.examly.springappuser.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("FROM User u WHERE u.email = ?1 or u.username = ?2")
    Optional<User> findEmailAndUsername(String email, String username);
}
