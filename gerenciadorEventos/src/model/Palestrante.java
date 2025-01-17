package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Palestrante extends Pessoa {

	private String especialidade;
	private static ArrayList<Palestrante> listaPalestrantes = new ArrayList<>();

	public Palestrante(String nome, String email, String telefone, String endereco, String especialidade) {
		super(nome, email, telefone, endereco);
		this.especialidade = especialidade;
	}

	// Método de Cadastro

	public void cadastrarPalestrante(Scanner sc) {

		System.out.print("\nDigite o nome do palestrante: ");
		String nome = sc.nextLine();

		System.out.print("Digite o e-mail do palestrante: ");
		String email = sc.nextLine();

		System.out.print("Digite o telefone do palestrante: ");
		String telefone = sc.nextLine();

		System.out.print("Digite o endereco do palestrante: ");
		String endereco = sc.nextLine();

		System.out.print("Digite a especialidade do palestrante: ");
		String especialidade = sc.nextLine();

		Palestrante novoPalestrante = new Palestrante(nome, email, telefone, endereco, especialidade);
		listaPalestrantes.add(novoPalestrante);

		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setEspecialidade(especialidade);

		System.out.println("\nPalestrante cadastrado com sucesso!");
		System.out.println("Nome: " + this.getNome());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Telefone: " + this.getTelefone());
		System.out.println("Endereco: " + this.getEndereco());
		System.out.println("Especialidade: " + this.getEspecialidade());

	}
	
	// Método Alterar Dados

	@Override
	public void alterarDados(String nome, String email, String telefone, String endereco) {
		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setEndereco(endereco);

	}

	public void atribuirPalestra() {

	}

	public static ArrayList<Palestrante> getListaPalestrantes() {
		return listaPalestrantes;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void exibirDados() {
		System.out.println("Nome: " + getNome());
		System.out.println("E-mail: " + getEmail());
		System.out.println("Telefone: " + getTelefone());
		System.out.println("Endereço: " + getEndereco());
		System.out.println("Especialidade " + getEspecialidade());

	}

	// Palestrantes Pré-Criados Para Testes Únitarios

	static {
		listaPalestrantes.add(new Palestrante("Marcio Katsumi Oikawa", "marcio@gmail.com", "11963157560", "Rua A, 123",
				"Programação Orientada a Objetos"));
		listaPalestrantes.add(new Palestrante("Carlos Eduardo Bognar", "carlos@gmail.com", "11965487532", "Rua B, 456",
				"Sistemas Operacionais"));
		listaPalestrantes.add(new Palestrante("Robson do Nascimento", "robson@gmail.com", "11956321575", "Rua C, 789",
				"Redes de Computadores"));
	}

}
