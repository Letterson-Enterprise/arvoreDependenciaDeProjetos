package br.com.lettersonEnterprise.servicos;

import java.util.List;
import java.util.ArrayList;
import br.com.lettersonEnterprise.modelos.TarefaNo;

public class TarefaServico {
	private List<TarefaNo> listaTarefas = new ArrayList<>();

	public TarefaServico() {
	}

	public boolean criarNo(int idTarefa, String descricao) {
		TarefaNo novaTarefa = new TarefaNo(idTarefa, descricao);

		boolean existe = verificarSeJaExiste(novaTarefa);

		if (!existe) {
			listaTarefas.add(novaTarefa);
			return true;
		}

		return false;
	}

	public boolean verificarSeJaExiste(TarefaNo tarefaCriada) {
		for (TarefaNo tarefa : listaTarefas) {
			if (tarefa.equals(tarefaCriada)) {
				return true;
			}
		}

		return false;
	}

	public TarefaNo buscarNo(int id) {
		for (TarefaNo tarefa : listaTarefas) {
			if (tarefa.getId() == id) {
				return tarefa;
			}
		}

		return null;
	}
}
