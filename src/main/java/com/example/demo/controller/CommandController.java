package com.example.demo.controller;

import com.example.demo.infrastructure.BotConfig;
import com.example.demo.service.commands.StartHandler;
import com.example.demo.service.pages.menus.MainMenuHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class CommandController implements TelegramMvcController {

    private BotConfig config;
    private StartHandler startHandler;
    private MainMenuHandler mainMenuHandler;

    @Autowired
    public CommandController(StartHandler startHandler,
                             BotConfig config,
                             MainMenuHandler mainMenuHandler) {
        this.startHandler = startHandler;
        this.config = config;
        this.mainMenuHandler = mainMenuHandler;
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
        mainMenuHandler.handle(update);
    }
}
