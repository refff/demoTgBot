package com.example.demo.service.handlers.pages.training.plans;

import com.example.demo.infrastructure.SendEditMessage;
import com.example.demo.logic.VerkhoshanskyCalc;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerkhoshanskyMovements implements RequestHandler {

    private String textBody = "Choose a movement to create a workout plan";

    @Override
    public void handle(Update update) {
        int messageId = update.callbackQuery().message().messageId();
        long chatId = update.callbackQuery().message().chat().id();

        SendEditMessage.sendMessage(chatId, messageId, textBody, createKeyboard());
    }

    private InlineKeyboardMarkup createKeyboard() {
        var deadlift = new InlineKeyboardButton("deadlift").callbackData("VerkhoshanskyPlanDeadlift");
        var benchPress = new InlineKeyboardButton("benchPress").callbackData("VerkhoshanskyPlanBenchPress");
        var squat = new InlineKeyboardButton("squat").callbackData("VerkhoshanskyPlanSquat");
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("training");

        return new InlineKeyboardMarkup()
                .addRow(deadlift)
                .addRow(benchPress)
                .addRow(squat)
                .addRow(backButton);
    }
}
