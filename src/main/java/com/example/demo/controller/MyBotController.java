package com.example.demo.controller;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.handlers.menu.*;
import com.example.demo.service.handlers.MessageHandler;
import com.example.demo.service.handlers.StartHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@BotController
public class MyBotController implements TelegramMvcController {

    private BotConfig config;
    private StartHandler startHandler;
    private MessageHandler messageHandler;
    private MenuHandler menuHandler;
    private SecondMenuHandler secondMenuHandler;
    private ProfileHandler profileHandler;
    private ProfileEditMenuHandler profileEditMenuHandler;
    private EditProfileInfo editProfileInfo;

    public static final Map<Long, String> userState = new HashMap<>();

    @Autowired
    public MyBotController(StartHandler startHandler,
                           MessageHandler messageHandler,
                           BotConfig config,
                           MenuHandler menuHandler,
                           SecondMenuHandler secondMenuHandler,
                           ProfileHandler profileHandler,
                           ProfileEditMenuHandler profileEditMenuHandler,
                           EditProfileInfo editProfileInfo) {
        this.startHandler = startHandler;
        this.messageHandler = messageHandler;
        this.config = config;
        this.menuHandler = menuHandler;
        this.secondMenuHandler = secondMenuHandler;
        this.profileHandler = profileHandler;
        this.profileEditMenuHandler = profileEditMenuHandler;
        this.editProfileInfo = editProfileInfo;
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

    @MessageRequest
    public String messenger(Update update) {
        return messageHandler.handle(update);
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
        secondMenuHandler.handle(update);
    }

    @CallbackQueryRequest(value = "profile")
    public void profile(Update update) {
        profileHandler.handle(update);
    }

    @CallbackQueryRequest("edit_profile")
    public void editProfile(Update update) {
        profileEditMenuHandler.handle(update);
    }

    @CallbackQueryRequest("change*")
    public void changeProfileInfo(Update update) {
        //return "good";
        editProfileInfo.handle(update);
    }
}
