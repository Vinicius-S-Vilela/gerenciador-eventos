package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Evento {

	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private Organizador organizador;
	private String local;
	private List<Atividade> atividades;

	private static ArrayList<Evento> listaEventos = new ArrayList<>();

	public Evento(String nome, Date dataInicio, Organizador organizador, Date dataFim, String local) {
		this.nome = nome;
		this.organizador = organizador;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.local = local;
		this.atividades = new ArrayList<>();

		listaEventos.add(this);
	}

	public Evento() {
		this.atividades = new ArrayList<>();
	}

	// Método Cadastrar Evento

	public void cadastrarEvento(Scanner sc, Organizador organizador) {

		Evento novoEvento = new Evento();
		Local local = new Local();

		System.out.print("\nDigite o nome do Evento(a): ");
		String nome = sc.nextLine();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = null;
		Date dataFim = null;

		while (dataInicio == null) {
			try {
				System.out.print("Digite a data de início (dd/MM/yyyy): ");
				dataInicio = sdf.parse(sc.nextLine());
			} catch (ParseException e) {
				System.out.println("Erro: formato de data inválido. Tente novamente.");
			}
		}

		while (dataFim == null || (dataFim != null && dataFim.before(dataInicio))) {
			try {
				System.out.print("Digite a data de fim (dd/MM/yyyy): ");
				dataFim = sdf.parse(sc.nextLine());
				if (dataFim.before(dataInicio)) {
					System.out.println("Erro: a data de fim não pode ser anterior à data de início. Tente novamente.");
					dataFim = null;
				}
			} catch (ParseException e) {
				System.out.println("Erro: formato de data inválido. Tente novamente.");
			}
		}

		System.out.println("\nEscolha o local do evento:");
		for (int i = 0; i < local.getCampi().size(); i++) {
			System.out.println((i + 1) + " - " + local.getCampi().get(i));
		}

		int escolha = -1;
		String localSelecionado = null;

		while (escolha < 1 || escolha > local.getCampi().size()) {
			System.out.print("Digite o número do local desejado: ");
			try {
				escolha = Integer.parseInt(sc.nextLine());
				if (escolha >= 1 && escolha <= local.getCampi().size()) {
					localSelecionado = local.getCampi().get(escolha - 1);
				} else {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		novoEvento.setNome(nome);
		novoEvento.setDataInicio(dataInicio);
		novoEvento.setDataFim(dataFim);
		novoEvento.setLocal(localSelecionado);

		List<Atividade> atividadesDoEvento = new ArrayList<>();

		boolean adicionarAtividade = true;
		while (adicionarAtividade) {
			System.out.print("\nDigite o nome da Atividade: ");
			String nomeAtividade = sc.nextLine();

			System.out.print("Digite o tipo de Atividade: ");
			String tipoAtividade = sc.nextLine();

			Date dataAtividade = null;
			while (dataAtividade == null) {
				try {
					System.out.print("Digite a data da atividade (dd/MM/yyyy): ");
					dataAtividade = sdf.parse(sc.nextLine());
				} catch (ParseException e) {
					System.out.println("Erro: formato de data inválido. Tente novamente.");
				}
			}

			System.out.print("Digite o horário da atividade (HH:mm): ");
			LocalTime horario = LocalTime.parse(sc.nextLine());

			System.out.print("Digite o espaço da atividade: ");
			String espaco = sc.nextLine();

			System.out.print("Digite o número de vagas: ");
			int vagas = Integer.parseInt(sc.nextLine());

			Atividade atividade = new Atividade(nomeAtividade, tipoAtividade, dataAtividade, horario, espaco, vagas,
					null);
			atividadesDoEvento.add(atividade);

			boolean entradaValida = false;
			while (!entradaValida) {
				System.out.print("Deseja adicionar outra atividade? (s/n): ");
				String resposta = sc.nextLine();

				if (resposta.equalsIgnoreCase("s")) {
					adicionarAtividade = true;
					entradaValida = true;
				} else if (resposta.equalsIgnoreCase("n")) {
					adicionarAtividade = false;
					entradaValida = true;
				} else {
					System.out.println("Erro: Entrada inválida. Por favor, digite 's' para sim ou 'n' para não.");
				}
			}

		}

		novoEvento.setAtividade(atividadesDoEvento);
		listaEventos.add(novoEvento);

		System.out.println("\nEvento cadastrado com sucesso!");
		System.out.println("Nome: " + novoEvento.getNome());
		System.out.println("Organizador: " + organizador.getNome());
		System.out.println("Data de início: " + sdf.format(novoEvento.getDataInicio()));
		System.out.println("Data de fim: " + sdf.format(novoEvento.getDataFim()));
		System.out.println("Local: " + novoEvento.getLocal());

	}

	// Método Listar Eventos e Atividades

	public void listarEventos(Organizador organizador) {
		System.out.println("\nLista de Eventos Cadastrados:\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Evento evento : listaEventos) {
			System.out.println("Nome: " + evento.getNome());
			System.out.println("Organizador(a): " + organizador.getNome());
			System.out.println("Data de início: " + sdf.format(evento.getDataInicio()));
			System.out.println("Data de fim: " + sdf.format(evento.getDataFim()));
			System.out.println("Local: " + evento.getLocal());

			System.out.println("\n  Atividades:\n");
			for (Atividade atividade : evento.getAtividades()) {
				System.out.println("  Nome da Atividade: " + atividade.getNomeAtividade());
				System.out.println("  Tipo: " + atividade.getTipoAtividade());
				System.out.println("  Data: " + sdf.format(atividade.getData()));
				System.out.println("  Horário: " + atividade.getHorario());
				System.out.println("  Espaço: " + atividade.getEspaco());
				System.out.println("  Vagas: " + atividade.getVagas());
				System.out.println("  Número de Participantes: " + atividade.getParticipantes().size());
				System.out.println("--------------------------------");
			}

			System.out.println("--------------------------------");
		}
	}

	// Método Listar Eventos e Atividades (Static)

	public void listarEventosStatic() {
		System.out.println("\nLista de Eventos Cadastrados:\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Evento evento : listaEventos) {
			System.out.println("Nome: " + evento.getNome());
			System.out.println("Organizador(a): Cilene Aparecida Mainente");
			System.out.println("Data de início: " + sdf.format(evento.getDataInicio()));
			System.out.println("Data de fim: " + sdf.format(evento.getDataFim()));
			System.out.println("Local: " + evento.getLocal());

			System.out.println("\n  Atividades:\n");
			for (Atividade atividade : evento.getAtividades()) {
				System.out.println("  Nome da Atividade: " + atividade.getNomeAtividade());
				System.out.println("  Tipo: " + atividade.getTipoAtividade());
				System.out.println("  Data: " + sdf.format(atividade.getData()));
				System.out.println("  Horário: " + atividade.getHorario());
				System.out.println("  Espaço: " + atividade.getEspaco());
				System.out.println("  Vagas: " + atividade.getVagas());
				System.out.println("  Número de Participantes: " + atividade.getParticipantes().size());
				System.out.println("--------------------------------");
			}

			System.out.println("--------------------------------");
		}
	}

	// Método Alterar Local do Evento

	public void alterarLocalEvento(Scanner sc) {
		if (listaEventos.size() < 1) {
			System.out.println("Não há eventos cadastrados ainda.");
			return;
		}

		System.out.println("\nLista de Eventos:");
		for (int i = 0; i < listaEventos.size(); i++) {
			System.out
					.println((i + 1) + " - " + listaEventos.get(i).getNome() + " - " + listaEventos.get(i).getLocal());
		}

		int escolhaEvento = -1;
		while (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
			System.out.print("\nDigite o número do evento que deseja alterar o local: ");
			try {
				escolhaEvento = Integer.parseInt(sc.nextLine());
				if (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(escolhaEvento - 1);
		String localAtual = eventoSelecionado.getLocal();

		Local local = new Local();
		System.out.println("\nEscolha o novo local do evento:\n");
		for (int i = 0; i < local.getCampi().size(); i++) {
			System.out.println((i + 1) + " - " + local.getCampi().get(i));
		}

		int escolhaLocal = -1;
		String localSelecionado = null;

		while (escolhaLocal < 1 || escolhaLocal > local.getCampi().size()) {
			System.out.print("\nDigite o número do novo local desejado: ");
			try {
				escolhaLocal = Integer.parseInt(sc.nextLine());
				if (escolhaLocal >= 1 && escolhaLocal <= local.getCampi().size()) {
					localSelecionado = local.getCampi().get(escolhaLocal - 1);

					if (localSelecionado.equals(localAtual)) {
						System.out.println(
								"\nErro: O novo local não pode ser o mesmo que o local atual. Tente novamente.");
						escolhaLocal = -1;

					}
				} else {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		eventoSelecionado.setLocal(localSelecionado);
		System.out.println("\nLocal do evento atualizado com sucesso!");
	}

	// Método Cadastrar Atividade

	public void cadastrarAtividade(Scanner sc) {
		if (listaEventos.size() < 1) {
			System.out.println("Não há eventos cadastrados ainda.");
			return;
		}

		System.out.println("\nLista de Eventos:");
		for (int i = 0; i < listaEventos.size(); i++) {
			System.out.println((i + 1) + " - " + listaEventos.get(i).getNome());
		}

		int escolhaEvento = -1;
		while (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
			System.out.print("\nDigite o número do evento ao qual deseja adicionar a atividade: ");
			try {
				escolhaEvento = Integer.parseInt(sc.nextLine());
				if (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(escolhaEvento - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("\nDigite o nome da Atividade: ");
		String nomeAtividade = sc.nextLine();

		System.out.print("Digite o tipo de Atividade: ");
		String tipoAtividade = sc.nextLine();

		Date dataAtividade = null;
		while (dataAtividade == null) {
			try {
				System.out.print("Digite a data da atividade (dd/MM/yyyy): ");
				dataAtividade = sdf.parse(sc.nextLine());
			} catch (ParseException e) {
				System.out.println("Erro: formato de data inválido. Tente novamente.");
			}
		}

		System.out.print("Digite o horário da atividade (HH:mm): ");
		LocalTime horario = LocalTime.parse(sc.nextLine());

		System.out.print("Digite o espaço da atividade: ");
		String espaco = sc.nextLine();

		System.out.print("Digite o número de vagas: ");
		int vagas = Integer.parseInt(sc.nextLine());

		Atividade atividade = new Atividade(nomeAtividade, tipoAtividade, dataAtividade, horario, espaco, vagas, null);
		eventoSelecionado.getAtividades().add(atividade);

		System.out.println("\nAtividade cadastrada com sucesso no evento \"" + eventoSelecionado.getNome() + "\"!");
	}

	// Método Remover Atividade

	public void removerAtividade(Scanner sc) {
		if (listaEventos.size() < 1) {
			System.out.println("Não há eventos cadastrados ainda.");
			return;
		}

		System.out.println("\nLista de Eventos:");
		for (int i = 0; i < listaEventos.size(); i++) {
			System.out.println((i + 1) + " - " + listaEventos.get(i).getNome());
		}

		int escolhaEvento = -1;
		while (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
			System.out.print("\nDigite o número do evento do qual deseja remover a atividade: ");
			try {
				escolhaEvento = Integer.parseInt(sc.nextLine());
				if (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(escolhaEvento - 1);

		if (eventoSelecionado.getAtividades().isEmpty()) {
			System.out.println("Não há atividades cadastradas para este evento.");
			return;
		}

		System.out.println("\nLista de Atividades do Evento \"" + eventoSelecionado.getNome() + "\":");
		for (int i = 0; i < eventoSelecionado.getAtividades().size(); i++) {
			System.out.println((i + 1) + " - " + eventoSelecionado.getAtividades().get(i).getNomeAtividade());
		}

		int escolhaAtividade = -1;
		while (escolhaAtividade < 1 || escolhaAtividade > eventoSelecionado.getAtividades().size()) {
			System.out.print("Digite o número da atividade que deseja remover: ");
			try {
				escolhaAtividade = Integer.parseInt(sc.nextLine());
				if (escolhaAtividade < 1 || escolhaAtividade > eventoSelecionado.getAtividades().size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Atividade atividadeRemovida = eventoSelecionado.getAtividades().remove(escolhaAtividade - 1);
		System.out.println("Atividade \"" + atividadeRemovida.getNomeAtividade()
				+ "\" removida com sucesso do evento \"" + eventoSelecionado.getNome() + "\"!");
	}

	// Método Atribuir Palestrante

	public void atribuirPalestrante(Scanner sc) {
		if (listaEventos.size() < 1) {
			System.out.println("Não há eventos cadastrados ainda.");
			return;
		}

		System.out.println("\nLista de Eventos:");
		for (int i = 0; i < listaEventos.size(); i++) {
			System.out.println((i + 1) + " - " + listaEventos.get(i).getNome());
		}

		int escolhaEvento = -1;
		while (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
			System.out.print("Digite o número do evento ao qual deseja atribuir um palestrante: ");
			try {
				escolhaEvento = Integer.parseInt(sc.nextLine());
				if (escolhaEvento < 1 || escolhaEvento > listaEventos.size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(escolhaEvento - 1);

		if (eventoSelecionado.getAtividades().isEmpty()) {
			System.out.println("Não há atividades cadastradas para este evento.");
			return;
		}

		System.out.println("\nLista de Atividades do Evento \"" + eventoSelecionado.getNome() + "\":");
		for (int i = 0; i < eventoSelecionado.getAtividades().size(); i++) {
			System.out.println((i + 1) + " - " + eventoSelecionado.getAtividades().get(i).getNomeAtividade());
		}

		int escolhaAtividade = -1;
		while (escolhaAtividade < 1 || escolhaAtividade > eventoSelecionado.getAtividades().size()) {
			System.out.print("Digite o número da atividade à qual deseja atribuir um palestrante: ");
			try {
				escolhaAtividade = Integer.parseInt(sc.nextLine());
				if (escolhaAtividade < 1 || escolhaAtividade > eventoSelecionado.getAtividades().size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Atividade atividadeSelecionada = eventoSelecionado.getAtividades().get(escolhaAtividade - 1);

		if (Palestrante.getListaPalestrantes().isEmpty()) {
			System.out.println("Não há palestrantes cadastrados.");
			return;
		}

		System.out.println("\nLista de Palestrantes Disponíveis:");
		for (int i = 0; i < Palestrante.getListaPalestrantes().size(); i++) {
			System.out.println((i + 1) + " - " + Palestrante.getListaPalestrantes().get(i).getNome()
					+ " (Especialidade: " + Palestrante.getListaPalestrantes().get(i).getEspecialidade() + ")");
		}

		int escolhaPalestrante = -1;
		while (escolhaPalestrante < 1 || escolhaPalestrante > Palestrante.getListaPalestrantes().size()) {
			System.out.print("Digite o número do palestrante que deseja atribuir à atividade: ");
			try {
				escolhaPalestrante = Integer.parseInt(sc.nextLine());
				if (escolhaPalestrante < 1 || escolhaPalestrante > Palestrante.getListaPalestrantes().size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Palestrante palestranteSelecionado = Palestrante.getListaPalestrantes().get(escolhaPalestrante - 1);
		atividadeSelecionada.setPalestrante(palestranteSelecionado);

		System.out.println("Palestrante \"" + palestranteSelecionado.getNome()
				+ "\" atribuído com sucesso à atividade \"" + atividadeSelecionada.getNomeAtividade()
				+ "\" no evento \"" + eventoSelecionado.getNome() + "\"!");
	}

	// Método Emitir Lista de Presença

	public void emitirListaPresenca(Scanner sc) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (listaEventos.isEmpty()) {
			System.out.println("Nenhum evento cadastrado.");
			return;
		}

		System.out.println("\nEscolha o evento para emitir a lista de presença:\n");
		for (int i = 0; i < listaEventos.size(); i++) {
			Evento evento = listaEventos.get(i);
			System.out.println((i + 1) + " - " + evento.getNome() + " (de " + sdf.format(evento.getDataInicio()) + " a "
					+ sdf.format(evento.getDataFim()) + ")");
		}

		int eventoEscolhido = -1;
		while (eventoEscolhido < 1 || eventoEscolhido > listaEventos.size()) {
			System.out.print("\nDigite o número do evento: ");
			try {
				eventoEscolhido = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(eventoEscolhido - 1);

		if (eventoSelecionado.getAtividades().isEmpty()) {
			System.out.println("Esse evento não possui atividades cadastradas.");
			return;
		}

		System.out.println("\nEscolha a atividade para emitir a lista de presença:\n");
		for (int i = 0; i < eventoSelecionado.getAtividades().size(); i++) {
			Atividade atividade = eventoSelecionado.getAtividades().get(i);
			System.out.println((i + 1) + " - " + atividade.getNomeAtividade() + " em " + sdf.format(atividade.getData())
					+ " às " + atividade.getHorario());
		}

		int atividadeEscolhida = -1;
		while (atividadeEscolhida < 1 || atividadeEscolhida > eventoSelecionado.getAtividades().size()) {
			System.out.print("\nDigite o número da atividade: ");
			try {
				atividadeEscolhida = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Atividade atividadeSelecionada = eventoSelecionado.getAtividades().get(atividadeEscolhida - 1);

		if (atividadeSelecionada.getParticipantes().isEmpty()) {
			System.out.println("Essa atividade não possui participantes inscritos.");
		} else {
			System.out.println("\nLista de Presença para a atividade: " + atividadeSelecionada.getNomeAtividade());
			System.out.println("Data: " + sdf.format(atividadeSelecionada.getData()));
			System.out.println("Horário: " + atividadeSelecionada.getHorario());
			System.out.println("Espaço: " + atividadeSelecionada.getEspaco());
			System.out.println("\nParticipantes inscritos:\n");

			for (Participante participante : atividadeSelecionada.getParticipantes()) {
				System.out.println(" - " + participante.getNome() + " (E-mail: " + participante.getEmail() + ")");
			}
		}
	}

	// Método para o participante realizar a inscrição em uma atividade

	public void inscreverParticipanteAtividade(Scanner sc, Participante participante) {
		if (listaEventos.isEmpty()) {
			System.out.println("Não há eventos disponíveis para inscrição.");
			return;
		}

		System.out.println("\nEventos disponíveis para inscrição:\n");
		for (int i = 0; i < listaEventos.size(); i++) {
			Evento evento = listaEventos.get(i);
			System.out.println((i + 1) + ". " + evento.getNome() + " - Local: " + evento.getLocal());
		}

		int eventoEscolhido = -1;
		while (eventoEscolhido < 1 || eventoEscolhido > listaEventos.size()) {
			System.out.print("\nDigite o número do evento para visualizar as atividades: ");
			try {
				eventoEscolhido = Integer.parseInt(sc.nextLine());
				if (eventoEscolhido < 1 || eventoEscolhido > listaEventos.size()) {
					System.out.println("\nOpção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("\nEntrada inválida. Digite um número.");
			}
		}

		Evento eventoSelecionado = listaEventos.get(eventoEscolhido - 1);
		if (eventoSelecionado.getAtividades().isEmpty()) {
			System.out.println("\nNão há atividades disponíveis para inscrição neste evento.");
			return;
		}

		System.out.println("\nAtividades disponíveis no evento " + eventoSelecionado.getNome() + ":\n");
		List<Atividade> atividadesDoEvento = eventoSelecionado.getAtividades();
		for (int i = 0; i < atividadesDoEvento.size(); i++) {
			Atividade atividade = atividadesDoEvento.get(i);
			System.out.println((i + 1) + ". " + atividade.getNomeAtividade() + " - Vagas disponíveis: "
					+ (atividade.getVagas() - atividade.getParticipantes().size()));
		}

		int atividadeEscolhida = -1;
		while (atividadeEscolhida < 1 || atividadeEscolhida > atividadesDoEvento.size()) {
			System.out.print("\nDigite o número da atividade para se inscrever: ");
			try {
				atividadeEscolhida = Integer.parseInt(sc.nextLine());
				if (atividadeEscolhida < 1 || atividadeEscolhida > atividadesDoEvento.size()) {
					System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
			}
		}

		Atividade atividadeSelecionada = atividadesDoEvento.get(atividadeEscolhida - 1);
		atividadeSelecionada.realizarInscricaoAtividade(participante);
	}

	// Eventos Pré-Criados Para Testes Unitários

	static {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			// Evento 1
			Evento evento1 = new Evento();
			evento1.setNome("Congresso de Tecnologia");
			evento1.setDataInicio(sdf.parse("01/12/2024"));
			evento1.setDataFim(sdf.parse("03/12/2024"));
			evento1.setLocal("Campus Central");

			Atividade atividade1Evento1 = new Atividade("Palestra sobre IA", "Palestra", sdf.parse("01/12/2024"),
					LocalTime.of(10, 0), "Auditório A", 100, null);
			Atividade atividade2Evento1 = new Atividade("Workshop de Big Data", "Workshop", sdf.parse("02/12/2024"),
					LocalTime.of(14, 0), "Sala 101", 50, null);

			evento1.getAtividades().add(atividade1Evento1);
			evento1.getAtividades().add(atividade2Evento1);
			listaEventos.add(evento1);

			atividade1Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(0));
			atividade1Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(1));
			atividade1Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(2));
			atividade1Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(3));
			atividade1Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(4));
			atividade2Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(5));
			atividade2Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(6));
			atividade2Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(7));
			atividade2Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(8));
			atividade2Evento1.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(9));

			// Evento 2
			Evento evento2 = new Evento();
			evento2.setNome("Simpósio de Engenharia");
			evento2.setDataInicio(sdf.parse("05/12/2024"));
			evento2.setDataFim(sdf.parse("06/12/2024"));
			evento2.setLocal("Campus Sul");

			Atividade atividade1Evento2 = new Atividade("Palestra sobre Sustentabilidade", "Palestra",
					sdf.parse("05/12/2024"), LocalTime.of(9, 0), "Auditório B", 80, null);
			Atividade atividade2Evento2 = new Atividade("Oficina de Protótipos", "Oficina", sdf.parse("06/12/2024"),
					LocalTime.of(13, 0), "Sala 202", 30, null);

			evento2.getAtividades().add(atividade1Evento2);
			evento2.getAtividades().add(atividade2Evento2);
			listaEventos.add(evento2);

			atividade1Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(0));
			atividade1Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(1));
			atividade1Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(2));
			atividade1Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(3));
			atividade1Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(4));
			atividade2Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(5));
			atividade2Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(6));
			atividade2Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(7));
			atividade2Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(8));
			atividade2Evento2.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(9));

			// Evento 3
			Evento evento3 = new Evento();
			evento3.setNome("Workshop de Empreendedorismo");
			evento3.setDataInicio(sdf.parse("10/12/2024"));
			evento3.setDataFim(sdf.parse("11/12/2024"));
			evento3.setLocal("Campus Norte");

			Atividade atividade1Evento3 = new Atividade("Oficina de Planejamento Estratégico", "Oficina",
					sdf.parse("10/12/2024"), LocalTime.of(11, 0), "Sala 303", 40, null);
			Atividade atividade2Evento3 = new Atividade("Mesa-redonda sobre Startups", "Mesa-redonda",
					sdf.parse("11/12/2024"), LocalTime.of(15, 0), "Auditório C", 60, null);

			evento3.getAtividades().add(atividade1Evento3);
			evento3.getAtividades().add(atividade2Evento3);
			listaEventos.add(evento3);

			atividade1Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(0));
			atividade1Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(1));
			atividade1Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(2));
			atividade1Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(3));
			atividade1Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(4));
			atividade2Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(5));
			atividade2Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(6));
			atividade2Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(7));
			atividade2Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(8));
			atividade2Evento3.realizarInscricaoAtividadeStatic(Participante.getListaParticipante().get(9));

		} catch (ParseException e) {
			System.out.println("Erro ao parsear datas no bloco estático: " + e.getMessage());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividade(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public static ArrayList<Evento> getListaEventos() {
		return listaEventos;
	}

	public static void setListaEventos(ArrayList<Evento> listaEventos) {
		Evento.listaEventos = listaEventos;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

}
