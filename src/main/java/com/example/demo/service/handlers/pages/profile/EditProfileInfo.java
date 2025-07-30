package com.example.demo.service.handlers.pages.profile;

import com.example.demo.controller.CallbackController;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class EditProfileInfo implements RequestHandler {

    @Override
    public void handle(Update update) {
        long chatId = update.callbackQuery().message().chat().id();
        String state = null;

        switch (update.callbackQuery().data()) {
            case "changeDeadlift" -> state = "deadlift";
            case "changeBenchPress" -> state = "bench";
            case "changeSquat" -> state = "squat";
            case "changePersonality" -> state = "personality";
        }

        CallbackController.userState.put(chatId, state);
    }
}
