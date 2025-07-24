package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.example.demo.entities.UserPRs;
import com.example.demo.entities.UserProfile;
import com.example.demo.persistance.UserPRsRepository;
import com.example.demo.persistance.UserProfileRepository;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileHandler implements RequestHandler {

    private String headerText = "Your Profile\n";
    private String paragraphText;
    private EditMessageText messageResponse;
    private UserProfileRepository userProfileRepository;
    private UserPRsRepository userPRsRepository;

    @Autowired
    public ProfileHandler(UserProfileRepository userProfileRepository,
                          UserPRsRepository userPRsRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userPRsRepository = userPRsRepository;
    }

    @Override
    public void handle(Update update) {
        long chatId = update.callbackQuery().message().chat().id();
        int messageId = update.callbackQuery().message().messageId();

        paragraphText = messageCreator(update);

        messageResponse = new EditMessageText(chatId, messageId, headerText +  paragraphText);
        messageResponse.replyMarkup(assignKeyboard());

        BotConfig.telegramBot.execute(messageResponse);
    }

    private String messageCreator(Update update) {
        UserProfile user = loadUserProfile(update);
        UserPRs userPRs = loadUserPRs(user);

        return String.format("""
                
                username: %s

                firstName: %s
                lastName: %s
                animal: %s

                deadlift: %s kg
                benchPress: %s kg
                squat: %s kg
                """,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPersonality(),
                userPRs.getDeadlift(),
                userPRs.getBenchPress(),
                userPRs.getSquat());
    }

    private InlineKeyboardMarkup assignKeyboard() {
        var backButton = new InlineKeyboardButton("⬅\uFE0F back").callbackData("menu_main_page");
        var editButton = new InlineKeyboardButton("\uD83E\uDE86 edit").callbackData("edit_profile");

        return new InlineKeyboardMarkup()
                .addRow(editButton)
                .addRow(backButton);
    }

    //move user load to separate class
    private UserProfile loadUserProfile(Update update) {
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

    private UserPRs loadUserPRs(UserProfile userProfile) {
        return userPRsRepository.findByUserProfile(userProfile);
    }
}
