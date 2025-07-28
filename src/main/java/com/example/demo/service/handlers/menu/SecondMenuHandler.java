package com.example.demo.service.handlers.menu;

import com.example.demo.configuration.BotConfig;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;

@Service
public class SecondMenuHandler implements RequestHandler {

    private String headerText;
    private final String bodyText = """
            Second menu page
            
            There're also nothing useful, just placeholder text to make message bigger""";

    public void handle(Update update) {
        int message_id = update.callbackQuery().message().messageId();
        long chat_id = update.callbackQuery().message().chat().id();

        EditMessageText newMessage = new EditMessageText(chat_id, message_id, bodyText);

        newMessage.replyMarkup(createKeyboard());

        TelegramBot bot = BotConfig.telegramBot;
        bot.execute(newMessage);
    }

    private InlineKeyboardMarkup createKeyboard() {
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("menu_main_page");
        return new InlineKeyboardMarkup()
                .addRow(backButton);
    }
}
