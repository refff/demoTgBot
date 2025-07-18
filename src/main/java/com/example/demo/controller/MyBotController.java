package com.example.demo.controller;

import com.example.demo.congiguration.BotConfig;
import com.example.demo.service.handlers.MenuHandler;
import com.example.demo.service.handlers.MessageHandler;
import com.example.demo.service.handlers.StartHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class MyBotController implements TelegramMvcController {

    private BotConfig config;
    private StartHandler startHandler;
    private MessageHandler messageHandler;
    private MenuHandler menuHandler;

    @Autowired
    public MyBotController(StartHandler startHandler,
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
        return startHandler.handleCommand(update);
    }

    @BotRequest("/menu")
    public void menu(Update update) {
        menuHandler.handleCommand(update);
    }

    @MessageRequest
    public String messenger(Update update) {
        return messageHandler.handleCommand(update);
    }

    @CallbackQueryRequest(value = "back")
    public String back(Update update) {
        return "back";
    }

    @CallbackQueryRequest(value = "action")
    public String action(Update update) {
        return "action";
    }

    @CallbackQueryRequest(value = "nothing")
    public String nothing(Update update) {
        return "nothing";
    }

    @CallbackQueryRequest(value = "forward")
    public String forward(Update update) {
        return "there're nothing for now";
    }
}
