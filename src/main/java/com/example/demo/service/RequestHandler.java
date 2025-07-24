package com.example.demo.service;

import com.pengrad.telegrambot.model.Update;

public interface RequestHandler {

    void handle(Update update);
}
