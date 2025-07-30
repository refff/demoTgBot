package com.example.demo.entities;

import com.example.demo.persistance.UserPRsRepository;
import com.example.demo.persistance.UserProfileRepository;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataLoader {

    private UserProfileRepository userProfileRepository;
    private UserPRsRepository userPRsRepository;

    @Autowired
    public UserDataLoader(UserProfileRepository userProfileRepository,
                          UserPRsRepository userPRsRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userPRsRepository = userPRsRepository;
    }

    public UserProfile loadUserProfile(Update update) {
        UserProfile user;
        String username = update.callbackQuery().message().chat().username();

        if (userProfileRepository.existsByUsername(username)) {
            user = userProfileRepository.findByUsername(username);
        } else {
            user = createUser(update);
        }

        return user;
    }

    private UserProfile createUser(Update update) {
        UserPRs userPRs = new UserPRs();
        userPRsRepository.save(userPRs);

        UserProfile user = new UserProfile();
        user.setUsername(update.callbackQuery().message().chat().username());
        user.setFirstName(update.callbackQuery().message().chat().firstName());
        user.setLastName(update.callbackQuery().message().chat().lastName());
        user.setPersonality("none");
        user.setPRs(userPRs);

        userProfileRepository.save(user);

        return user;
    }

    public UserPRs loadUserPRs(UserProfile userProfile) {
        return userPRsRepository.findByUserProfile(userProfile);
    }
}
