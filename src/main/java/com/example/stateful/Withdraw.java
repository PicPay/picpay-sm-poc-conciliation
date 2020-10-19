package com.example.stateful;

import com.github.zevada.stateful.StateMachine;
import com.github.zevada.stateful.StateMachineBuilder;
import com.github.zevada.stateful.UnexpectedEventTypeException;

public class Withdraw {
    private StateMachine<State, Event> stateMachine;

    public Withdraw() {
        this.createStateMachine();
    }

    private void createStateMachine()
    {
        this.stateMachine =
                new StateMachineBuilder<State, Event>(State.CRIADO)
                        .addTransition(State.CRIADO, Event.STONE_ANALISE_CREDITO, State.INTERMEDIARIO)
                        .addTransition(State.INTERMEDIARIO, Event.STONE_CREDITADA, State.REALIZADO)
                        .addTransition(State.INTERMEDIARIO, Event.STONE_INFORMOU_VALOR_MUITO_ALTO, State.CANCELADO)
                        .onEnter(State.CRIADO, () -> System.out.println("Saque Criado!"))
                        .onEnter(State.INTERMEDIARIO, () -> System.out.println("Analisando Crédito - Status INTERMEDIARIO!"))
                        .onEnter(State.REALIZADO, () -> System.out.println("Crédito Aprovado - Status REALIZADO!"))
                        .onEnter(State.CANCELADO, () -> System.out.println("Crédito Reprovado - Status CANCELADO!"))
                        .build();
    }

    public void executeEvent(Event event)
    {
        try {
            this.stateMachine.apply(event);
            System.out.println(this.stateMachine.getState().name());
        } catch (UnexpectedEventTypeException exception) {
            System.out.println("Evento não permitido para o estado atual do saque");
            System.out.println(this.stateMachine.getState().name());
        }

    }
}
