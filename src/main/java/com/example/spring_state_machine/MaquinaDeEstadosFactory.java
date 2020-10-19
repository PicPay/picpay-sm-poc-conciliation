package com.example.spring_state_machine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

public class MaquinaDeEstadosFactory {


    public static final StateMachine<Estado, Evento> construir() throws Exception {
        StateMachineBuilder.Builder<Estado, Evento> builder = StateMachineBuilder.builder();
        builder
                .configureTransitions()
                .withExternal()
                .source(Estado.CRIADO).target(Estado.REALIZADO).event(Evento.STONE_CREDITADA)
                .and()
                .withExternal()
                .source(Estado.CRIADO).target(Estado.CANCELADO).event(Evento.STONE_INFORMOU_VALOR_MUITO_ALTO);

        builder
                .configureStates()
                .withStates()
                .initial(Estado.CRIADO)
                .states(EnumSet.allOf(Estado.class));
                return builder.build();
    }
}
