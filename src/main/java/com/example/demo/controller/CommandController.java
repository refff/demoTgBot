package com.example.demo.controller;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.handlers.menu.*;
import com.example.demo.service.handlers.MessageHandler;
import com.example.demo.service.handlers.StartHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@BotController
public class CommandController implements TelegramMvcController {

    private BotConfig config;
    private StartHandler startHandler;
    private MessageHandler messageHandler;
    private MenuHandler menuHandler;


    public static final Map<Long, String> userState = new HashMap<>();

    @Autowired
    public CommandController(StartHandler startHandler,
                             MessageHandler messageHandler,
                             BotConfig config,
                             MenuHandler menuHandler) {
        this.startHandler = startHandler;
        this.messageHandler = messageHandler;
        this.config = config;
        this.menuHandler = menuHandler;
    }

    @Override
    public String getToken() {
        return config.getToken();
    }

    @BotRequest("/start")
    public String startBot(Update update) {
        return startHandler.handle(update);
    }

    @BotRequest("/menu")
    public void menu(Update update) {
        menuHandler.handle(update);
    }
}
