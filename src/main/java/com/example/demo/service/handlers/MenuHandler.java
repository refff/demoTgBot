package com.example.demo.service.handlers;

import com.example.demo.congiguration.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MenuHandler {

    public void handleCommand(Update update) {

        var backButton = new InlineKeyboardButton("back").callbackData("back");
        var actionButton = new InlineKeyboardButton("action").callbackData("action");
        var nothingButton = new InlineKeyboardButton("nothing").callbackData("nothing");
        var forwardButton = new InlineKeyboardButton("->").callbackData("forward");

        Keyboard k1 = new InlineKeyboardMarkup()
                .addRow(actionButton)
                .addRow(nothingButton)
                .addRow(backButton,  forwardButton);


        SendMessage message = new SendMessage(update.message().chat().id(), "hello menu");
        message.replyMarkup(k1);

        TelegramBot bot = BotConfig.telegramBot;
        bot.execute(message);
    }
}
