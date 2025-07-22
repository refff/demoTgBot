package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;

@Service
public class SecondMenuPageButtonHandler {

    private String headerText = "Second menu page\n\n" +
            "There're also nothing useful, just placeholder text to make message bigger";

    public void handle(Update update) {
        int message_id = update.callbackQuery().message().messageId();
        long chat_id = update.callbackQuery().message().chat().id();

        EditMessageText newMessage = new EditMessageText(chat_id, message_id, headerText);

        var backButton = new InlineKeyboardButton("⬅\uFE0F back").callbackData("menu_main_page");
        var k1 = new InlineKeyboardMarkup()
                .addRow(backButton);

        newMessage.replyMarkup(k1);

        TelegramBot bot = BotConfig.telegramBot;
        bot.execute(newMessage);
    }
}
