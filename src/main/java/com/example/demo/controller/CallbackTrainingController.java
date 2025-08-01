package com.example.demo.controller;

import com.example.demo.infrastructure.BotConfig;
import com.example.demo.service.pages.training.MainTrainingPage;
import com.example.demo.service.pages.training.calcs.VerkhoshanskyPlan;
import com.example.demo.service.pages.training.plans.VerkhoshanskyMovements;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.request.CallbackQueryRequest;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;

@BotController
public class CallbackTrainingController implements TelegramMvcController {

    private final BotConfig botConfig;

    private MainTrainingPage trainingPage;
    private VerkhoshanskyMovements verkhoshanskyMovements;
    private VerkhoshanskyPlan verkhoshanskyPlan;

    @Autowired
    public CallbackTrainingController(BotConfig botConfig,
                                      MainTrainingPage trainingPage,
                                      VerkhoshanskyMovements verkhoshanskyMovements,
                                      VerkhoshanskyPlan verkhoshanskyPlan) {
        this.botConfig = botConfig;
        this.trainingPage = trainingPage;
        this.verkhoshanskyMovements = verkhoshanskyMovements;
        this.verkhoshanskyPlan = verkhoshanskyPlan;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    @CallbackQueryRequest("training")
    public void training(Update update) {
        trainingPage.handle(update);
    }

    @CallbackQueryRequest("verkhoshansky")
    public void verkhoshansky(Update update) {
        verkhoshanskyMovements.handle(update);
    }

    @CallbackQueryRequest("VerkhoshanskyPlan*")
    public void verkhoshanskyPlan(Update update) {
        verkhoshanskyPlan.handle(update);
    }
}
