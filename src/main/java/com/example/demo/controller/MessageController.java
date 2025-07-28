package com.example.demo.controller;

import com.example.demo.configuration.BotConfig;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;

@BotController
public class MessageController implements TelegramMvcController {

    private final BotConfig botConfig;

    public MessageController(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    @MessageRequest
    String handleMessage(Update update) {
        return update.message().text();
    }
}
