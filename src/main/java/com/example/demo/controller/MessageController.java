package com.example.demo.controller;

import com.example.demo.infrastructure.BotConfig;
import com.example.demo.service.handlers.message.MessageHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class MessageController implements TelegramMvcController {

    private final BotConfig botConfig;
    private final MessageHandler messageHandler;

    @Autowired
    public MessageController(BotConfig botConfig,
                             MessageHandler messageHandler) {
        this.botConfig = botConfig;
        this.messageHandler = messageHandler;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    @MessageRequest
    String handleMessage(Update update) {
        return messageHandler.handle(update);
    }
}
