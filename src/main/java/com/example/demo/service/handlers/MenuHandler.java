package com.example.demo.service.handlers;

import com.example.demo.configuration.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MenuHandler {

    private InlineKeyboardMarkup keyboard;

    public void handleCommand(Update update) {
        keyboard = assignKeyboard();

        BaseRequest message;

        if (update.callbackQuery() != null) {
            message = new SendMessage(update.callbackQuery().message().chat().id().toString(), "hello menu").replyMarkup(keyboard);
        } else {
            message = new EditMessageText(update.message().chat().id().toString(), "hello menu").replyMarkup(keyboard);
        }

        TelegramBot bot = BotConfig.telegramBot;
        bot.execute(message);
    }

    private InlineKeyboardMarkup assignKeyboard() {
        var actionButton = new InlineKeyboardButton("action").callbackData("action");
        var nothingButton = new InlineKeyboardButton("nothing").callbackData("nothing");
        var forwardButton = new InlineKeyboardButton("->").callbackData("forward");

        return new InlineKeyboardMarkup()
                .addRow(actionButton)
                .addRow(nothingButton)
                .addRow(forwardButton);
    }

}