package com.example.easy_states.factories;

import com.example.easy_states.actions.CancelWithdraw;
import com.example.easy_states.events.StoneAnalyseCreditEvent;
import com.example.easy_states.events.StoneCreditedEvent;
import com.example.easy_states.events.StoneHighValueEvent;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.State;
import org.jeasy.states.api.Transition;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.jeasy.states.core.TransitionBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WithdrawStateMachineFactory {
    public static FiniteStateMachine factory(UUID correlationId) {
        State created = new State("Criado");
        State intermediate = new State("Intermediario");
        State realized = new State("Realizado");
        State canceled = new State("Cancelado");

        Set<State > allStates = new HashSet<>();
        allStates.add(created);
        allStates.add(intermediate);
        allStates.add(realized);
        allStates.add(canceled);

        Set<State> finalStates = new HashSet<>();
        finalStates.add(realized);
        finalStates.add(canceled);

        Transition stoneAnalysing = new TransitionBuilder()
                .name("stone_analyses")
                .sourceState(created)
                .eventType(StoneAnalyseCreditEvent.class)
                .targetState(intermediate)
                .build();

        Transition stoneApproved = new TransitionBuilder()
                .name("stone_approved")
                .sourceState(intermediate)
                .eventType(StoneCreditedEvent.class)
                .targetState(realized)
                .build();

        Transition stoneRejected = new TransitionBuilder()
                .name("stone_rejected")
                .sourceState(intermediate)
                .eventType(StoneHighValueEvent.class)
                .eventHandler(new CancelWithdraw(correlationId))
                .targetState(canceled)
                .build();

        return new FiniteStateMachineBuilder(allStates, created)
                .registerTransition(stoneAnalysing)
                .registerTransition(stoneApproved)
                .registerTransition(stoneRejected)
                .registerFinalStates(finalStates)
                .build();
    }
}
