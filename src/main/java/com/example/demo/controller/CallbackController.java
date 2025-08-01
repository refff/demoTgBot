package com.example.demo.controller;

import com.example.demo.infrastructure.BotConfig;
import com.example.demo.service.pages.menus.MainMenuHandler;
import com.example.demo.service.pages.menus.SecondMenuHandler;
import com.example.demo.service.pages.profile.ProfileEditStateInitializer;
import com.example.demo.service.pages.profile.ProfileEditPageHandler;
import com.example.demo.service.pages.profile.ProfilePageHandler;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.pengrad.telegrambot.model.Update;

import java.util.HashMap;
import java.util.Map;

@BotController
public class CallbackController implements TelegramMvcController {

    private final BotConfig botConfig;

    private SecondMenuHandler secondMenuHandler;
    private ProfilePageHandler profilePageHandler;
    private ProfileEditPageHandler profileEditPageHandler;
    private ProfileEditStateInitializer profileEditStateInitializer;
    private MainMenuHandler mainMenuHandler;


    public static final Map<Long, String> userState = new HashMap<>();

    public CallbackController(BotConfig botConfig,
                              SecondMenuHandler secondMenuHandler,
                              ProfilePageHandler profilePageHandler,
                              ProfileEditPageHandler profileEditPageHandler,
                              ProfileEditStateInitializer profileEditStateInitializer,
                              MainMenuHandler mainMenuHandler) {
        this.botConfig = botConfig;
        this.secondMenuHandler = secondMenuHandler;
        this.profilePageHandler = profilePageHandler;
        this.profileEditPageHandler = profileEditPageHandler;
        this.profileEditStateInitializer = profileEditStateInitializer;
        this.mainMenuHandler = mainMenuHandler;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    @CallbackQueryRequest(value = "mainMenuPage")
    public void back(Update update) {
        mainMenuHandler.handle(update);
    }

    @CallbackQueryRequest(value = "secondMenuPage")
    public void secondMenu(Update update) {
        secondMenuHandler.handle(update);
    }

    @CallbackQueryRequest(value = "action")
    public String action(Update update) {
        return "did something";
    }

    @CallbackQueryRequest(value = "nothing")
    public String nothing(Update update) {
        return "nothing";
    }

    @CallbackQueryRequest(value = "profile")
    public void profile(Update update) {
        profilePageHandler.handle(update);
    }

    @CallbackQueryRequest("edit_profile")
    public void editProfile(Update update) {
        profileEditPageHandler.handle(update);
    }

    @CallbackQueryRequest("change*")
    public void changeProfileInfo(Update update) {
        profileEditStateInitializer.handle(update);
    }
}
