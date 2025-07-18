package com.example.demo.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class StartHandler implements RequestHandler {

    @Override
    public String handleCommand(Update update) {
        return "";
    }
}
