package com.example.demo.controller;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.handlers.menu.*;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.pengrad.telegrambot.model.Update;

@BotController
public class CallbackController implements TelegramMvcController {

    private final BotConfig botConfig;

    private SecondMenuHandler secondMenuHandler;
    private ProfileHandler profileHandler;
    private ProfileEditMenuHandler profileEditMenuHandler;
    private EditProfileInfo editProfileInfo;
    private MenuHandler menuHandler;

    public CallbackController(BotConfig botConfig,
                              SecondMenuHandler secondMenuHandler,
                              ProfileHandler profileHandler,
                              ProfileEditMenuHandler profileEditMenuHandler,
                              EditProfileInfo editProfileInfo,
                              MenuHandler menuHandler) {
        this.botConfig = botConfig;
        this.secondMenuHandler = secondMenuHandler;
        this.profileHandler = profileHandler;
        this.profileEditMenuHandler = profileEditMenuHandler;
        this.editProfileInfo = editProfileInfo;
        this.menuHandler = menuHandler;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    @CallbackQueryRequest(value = "mainMenuPage")
    public void back(Update update) {
        menuHandler.handle(update);
    }

    @CallbackQueryRequest(value = "action")
    public String action(Update update) {
        return "did something";
    }

    @CallbackQueryRequest(value = "nothing")
    public String nothing(Update update) {
        return "nothing";
    }

    @CallbackQueryRequest(value = "secondMenuPage")
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
        editProfileInfo.handle(update);
    }
}
