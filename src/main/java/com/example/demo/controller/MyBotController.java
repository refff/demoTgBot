package com.example.demo.controller;

import com.example.demo.congiguration.BotConfig;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.telegram.telegrambots.meta.api.objects.Chat;

@BotController
public class MyBotController implements TelegramMvcController {

    @Autowired
    private BotConfig config;

    @Override
    public String getToken() {
        return config.getToken();
    }

    @BotRequest(value = "/start")
    public String startBot(User user, Chat chat) {
        return "Hello, " + user.username() + " it handel by @BotRequest";
    }

    @MessageRequest
    public String messenger(Update update, Chat chat) {
        return update.message().text();
    }
}
