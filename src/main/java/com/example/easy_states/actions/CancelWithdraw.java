package com.example.easy_states.actions;

import org.jeasy.states.api.Event;
import org.jeasy.states.api.EventHandler;

import java.util.UUID;

public class CancelWithdraw implements EventHandler {
    private UUID correlationId;

    public CancelWithdraw(UUID correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public void handleEvent(Event event) throws Exception {
        System.out.println("********* Cancelando saque: " + this.correlationId + " ***********");
    }
}
