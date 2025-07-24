package com.example.demo.persistance;

import com.example.demo.entities.UserPRs;
import com.example.demo.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPRsRepository extends JpaRepository<UserPRs, Long> {
    UserPRs findByUserProfile(UserProfile userProfile);
    //UserPRs findByUsername(String username);
}
