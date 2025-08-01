package com.example.demo.service.pages.menus;

import com.example.demo.infrastructure.SendEditMessage;
import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

@Service
public class SecondMenuHandler implements RequestHandler {

    private final String textBody = """
            Second menu page
            
            There're also nothing useful, just placeholder text to make message bigger""";

    public void handle(Update update) {
        int messageId = update.callbackQuery().message().messageId();
        long chatId = update.callbackQuery().message().chat().id();

        SendEditMessage.sendMessage(chatId, messageId, textBody, createKeyboard());
    }

    private InlineKeyboardMarkup createKeyboard() {
        var backButton = new InlineKeyboardButton("⬅️ back").callbackData("mainMenuPage");

        return new InlineKeyboardMarkup()
                .addRow(backButton);
    }
}
