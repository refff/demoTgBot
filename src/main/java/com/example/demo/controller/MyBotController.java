package com.example.demo.controller;

import com.example.demo.congiguration.BotConfig;
import com.example.demo.service.MessageHandler;
import com.example.demo.service.StartHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class MyBotController implements TelegramMvcController {

    private BotConfig config;
    private StartHandler startHandler;
    private MessageHandler messageHandler;

    @Autowired
    public MyBotController(StartHandler startHandler,
                           MessageHandler messageHandler,
                           BotConfig config) {
        this.startHandler = startHandler;
        this.messageHandler = messageHandler;
        this.config = config;
    }

    @Override
    public String getToken() {
        return config.getToken();
    }

    @BotRequest()
    public String startBot(Update update) {
        return startHandler.handleCommand(update);
    }

    @MessageRequest
    public String messenger(Update update) {
        return messageHandler.handleCommand(update);
    }
}
