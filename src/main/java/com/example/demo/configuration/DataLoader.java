package com.example.demo.configuration;

import com.example.demo.entities.UserProfile;
import com.example.demo.persistance.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public DataLoader(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void loadBasicUsers() {
        UserProfile userMatvey = new UserProfile();
        userMatvey.setUsername("Matvey_1809");
        userProfileRepository.save(userMatvey);

        UserProfile userVral = new UserProfile();
        userVral.setUsername("vralmann");
        userProfileRepository.save(userVral);
    }
}
