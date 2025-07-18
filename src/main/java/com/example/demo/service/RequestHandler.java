package com.example.demo.service;

import com.pengrad.telegrambot.model.Update;

public interface RequestHandler {
    String handleCommand(Update update);
}
