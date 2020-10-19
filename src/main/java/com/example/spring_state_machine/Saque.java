package com.example.spring_state_machine;

import org.springframework.statemachine.StateMachine;

import java.math.BigDecimal;
import java.util.UUID;

public class Saque {

    private UUID correlationId;
    private BigDecimal valor;
    private Estado estado;

    private StateMachine<Estado, Evento> maquinaDeEstados;

    protected Saque(){}

    public Saque(UUID correlationId, BigDecimal valor) throws Exception {
        this.correlationId = correlationId;
        this.valor = valor;
        this.maquinaDeEstados = MaquinaDeEstadosFactory.construir();
        this.maquinaDeEstados.sendEvent(Evento.NOVO);
        this.maquinaDeEstados.start();
    }

    public void receberEvento(StateMachine<Estado, Evento> maquinaDeEstado, Evento evento){
        //this.estado = Estado.REALIZADO;
        maquinaDeEstados.sendEvent(evento);
    }

    public boolean receberEvento(Evento evento){
        return maquinaDeEstados.sendEvent(evento);
    }

    public Estado obterEstadoAtual(){
        return maquinaDeEstados.getState().getId();
    }
}
