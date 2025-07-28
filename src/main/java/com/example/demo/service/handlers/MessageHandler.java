package com.example.demo.service.handlers;

import com.example.demo.controller.CommandController;
import com.example.demo.entities.UserPRs;
import com.example.demo.entities.UserProfile;
import com.example.demo.persistance.UserPRsRepository;
import com.example.demo.persistance.UserProfileRepository;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

    private UserProfileRepository userProfileRepository;
    private UserPRsRepository userPRsRepository;

    @Autowired
    public MessageHandler(UserProfileRepository userProfileRepository,
                          UserPRsRepository userPRsRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userPRsRepository = userPRsRepository;
    }

    public String handle(Update update) {

        String response;
        String state = CommandController.userState.get(update.message().chat().id());

        if (state == null) {
            response = update.message().text();
        } else {
            response =  handleUserUpdate(update, state);
        }

        return response;
    }

    private String handleUserUpdate(Update update, String state) {
        String username = update.message().from().username();

        UserProfile user = userProfileRepository.findByUsername(username);
        UserPRs prs = userPRsRepository.findByUserProfile(user);

        switch (state) {
            case "deadlift" -> prs.setDeadlift(update.message().text());
            case "bench" -> prs.setBenchPress(update.message().text());
            case "squat" -> prs.setSquat(update.message().text());
            case "personality" -> {
                user.setPersonality(update.message().text());
                userProfileRepository.save(user);
            }
        }

        userPRsRepository.save(prs);
        CommandController.userState.clear();

        return "value " + state + " was changed to " + update.message().text();
    }
}
