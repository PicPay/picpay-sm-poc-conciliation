package com.example.easy_states;

import com.example.easy_states.factories.WithdrawStateMachineFactory;
import org.jeasy.states.api.AbstractEvent;
import org.jeasy.states.api.FiniteStateMachine;
import java.util.UUID;

public class Withdraw {
    private final FiniteStateMachine stateMachine;
    private final UUID correlationId;

    public Withdraw(UUID correlationId) {
        this.correlationId = correlationId;
        this.stateMachine = WithdrawStateMachineFactory.factory(correlationId);

        System.out.println("Estado atual: " + this.stateMachine.getCurrentState().getName());
    }

    public void startEvent(AbstractEvent event)
    {
        try {
            System.out.println("De: " + this.stateMachine.getCurrentState().getName());
            this.stateMachine.fire(event);
            System.out.println("Para: " + this.stateMachine.getCurrentState().getName());
        } catch (Exception exception) {
            System.out.println("Erro FSM: " + exception.getMessage());
        }
    }
}
