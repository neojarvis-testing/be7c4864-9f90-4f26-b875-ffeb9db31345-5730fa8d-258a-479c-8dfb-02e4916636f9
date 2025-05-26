package com.examly.springappfeedback.repository;

import org.springframework.stereotype.Repository;
import com.examly.springappfeedback.model.User;
import org.springframework.data.jpa.repository.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
