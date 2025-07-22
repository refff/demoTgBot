package com.example.demo.controller;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.handlers.menu.SecondMenuPageButtonHandler;
import com.example.demo.service.handlers.menu.MenuHandler;
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
    private SecondMenuPageButtonHandler secondMenuPageButtonHandler;

    @Autowired
    public MyBotController(StartHandler startHandler,
                           MessageHandler messageHandler,
                           BotConfig config,
                           MenuHandler menuHandler,
                           SecondMenuPageButtonHandler secondMenuPageButtonHandler) {
        this.startHandler = startHandler;
        this.messageHandler = messageHandler;
        this.config = config;
        this.menuHandler = menuHandler;
        this.secondMenuPageButtonHandler = secondMenuPageButtonHandler;
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

    @CallbackQueryRequest(value = "menu_main_page")
    public void back(Update update) {
        menu(update);
    }

    @CallbackQueryRequest(value = "action")
    public String action(Update update) {
        return "did something";
    }

    @CallbackQueryRequest(value = "nothing")
    public String nothing(Update update) {
        return "nothing";
    }

    @CallbackQueryRequest(value = "menuSecondPage")
    public void secondMenu(Update update) {
        secondMenuPageButtonHandler.handle(update);
    }
}
