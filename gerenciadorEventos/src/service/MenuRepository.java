package service;

import java.util.Scanner;

import model.Certificado;
import model.Evento;
import model.Organizador;
import model.Palestrante;
import model.Participante;

public class MenuRepository {
	private Scanner sc;
	private Participante participante;
	private Palestrante palestrante;
	private Organizador organizador;
	private Evento evento;
	private Certificado certificado;

	public MenuRepository(Participante participante, Scanner sc, Evento evento) {
		this.participante = participante;
		this.sc = sc;
		this.evento = evento;
	}

	public MenuRepository(Palestrante palestrante, Scanner sc, Evento evento) {
		this.palestrante = palestrante;
		this.sc = sc;
		this.evento = evento;
	}

	public MenuRepository(Organizador organizador, Scanner sc, Evento evento, Certificado certificado) {
		this.organizador = organizador;
		this.sc = sc;
		this.evento = evento;
		this.certificado = certificado;
	}

	// Método Menu Participante

	public void menuParticipante() {
		boolean running = true;
		while (running) {
			System.out.println("\nBem-vindo, participante " + participante.getNome());
			System.out.println("\n1. Alterar dados cadastrais");
			System.out.println("2. Visualizar eventos USCS e suas atividades");
			System.out.println("3. Realizar inscrição de uma atividade");
			System.out.println("4. Cancelar inscrição");
			System.out.println("5. Visualizar minhas inscrições");
			System.out.println("6. Certificados de participação");
			System.out.println("7. Desconectar");
			System.out.print("\nEscolha uma operação: ");

			int escolhaParticipante = sc.nextInt();
			sc.nextLine();
			switch (escolhaParticipante) {
			case 1:
				System.out.print("\nDigite o novo nome: ");
				String nome = sc.nextLine();

				System.out.print("Digite o novo e-mail: ");
				String email = sc.nextLine();

				System.out.print("Digite o novo telefone: ");
				String telefone = sc.nextLine();

				System.out.print("Digite o novo endereço: ");
				String endereco = sc.nextLine();

				participante.alterarDados(nome, email, telefone, endereco);
				break;
			case 2:
				evento.listarEventosStatic();
				break;
			case 3:
				evento.inscreverParticipanteAtividade(sc, participante);
				break;
			case 4:
				participante.cancelarInscricao(sc);
				break;
			case 5:
				participante.verInscricoes();
				break;
			case 6:
				System.out.println("\nNenhum certificado de participação atribuído");
				break;
			case 7:
				running = false;
				System.out.println("\nEncerrando o programa...");
				break;
			default:
				System.out.println("Escolha inválida.");
			}
		}
	}

	// Método Menu Palestrante

	public void menuPalestrante() {
		boolean running = true;
		while (running) {
			System.out.println("\nBem-vindo, palestrante " + palestrante.getNome());
			System.out.println("\n1. Alterar dados cadastrais");
			System.out.println("2. Visualizar eventos USCS e suas atividades");
			System.out.println("3. Visualizar palestras atribuídas");
			System.out.println("4. Desconectar");
			System.out.print("\nEscolha uma operação: ");

			int escolhaPalestrante = sc.nextInt();
			sc.nextLine();
			switch (escolhaPalestrante) {
			case 1:
				System.out.print("\nDigite o novo nome: ");
				String nome = sc.nextLine();

				System.out.print("Digite o novo e-mail: ");
				String email = sc.nextLine();

				System.out.print("Digite o novo telefone: ");
				String telefone = sc.nextLine();

				System.out.print("Digite o novo endereço: ");
				String endereco = sc.nextLine();

				palestrante.alterarDados(nome, email, telefone, endereco);
				break;
			case 2:
				evento.listarEventosStatic();
				break;
			case 3:
				System.out.println("\nNenhuma palestra atribuída");
				break;
			case 4:
				running = false;
				System.out.println("\nEncerrando o programa...");
				break;
			default:
				System.out.println("Escolha inválida.");
			}
		}
	}

	// Método Menu Organizador

	public void menuOrganizador() {
		boolean running = true;
		while (running) {
			System.out.println("\nBem-vindo, organizador " + organizador.getNome());
			System.out.println("\n1. Alterar dados cadastrais");
			System.out.println("2. Visualizar eventos USCS e suas atividades");
			System.out.println("3. Cadastrar novo evento");
			System.out.println("4. Alterar local-sede de um evento");
			System.out.println("5. Cadastrar nova atividade em um evento");
			System.out.println("6. Remover atividade de um evento");
			System.out.println("7. Atribuir um palestrante a uma atividade");
			System.out.println("8. Exibir a lista de presença para uma determinada atividade");
			System.out.println("9. Emitir o certificado para um determinado participante");
			System.out.println("10. Desconectar");
			System.out.print("\nEscolha uma operação: ");

			int escolhaOrganizador = sc.nextInt();
			sc.nextLine();
			switch (escolhaOrganizador) {
			case 1:
				System.out.print("\nDigite o novo nome: ");
				String nome = sc.nextLine();

				System.out.print("Digite o novo e-mail: ");
				String email = sc.nextLine();

				System.out.print("Digite o novo telefone: ");
				String telefone = sc.nextLine();

				System.out.print("Digite o novo endereço: ");
				String endereco = sc.nextLine();

				organizador.alterarDados(nome, email, telefone, endereco);
				break;
			case 2:
				evento.listarEventos(organizador);
				break;
			case 3:
				evento.cadastrarEvento(sc, organizador);
				break;
			case 4:
				evento.alterarLocalEvento(sc);
				break;
			case 5:
				evento.cadastrarAtividade(sc);
				break;
			case 6:
				evento.removerAtividade(sc);
				break;
			case 7:
				evento.atribuirPalestrante(sc);
				break;
			case 8:
				evento.emitirListaPresenca(sc);
				break;
			case 9:
				certificado.emitirCertificado(sc);
				break;
			case 10:
				running = false;
				System.out.println("\nEncerrando o programa...");
				break;
			default:
				System.out.println("Escolha inválida.");
			}
		}
	}
}
