package com.example.demo.service.handlers.pages.profile;

import com.example.demo.entities.UserPRs;
import com.example.demo.entities.UserProfile;
import com.example.demo.infrastructure.SendEditMessage;
import com.example.demo.service.RequestHandler;
import com.example.demo.entities.UserDataLoader;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

@Service
public class ProfilePageHandler implements RequestHandler {

    private final UserDataLoader userDataLoader;
    private String headerText = "Your Profile\n";
    private String textBody;

    public ProfilePageHandler(UserDataLoader userDataLoader) {
        this.userDataLoader = userDataLoader;
    }


    @Override
    public void handle(Update update) {
        long chatId = update.callbackQuery().message().chat().id();
        int messageId = update.callbackQuery().message().messageId();

        textBody = messageCreator(update);

        SendEditMessage.sendMessage(chatId, messageId, headerText + textBody, createKeyboard());
    }

    private String messageCreator(Update update) {
        UserProfile user = userDataLoader.loadUserProfile(update);
        UserPRs userPRs = userDataLoader.loadUserPRs(user);

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

    private InlineKeyboardMarkup createKeyboard() {
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("mainMenuPage");
        var editButton = new InlineKeyboardButton("\uD83E\uDE86edit").callbackData("edit_profile");

        return new InlineKeyboardMarkup()
                .addRow(editButton)
                .addRow(backButton);
    }
}
