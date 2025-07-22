package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MenuHandler {

    private InlineKeyboardMarkup keyboard;
    private String menuText = "Some menu text that have no sense cause i need it just to make message wider";

    public void handleCommand(Update update) {
        TelegramBot bot = BotConfig.telegramBot;
        keyboard = assignKeyboard();

        if (update.callbackQuery() == null) {
            SendMessage message = new SendMessage(update.message().chat().id().toString(), menuText);
            message.replyMarkup(keyboard);
            bot.execute(message);
        } else {
            int message_id = update.callbackQuery().message().messageId();
            EditMessageText message = new EditMessageText(update.callbackQuery().message().chat().id().toString(), message_id, menuText);
            message.replyMarkup(keyboard);
            bot.execute(message);
        }
    }

    private InlineKeyboardMarkup assignKeyboard() {
        var profileButton = new InlineKeyboardButton("\uD83D\uDC64 My profile").callbackData("profile");
        var actionButton = new InlineKeyboardButton("action").callbackData("action");
        var nothingButton = new InlineKeyboardButton("nothing").callbackData("nothing");
        var forwardButton = new InlineKeyboardButton("next ➡\uFE0F").callbackData("menuSecondPage");

        return new InlineKeyboardMarkup()
                .addRow(profileButton)
                .addRow(actionButton)
                .addRow(nothingButton)
                .addRow(forwardButton);
    }

}