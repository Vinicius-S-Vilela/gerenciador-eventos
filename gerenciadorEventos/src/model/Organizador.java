package model;

import java.util.Scanner;

public class Organizador extends Pessoa {

	public Organizador(String nome, String email, String telefone, String endereco) {
		super(nome, email, telefone, endereco);

	}
	
	// Método de Cadastro

	public void cadastrarOrganizador(Scanner sc) {

		System.out.print("\nDigite o nome do organizador(a): ");
		String nome = sc.nextLine();

		System.out.print("Digite o e-mail do organizador(a): ");
		String email = sc.nextLine();

		System.out.print("Digite o telefone do organizador(a): ");
		String telefone = sc.nextLine();

		System.out.print("Digite o endereco do organizador(a): ");
		String endereco = sc.nextLine();

		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setEndereco(endereco);

		System.out.println("\nOrganizador(a) cadastrado(a) com sucesso!");
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

	public void gerenciarEvento() {

	}

	public void gerenciarAtividade() {

	}

	public void exibirDados() {
		System.out.println("Nome: " + getNome());
		System.out.println("E-mail: " + getEmail());
		System.out.println("Telefone: " + getTelefone());
		System.out.println("Endereço: " + getEndereco());

	}

}
