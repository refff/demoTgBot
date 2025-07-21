package com.example.demo.service.handlers;

import com.example.demo.service.RequestHandler;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler implements RequestHandler {

    @Override
    public String handleCommand(Update update) {
        return update.message().text();
    }
}
