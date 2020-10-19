package com.example.easy_states.actions;

import org.jeasy.states.api.Event;
import org.jeasy.states.api.EventHandler;

public class CancelWithdraw implements EventHandler {
    @Override
    public void handleEvent(Event event) throws Exception {
        System.out.println("*********Fazendo alguma ação***********");
    }
}
