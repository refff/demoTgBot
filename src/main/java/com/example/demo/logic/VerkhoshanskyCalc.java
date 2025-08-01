package com.example.demo.logic;

import com.example.demo.entities.UserDataLoader;
import com.example.demo.entities.UserPRs;
import com.example.demo.entities.UserProfile;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

@Component
public class VerkhoshanskyCalc {

    private final UserDataLoader userDataLoader;
    private double max;

    public VerkhoshanskyCalc(UserDataLoader userDataLoader) {
        this.userDataLoader = userDataLoader;
    }

    public String makePlan(Update update) {
        String movement = update.callbackQuery().data().substring(17);

        UserProfile user = userDataLoader.loadUserProfile(update);
        UserPRs prs = userDataLoader.loadUserPRs(user);

        switch (movement) {
            case "Deadlift" -> max = Double.parseDouble(prs.getDeadlift());
            case "BenchPress" -> max = Double.parseDouble(prs.getBenchPress());
            case "Squat" ->  max = Double.parseDouble(prs.getSquat());
        }

        return doCalculations();
    }

    private String doCalculations() {
        return weekOne() +
                weekTwo() +
                weekThree() +
                weekFour() +
                weekFive() +
                weekSix();
    }

    private String weekOne() {
        String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w1 =  String.format("%.1f x 6; ", max * 0.65);
        String rep4w1 =  String.format("%.1f x 6;6;6; ", max * 0.70);
        String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1;

        String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w2 =  String.format("%.1f x 5; ", max * 0.75);
        String rep5w2 =  String.format("%.1f x 5;5;5; ", max * 0.8);
        String rep6w2 =  String.format("%.1f x 5; ", max * 0.75);
        String rep7w2 =  String.format("%.1f x 8; ", max * 0.65);
        String rep8w2 =  String.format("%.1f x 12; ", max * 0.55);
        String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2 + rep7w2 + rep8w2;

        return "Week 1: \n" + w1 + "\n\n" + w2 + "\n\n\n";
    }

    private String weekTwo() {
        String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w1 =  String.format("%.1f x 6; ", max * 0.65);
        String rep4w1 =  String.format("%.1f x 5; ", max * 0.70);
        String rep5w1 =  String.format("%.1f x 5;5; ", max * 0.75);
        String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1;

        String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w2 =  String.format("%.1f x 4; ", max * 0.75);
        String rep5w2 =  String.format("%.1f x 4; ", max * 0.8);
        String rep6w2 =  String.format("%.1f x 4;4;4; ", max * 0.85);
        String rep7w2 =  String.format("%.1f x 5; ", max * 0.8);
        String rep8w2 =  String.format("%.1f x 10; ", max * 0.60);
        String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2 + rep7w2 + rep8w2;

        return "Week 2: \n" + w1 + "\n\n" + w2 + "\n\n\n";
    }

    private String weekThree() {
            String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
            String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
            String rep3w1 =  String.format("%.1f x 5; ", max * 0.65);
            String rep4w1 =  String.format("%.1f x 4;", max * 0.70);
            String rep5w1 =  String.format("%.1f x 3; ", max * 0.75);
            String rep6w1 =  String.format("%.1f x 3; ", max * 0.8);
            String rep7w1 =  String.format("%.1f x 3; ", max * 0.85);
            String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1 +  rep5w1 + rep6w1 + rep7w1;

            String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
            String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
            String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
            String rep4w2 =  String.format("%.1f x 4; ", max * 0.75);
            String rep5w2 =  String.format("%.1f x 3; ", max * 0.85);
            String rep6w2 =  String.format("%.1f x 3;3; ", max * 0.9);
            String rep7w2 =  String.format("%.1f x 5; ", max * 0.8);
            String rep8w2 =  String.format("%.1f x 10; ", max * 0.60);
            String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2 + rep7w2 + rep8w2;

            return "Week 3: \n" + w1 + "\n\n" + w2 + "\n\n\n";
    }

    private String weekFour() {
        String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w1 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w1 =  String.format("%.1f x 4; ", max * 0.75);
        String rep5w1 =  String.format("%.1f x 3;3; ", max * 0.85);
        String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1 + rep4w1 + rep5w1;

        String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w2 =  String.format("%.1f x 4; ", max * 0.75);
        String rep5w2 =  String.format("%.1f x 2; ", max * 0.85);
        String rep6w2 =  String.format("%.1f x 2; ", max * 0.9);
        String rep7w2 =  String.format("%.1f x 2; ", max * 0.95);
        String rep8w2 =  String.format("%.1f x 6; ", max * 0.75);
        String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2 + rep7w2 + rep8w2;

        return "Week 4: \n" + w1 + "\n\n" + w2 + "\n\n\n";
}

    private String weekFive() {
        String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w1 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w1 =  String.format("%.1f x 5;5;", max * 0.75);
        String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1;

        String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w2 =  String.format("%.1f x 3; ", max * 0.75);
        String rep5w2 =  String.format("%.1f x 3; ", max * 0.80);
        String rep6w2 =  String.format("%.1f x 2; ", max * 0.85);
        String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2;

        return "Week 5: \n" + w1 + "\n\n" + w2 + "\n\n\n";
    }

    private String weekSix() {
        String rep1w1 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w1 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w1 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w1 =  String.format("%.1f x 3;", max * 0.75);
        String rep5w1 =  String.format("%.1f x 2;2; ", max * 0.8);
        String w1 = "1st workout: " + rep1w1 + rep2w1 + rep3w1 + rep4w1 +  rep5w1;

        String rep1w2 =  String.format("%.1f x 10; ", max * 0.45);
        String rep2w2 =  String.format("%.1f x 8; ", max * 0.55);
        String rep3w2 =  String.format("%.1f x 5; ", max * 0.65);
        String rep4w2 =  String.format("%.1f x 3; ", max * 0.75);
        String rep5w2 =  String.format("%.1f x 2; ", max * 0.85);
        String rep6w2 =  String.format("%.1f x 1; ", max * 0.9);
        String rep7w2 =  String.format("%.1f x 1; ", max * 0.95);
        String rep8w2 =  String.format("%.1f x 1; ", max);
        String rep9w2 =  String.format("%.1f x 1; ", max * 1.02);
        String rep10w2 =  String.format("%.1f x 1; ", max * 1.05);
        String w2 = "2nd workout: " + rep1w2 + rep2w2 + rep3w2 + rep4w2 + rep5w2 + rep6w2 + rep7w2 + rep8w2 +  rep9w2 + rep10w2;

        return "Week 6: \n" + w1 + "\n\n" + w2 + "\n\n\n";
    }
}
