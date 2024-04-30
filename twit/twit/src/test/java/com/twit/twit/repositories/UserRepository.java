package com.twit.twit.repositories;

import com.twit.twit.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserClass, Integer> {
    UserClass findByEmail(String email);
}
