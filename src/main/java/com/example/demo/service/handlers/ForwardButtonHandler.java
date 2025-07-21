package com.example.demo.service.handlers;

import com.example.demo.configuration.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;

@Service
public class ForwardButtonHandler {

    public void handle(Update update) {
        //String call_data = update.callbackQuery().data();
        int message_id = update.callbackQuery().message().messageId();
        long chat_id = update.callbackQuery().message().chat().id();

        String answer = "new menu's page";
        EditMessageText newMessage = new EditMessageText(chat_id, message_id, answer);

        var backButton = new InlineKeyboardButton("back").callbackData("menu_main_page");
        var k1 = new InlineKeyboardMarkup()
                .addRow(backButton);

        newMessage.replyMarkup(k1);

        TelegramBot bot = BotConfig.telegramBot;
        bot.execute(newMessage);
    }
}
