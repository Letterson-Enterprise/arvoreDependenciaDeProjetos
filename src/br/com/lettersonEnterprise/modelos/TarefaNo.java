package br.com.lettersonEnterprise.modelos;

import java.util.Objects;

public class TarefaNo {
	private int id;
	private String nome;
	private TarefaNo noTarefaEsqueda = null;
	private TarefaNo noTarefaDireita = null;
	
	public TarefaNo(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TarefaNo getNoTarefaEsqueda() {
		return noTarefaEsqueda;
	}

	public void setNoTarefaEsqueda(TarefaNo noTarefaEsqueda) {
		this.noTarefaEsqueda = noTarefaEsqueda;
	}

	public TarefaNo getNoTarefaDireita() {
		return noTarefaDireita;
	}

	public void setNoTarefaDireita(TarefaNo noTarefaDireita) {
		this.noTarefaDireita = noTarefaDireita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaNo other = (TarefaNo) obj;
		return id == other.id;
	}
}
