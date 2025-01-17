package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Certificado;
import model.Evento;
import model.Organizador;
import model.Palestrante;
import model.Participante;
import service.MenuRepository;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Participante participante = new Participante("", "", "", "", "");
		Palestrante palestrante = new Palestrante("", "", "", "", "");
		Organizador organizador = new Organizador("", "", "", "");
		Evento evento = new Evento();
		Certificado certificado = new Certificado();

		MenuRepository menuParticipante = new MenuRepository(participante, sc, evento);
		MenuRepository menuPalestrante = new MenuRepository(palestrante, sc, evento);
		MenuRepository menuOrganizador = new MenuRepository(organizador, sc, evento, certificado);

		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

		System.out.println("------------ " + data + " - " + hora + " ------------");
		System.out.println("Bem-Vindo ao Sistema de Gerenciamento de Eventos");
		System.out.println("\nSelecione o tipo de conta para cadastro");
		System.out.println("1. Participante");
		System.out.println("2. Palestrante");
		System.out.println("3. Organizador");
		System.out.println("4. Sair");
		System.out.print("\nEscolha uma opção: ");

		int tipoConta = sc.nextInt();
		sc.nextLine();

		switch (tipoConta) {
		case 1:
			participante.cadastrarParticipante(sc);
			menuParticipante.menuParticipante();
			break;
		case 2:
			palestrante.cadastrarPalestrante(sc);
			menuPalestrante.menuPalestrante();
			break;
		case 3:
			organizador.cadastrarOrganizador(sc);
			menuOrganizador.menuOrganizador();
			break;
		case 4:
			System.out.println("Encerrando o Programa...");
			break;
		default:
			System.out.println("Escolha inválida.");
		}

		sc.close();
	}
}
