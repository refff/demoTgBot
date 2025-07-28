package com.example.demo.service.handlers.menu;

import com.example.demo.controller.CommandController;
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

        CommandController.userState.put(chatId, state);
    }
}
