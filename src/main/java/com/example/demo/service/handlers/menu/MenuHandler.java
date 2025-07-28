package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MenuHandler implements RequestHandler {

    private InlineKeyboardMarkup keyboard;
    private String textBody = "Some menu text that have no sense, i need it just to make message wider";

    public void handle(Update update) {
        TelegramBot bot = BotConfig.telegramBot;
        keyboard = assignKeyboard();

        if (update.callbackQuery() == null) {
            var message = new SendMessage(update.message().chat().id().toString(), textBody);
            message.replyMarkup(keyboard);
            bot.execute(message);
        } else {
            int message_id = update.callbackQuery().message().messageId();
            var message = new EditMessageText(update.callbackQuery().message().chat().id().toString(), message_id, textBody);
            message.replyMarkup(keyboard);
            bot.execute(message);
        }
    }

    private InlineKeyboardMarkup assignKeyboard() {
        var profileButton = new InlineKeyboardButton("\uD83D\uDC64 My profile").callbackData("profile");
        var actionButton = new InlineKeyboardButton("action").callbackData("action");
        var nothingButton = new InlineKeyboardButton("nothing").callbackData("nothing");
        var forwardButton = new InlineKeyboardButton("next ➡️").callbackData("secondMenuPage");

        return new InlineKeyboardMarkup()
                .addRow(profileButton)
                .addRow(actionButton, nothingButton)
                .addRow()
                .addRow(forwardButton);
    }
}