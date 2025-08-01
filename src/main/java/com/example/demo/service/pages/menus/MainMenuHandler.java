package com.example.demo.service.pages.menus;

import com.example.demo.infrastructure.BotConfig;
import com.example.demo.infrastructure.SendEditMessage;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MainMenuHandler implements RequestHandler {

    private InlineKeyboardMarkup keyboard;
    private String textBody = "Some menu text that have no sense, i need it just to make message wider";

    @Override
    public void handle(Update update) {
        keyboard = createKeyboard();

        if (update.callbackQuery() == null) {
            sendMessage(update);
        } else {
            editMessage(update);
        }
    }

    private InlineKeyboardMarkup createKeyboard() {
        var profileButton = new InlineKeyboardButton("\uD83D\uDC64 My profile").callbackData("profile");
        var trainingButton = new  InlineKeyboardButton("\uD83C\uDFCB Training").callbackData("training");
        var actionButton = new InlineKeyboardButton("action").callbackData("action");
        var nothingButton = new InlineKeyboardButton("nothing").callbackData("nothing");
        var forwardButton = new InlineKeyboardButton("next ➡️").callbackData("secondMenuPage");

        return new InlineKeyboardMarkup()
                .addRow(profileButton)
                .addRow(trainingButton)
                .addRow(actionButton, nothingButton)
                .addRow()
                .addRow(forwardButton);
    }

    //???
    private void sendMessage(Update update) {
        var message = new SendMessage(update.message().chat().id().toString(), textBody);
        message.replyMarkup(keyboard);

        BotConfig.telegramBot.execute(message);
    }

    private void editMessage(Update update) {
        int messageId = update.callbackQuery().message().messageId();
        long chatId = update.callbackQuery().message().chat().id();

        SendEditMessage.sendMessage(chatId, messageId, textBody, keyboard);
    }
}