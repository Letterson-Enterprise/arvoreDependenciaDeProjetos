package br.com.lettersonEnterprise.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.lettersonEnterprise.enumeradores.Posicao;
import br.com.lettersonEnterprise.modelos.ArvoreDeDependencias;
import br.com.lettersonEnterprise.modelos.TarefaNo;

/*Classe responsavel por funcionalidades de gerenciamento de tarefas
 * Exemplo: Inserção de tarefas, Vizualização de percurso
 * @author Jefferson Matheus*/
public class ArvoreDeDependenciaServico {

	private ArvoreDeDependencias arvoreDeDependencias;

	public void inserirNo(int idNoPai, TarefaNo tarefa, Posicao posicao) {
		TarefaNo pai = buscarNo(idNoPai);

		if (pai == null) {
			System.out.println("Erro: Tarefa pai com ID " + idNoPai + " não encontrada na árvore.");
			return; // Sai do método sem fazer nada
		}

		// 2. Agora usamos o seu Enum de forma elegante para decidir o lado
		if (posicao == Posicao.ESQUERDA) {
			// Verifica se a vaga da esquerda já está ocupada
			if (pai.getNoTarefaEsqueda() != null) {
				System.out.println("Erro: A tarefa " + pai.getNome() + " já possui uma dependência à esquerda.");
			} else {
				pai.setNoTarefaEsqueda(tarefa); // Conecta o nó!
				System.out.println("Sucesso: Tarefa inserida à esquerda de " + pai.getNome());
			}
		} else if (posicao == Posicao.DIREITA) {
			// Verifica se a vaga da direita já está ocupada
			if (pai.getNoTarefaDireita() != null) {
				System.out.println("Erro: A tarefa " + pai.getNome() + " já possui uma dependência à direita.");
			} else {
				pai.setNoTarefaDireita(tarefa); // Conecta o nó!
				System.out.println("Sucesso: Tarefa inserida à direita de " + pai.getNome());
			}
		}
	}



	public TarefaNo buscarNo(int idTarefaNo) {
		if (this.arvoreDeDependencias == null || this.arvoreDeDependencias.getRaiz() == null) {
			System.out.println("A árvore ainda não foi criada!");
			return null;
		}
		// Dispara a busca começando pela raiz da árvore
		return buscarRecursivo(this.arvoreDeDependencias.getRaiz(), idTarefaNo);
	}

	private TarefaNo buscarRecursivo(TarefaNo noAtual, int idDesejado) {
		// Se chegou num beco sem saída (nulo), retorna nulo
		if (noAtual == null) {
			return null;
		}

		// Se o nó atual for o que estamos procurando, devolve ele!
		if (noAtual.getId() == idDesejado) {
			return noAtual;
		}

		// Se não é o atual, manda procurar em toda a sub-árvore da ESQUERDA
		TarefaNo encontradoNaEsquerda = buscarRecursivo(noAtual.getNoTarefaEsqueda(), idDesejado);
		if (encontradoNaEsquerda != null) {
			return encontradoNaEsquerda; // Achou na esquerda, repassa o resultado para cima
		}

		// Se não achou nem no atual e nem na esquerda, a última esperança é a DIREITA
		return buscarRecursivo(noAtual.getNoTarefaDireita(), idDesejado);
	}

	public boolean CriarArvoreDeDependencia(int idRaiz, String descricao) {

		if (idRaiz <= 0 || descricao == null || descricao.trim().isEmpty()) {
			return false;
		}

		TarefaNo raiz = new TarefaNo(idRaiz, descricao);

		this.arvoreDeDependencias = new ArvoreDeDependencias(raiz);
		return true;
	}
	
	
	public List<TarefaNo> obterOrdemExecucao() {
	    List<TarefaNo> ordem = new ArrayList<>();
	    if (this.arvoreDeDependencias != null && this.arvoreDeDependencias.getRaiz() != null) {
	        percursoPosOrdem(this.arvoreDeDependencias.getRaiz(), ordem);
	    }
	    return ordem;
	}

	private void percursoPosOrdem(TarefaNo noAtual, List<TarefaNo> listaOrdem) {
	    if (noAtual == null) {
	        return;
	    }
	    // 1. Visita a subárvore da esquerda (dependência 1)
	    percursoPosOrdem(noAtual.getNoTarefaEsqueda(), listaOrdem);
	    
	    // 2. Visita a subárvore da direita (dependência 2)
	    percursoPosOrdem(noAtual.getNoTarefaDireita(), listaOrdem);
	    
	    // 3. Visita a si mesmo (só executa a tarefa após os filhos estarem prontos)
	    listaOrdem.add(noAtual);
	}
}
