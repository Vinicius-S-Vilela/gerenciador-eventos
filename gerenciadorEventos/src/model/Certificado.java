package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Certificado {

	// Método Emitir Certificado

	public void emitirCertificado(Scanner sc) {
		if (Participante.getListaParticipante().isEmpty()) {
			System.out.println("Nenhum aluno cadastrado no sistema.");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

		System.out.println("\nEscolha o aluno para emissão do certificado:\n");
		for (int i = 0; i < Participante.getListaParticipante().size(); i++) {
			Participante participante = Participante.getListaParticipante().get(i);
			System.out.println((i + 1) + " - " + participante.getNome() + " (E-mail: " + participante.getEmail() + ")");
		}

		int alunoEscolhido = -1;
		while (alunoEscolhido < 1 || alunoEscolhido > Participante.getListaParticipante().size()) {
			System.out.print("\nDigite o número do aluno: ");
			try {
				alunoEscolhido = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Participante alunoSelecionado = Participante.getListaParticipante().get(alunoEscolhido - 1);

		List<Evento> eventosAluno = new ArrayList<>();
		for (Evento evento : Evento.getListaEventos()) {
			for (Atividade atividade : evento.getAtividades()) {
				if (atividade.getParticipantes().contains(alunoSelecionado)) {
					eventosAluno.add(evento);
					break;
				}
			}
		}

		if (eventosAluno.isEmpty()) {
			System.out.println("O aluno não está inscrito em nenhum evento.");
			return;
		}

		System.out.println("\nEscolha o evento onde o aluno está inscrito para emissão do certificado:\n");
		for (int i = 0; i < eventosAluno.size(); i++) {
			Evento evento = eventosAluno.get(i);

			String dataInicioFormatada = sdf.format(evento.getDataInicio());
			String dataFimFormatada = sdf.format(evento.getDataFim());

			System.out.println((i + 1) + " - " + evento.getNome() + " (de " + dataInicioFormatada + " a "
					+ dataFimFormatada + ")");
		}

		int eventoEscolhido = -1;
		while (eventoEscolhido < 1 || eventoEscolhido > eventosAluno.size()) {
			System.out.print("\nDigite o número do evento: ");
			try {
				eventoEscolhido = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = eventosAluno.get(eventoEscolhido - 1);

		List<Atividade> atividadesAluno = new ArrayList<>();
		for (Atividade atividade : eventoSelecionado.getAtividades()) {
			if (atividade.getParticipantes().contains(alunoSelecionado)) {
				atividadesAluno.add(atividade);
			}
		}

		if (atividadesAluno.isEmpty()) {
			System.out.println("O aluno não está inscrito em nenhuma atividade deste evento.");
			return;
		}

		System.out.println("\nEscolha a atividade do evento onde o aluno participou:\n");
		for (int i = 0; i < atividadesAluno.size(); i++) {
			Atividade atividade = atividadesAluno.get(i);

			String dataAtividadeFormatada = sdf.format(atividade.getData());

			System.out.println((i + 1) + " - " + atividade.getNomeAtividade() + " em " + dataAtividadeFormatada + " às "
					+ atividade.getHorario());
		}

		int atividadeEscolhida = -1;
		while (atividadeEscolhida < 1 || atividadeEscolhida > atividadesAluno.size()) {
			System.out.print("\nDigite o número da atividade: ");
			try {
				atividadeEscolhida = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Atividade atividadeSelecionada = atividadesAluno.get(atividadeEscolhida - 1);

		System.out.println("\nEmitindo certificado para " + alunoSelecionado.getNome() + ":\n");
		System.out.println("Certificado de participação na atividade: " + atividadeSelecionada.getNomeAtividade());
		String dataAtividadeFormatada = sdf.format(atividadeSelecionada.getData());
		System.out.println("Data da Atividade: " + dataAtividadeFormatada + " às " + atividadeSelecionada.getHorario());
		System.out.println("Espaço: " + atividadeSelecionada.getEspaco());
		System.out.println("Participante: " + alunoSelecionado.getNome());
		System.out.println("\nCertificado emitido com sucesso!");
	}
}