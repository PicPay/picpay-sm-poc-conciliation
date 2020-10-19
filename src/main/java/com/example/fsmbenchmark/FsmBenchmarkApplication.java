package com.example.fsmbenchmark;

import com.example.easy_states.Withdraw;
import com.example.easy_states.events.StoneAnalyseCreditEvent;
import com.example.easy_states.events.StoneHighValueEvent;
import com.example.spring_state_machine.Evento;
import com.example.spring_state_machine.Saque;
import com.example.stateful.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class FsmBenchmarkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FsmBenchmarkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		switch (scanner.nextInt()) {
			case 1:
				this.easyStateFsm();
				break;
			case 2:
				this.springFsm();
				break;
			case 3:
				this.statefulFms();
		}

	}

	private void easyStateFsm() {
		Withdraw withdraw = new Withdraw();
		withdraw.startEvent(new StoneAnalyseCreditEvent());
		withdraw.startEvent(new StoneHighValueEvent());
	}

	private void springFsm() throws Exception {
		Saque umSaque = new Saque(UUID.randomUUID(), BigDecimal.TEN);
		System.out.println(umSaque.obterEstadoAtual());
		System.out.println(umSaque.receberEvento(Evento.STONE_INFORMOU_VALOR_MUITO_ALTO));
		System.out.println(umSaque.receberEvento(Evento.STONE_CREDITADA));
		System.out.println(umSaque.receberEvento(Evento.STONE_INFORMOU_VALOR_MUITO_ALTO));
		System.out.println(umSaque.obterEstadoAtual());

		Saque outroSaque = new Saque(UUID.randomUUID(), BigDecimal.ONE);
		outroSaque.receberEvento(Evento.STONE_CREDITADA);
		System.out.println(outroSaque.obterEstadoAtual());
	}

	private void statefulFms() {
		com.example.stateful.Withdraw withdraw = new com.example.stateful.Withdraw();
		withdraw.executeEvent(Event.STONE_ANALISE_CREDITO);
		withdraw.executeEvent(Event.STONE_CREDITADA);
		withdraw.executeEvent(Event.STONE_INFORMOU_VALOR_MUITO_ALTO);
	}
}
