package com.example.demo.service.commands;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;

@Service
public class StartHandler{

    public String handle(Update update) {
        return "Hi, that's my bot, you're welcome";
    }
}
