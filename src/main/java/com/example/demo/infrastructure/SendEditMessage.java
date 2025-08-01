package com.example.demo.infrastructure;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;


@Service
public class SendEditMessage {

    public static void sendMessage(long chatId, int messageId, String textBody, InlineKeyboardMarkup keyboard) {
        EditMessageText newMessage = new EditMessageText(chatId, messageId, textBody).parseMode(ParseMode.Markdown);
        newMessage.replyMarkup(keyboard);

        BotConfig.telegramBot.execute(newMessage);
    }
}
