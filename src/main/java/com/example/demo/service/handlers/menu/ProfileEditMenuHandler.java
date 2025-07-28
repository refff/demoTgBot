package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;

@Service
public class ProfileEditMenuHandler implements RequestHandler {

    private String headerText = "Edit Profile\n";
    private String bodyText = "\nWhich value do you want to change?";

    private EditMessageText messageResponse;

    @Override
    public void handle(Update update) {
        long chatId = update.callbackQuery().message().chat().id();
        int messageId = update.callbackQuery().message().messageId();

        messageResponse = new EditMessageText(chatId, messageId, headerText + bodyText);
        messageResponse.replyMarkup(createKeyboard());

        BotConfig.telegramBot.execute(messageResponse);
    }

    private InlineKeyboardMarkup createKeyboard() {
        var deadlift = new  InlineKeyboardButton("deadlift").callbackData("changeDeadlift");
        var benchPress = new  InlineKeyboardButton("bench press").callbackData("changeBenchPress");
        var squat = new  InlineKeyboardButton("squat").callbackData("changeSquat");
        var personality = new  InlineKeyboardButton("personality").callbackData("changePersonality");
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("profile");
        return new InlineKeyboardMarkup()
                .addRow(deadlift)
                .addRow(benchPress)
                .addRow(squat)
                .addRow(personality)
                .addRow(backButton);
    }
}
