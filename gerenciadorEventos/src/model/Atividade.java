package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Atividade {
	private String nomeAtividade;
	private String tipoAtividade;
	private Date data;
	private LocalTime horario;
	private String espaco;
	private int vagas;
	private Palestrante palestrante;
	private List<Participante> participantes;

	public Atividade(String nomeAtividade, String tipoAtividade, Date data, LocalTime horario, String espaco, int vagas,
			Palestrante palestrante) {
		this.nomeAtividade = nomeAtividade;
		this.tipoAtividade = tipoAtividade;
		this.data = data;
		this.horario = horario;
		this.espaco = espaco;
		this.vagas = vagas;
		this.palestrante = palestrante;
		this.participantes = new ArrayList<>();
	}
	
	// Método Realizar Inscrição em Atividade

	public void realizarInscricaoAtividade(Participante participante) {
		if (participantes.contains(participante)) {
			System.out.println("Participante já está inscrito nesta atividade.");
		} else if (participantes.size() < vagas) {
			participantes.add(participante);
			System.out.println("Inscrição realizada com sucesso para: " + participante.getNome());
		} else {
			System.out.println("Não há vagas disponíveis para esta atividade.");
		}
	}

	public void realizarInscricaoAtividadeStatic(Participante participante) {
		if (participantes.contains(participante)) {
			System.out.println("Participante já está inscrito nesta atividade.");
		} else if (participantes.size() < vagas) {
			participantes.add(participante);
		}
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public String getEspaco() {
		return espaco;
	}

	public void setEspaco(String espaco) {
		this.espaco = espaco;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

}