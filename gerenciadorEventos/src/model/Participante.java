package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Participante extends Pessoa {

	private List<Atividade> inscricoes;
	private static ArrayList<Participante> listaParticipante = new ArrayList<>();

	public Participante(String nome, String email, String telefone, String endereco, String inscricoes) {
		super(nome, email, telefone, endereco);
		this.inscricoes = new ArrayList<>();
	}

	// Método de Cadastro

	public void cadastrarParticipante(Scanner sc) {

		System.out.print("\nDigite o nome do participante: ");
		String nome = sc.nextLine();

		System.out.print("Digite o e-mail do participante: ");
		String email = sc.nextLine();

		System.out.print("Digite o telefone do participante: ");
		String telefone = sc.nextLine();

		System.out.print("Digite o endereco do participante: ");
		String endereco = sc.nextLine();

		Participante novoParticipante = new Participante(nome, email, telefone, endereco, endereco);
		listaParticipante.add(novoParticipante);

		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setEndereco(endereco);

		System.out.println("\nParticipante cadastrado com sucesso!");
		System.out.println("Nome: " + this.getNome());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Telefone: " + this.getTelefone());
		System.out.println("Endereco: " + this.getEndereco());

	}

	// Método Alterar Dados

	@Override
	public void alterarDados(String nome, String email, String telefone, String endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setEndereco(endereco);

	}

	// Método Ver Inscrições

	public void verInscricoes() {
		System.out.println("Inscrições de " + getNome() + ":\n");

		boolean inscricaoEncontrada = false;
		for (Evento evento : Evento.getListaEventos()) {
			for (Atividade atividade : evento.getAtividades()) {
				if (atividade.getParticipantes().contains(this)) {
					if (!inscricaoEncontrada) {
						inscricaoEncontrada = true;
					}

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String dataAtividadeFormatada = sdf.format(atividade.getData());

					System.out.println("Evento: " + evento.getNome());
					System.out.println("  - Atividade: " + atividade.getNomeAtividade());
					System.out.println("  - Data: " + dataAtividadeFormatada);
					System.out.println("  - Horário: " + atividade.getHorario());
					System.out.println("  - Local: " + atividade.getEspaco());
					System.out.println("--------------------------------");
				}
			}
		}
		if (!inscricaoEncontrada) {
			System.out.println("Nenhuma inscrição encontrada.");
		}
	}

	// Método Cancelar Inscrição

	public void cancelarInscricao(Scanner sc) {
		boolean encontrouInscricao = false;
		List<Atividade> atividadesInscritas = new ArrayList<>();

		for (Evento evento : Evento.getListaEventos()) {
			for (Atividade atividade : evento.getAtividades()) {
				if (atividade.getParticipantes().contains(this)) {
					atividadesInscritas.add(atividade);
					encontrouInscricao = true;
					System.out.println("\nAtividades inscritas:\n");
					System.out.println(atividadesInscritas.size() + ". " + atividade.getNomeAtividade() + " - Evento: "
							+ evento.getNome());
				}
			}
		}

		if (!encontrouInscricao) {
			System.out.println("\nVocê não está inscrito em nenhuma atividade.");
			return;
		}

		System.out.print("\nEscolha o número da atividade que deseja cancelar: ");
		int escolha;

		try {
			escolha = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida. Por favor, digite um número.");
			return;
		}

		if (escolha < 1 || escolha > atividadesInscritas.size()) {
			System.out.println("Opção inválida.");
			return;
		}

		Atividade atividadeEscolhida = atividadesInscritas.get(escolha - 1);
		atividadeEscolhida.getParticipantes().remove(this);
		this.inscricoes.remove(atividadeEscolhida);

		System.out.println("Inscrição cancelada com sucesso na atividade: " + atividadeEscolhida.getNomeAtividade());
	}

	public List<Atividade> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Atividade> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public static ArrayList<Participante> getListaParticipante() {
		return listaParticipante;
	}

	public void exibirDados() {
		System.out.println("Nome: " + getNome());
		System.out.println("E-mail: " + getEmail());
		System.out.println("Telefone: " + getTelefone());
		System.out.println("Endereço: " + getEndereco());

	}

	// Participantes Pré-Criados Para Testes Unitários

	static {
		listaParticipante.add(new Participante("João Silva", "joao@gmail.com", "1196874530", "Rua A, 123", null));
		listaParticipante.add(new Participante("Maria Oliveira", "maria@gmail.com", "1198765230", "Rua B, 456", null));
		listaParticipante.add(new Participante("Carlos Costa", "carlos@gmail.com", "11965423768", "Rua C, 789", null));
		listaParticipante.add(new Participante("Ana Santos", "ana@gmail.com", "11963215201", "Rua D, 101", null));
		listaParticipante.add(new Participante("Pedro Almeida", "pedro@gmail.com", "11942156720", "Rua E, 202", null));
		listaParticipante.add(new Participante("Julia Lima", "julia@gmail.com", "11965478530", "Rua F, 303", null));
		listaParticipante.add(new Participante("Fernando Ribeiro", "fernando@gmail.com", "11954632576", "Rua G, 404", null));
		listaParticipante.add(new Participante("Roberta Rocha", "roberta@gmail.com", "11965423879", "Rua H, 505", null));
		listaParticipante.add(new Participante("Lucas Martins", "lucas@gmail.com", "11965321546", "Rua I, 606", null));
		listaParticipante.add(new Participante("Patricia Souza", "patricia@gmail.com", "11953246015", "Rua J, 707", null));
	}

}
