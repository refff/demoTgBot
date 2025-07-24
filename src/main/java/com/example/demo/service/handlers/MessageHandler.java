package com.example.demo.service.handlers;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

    public String handle(Update update) {
        return update.message().text();
    }
}
