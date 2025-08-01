package com.example.demo.service.pages.training.calcs;

import com.example.demo.infrastructure.SendEditMessage;
import com.example.demo.logic.VerkhoshanskyCalc;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerkhoshanskyPlan implements RequestHandler {

    private String textBody;

    private final VerkhoshanskyCalc verkhoshanskyCalc;

    @Autowired
    public VerkhoshanskyPlan(VerkhoshanskyCalc verkhoshanskyCalc) {
        this.verkhoshanskyCalc = verkhoshanskyCalc;
    }

    @Override
    public void handle(Update update) {
        int messageId = update.callbackQuery().message().messageId();
        long chatId = update.callbackQuery().message().chat().id();

        textBody = verkhoshanskyCalc.makePlan(update);

        SendEditMessage.sendMessage(chatId, messageId, textBody, createKeyboard());
    }

    private InlineKeyboardMarkup createKeyboard() {
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("verkhoshansky");

        return new InlineKeyboardMarkup()
                .addRow(backButton);
    }
}
