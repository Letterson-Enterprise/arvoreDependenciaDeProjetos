package br.com.lettersonEnterprise.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.lettersonEnterprise.modelos.TarefaNo;
import br.com.lettersonEnterprise.servicos.ArvoreDeDependenciaServico;

public class PainelVisualizacao extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTree treeVisualizacao;
	private JTextArea txtAreaOrdem;

	public PainelVisualizacao() {
		setLayout(new GridLayout(1, 2, 10, 10));

		// Lado Esquerdo: JTree
		JPanel panelTree = new JPanel(new BorderLayout());
		panelTree.setBorder(BorderFactory.createTitledBorder("3. Visualização de Dependências"));
		treeVisualizacao = new JTree();
		panelTree.add(new JScrollPane(treeVisualizacao), BorderLayout.CENTER);

		// Lado Direito: JTextArea para Ordem
		JPanel panelOrdem = new JPanel(new BorderLayout());
		panelOrdem.setBorder(BorderFactory.createTitledBorder("4. Ordem de Execução (Pós-Ordem)"));
		txtAreaOrdem = new JTextArea();
		txtAreaOrdem.setEditable(false);
		txtAreaOrdem.setFont(new Font("Monospaced", Font.PLAIN, 12));
		panelOrdem.add(new JScrollPane(txtAreaOrdem), BorderLayout.CENTER);

		add(panelTree);
		add(panelOrdem);
	}

	/**
	 * Atualiza os componentes gráficos internos com base no estado atual do serviço
	 */
	public void renderizarDados(ArvoreDeDependenciaServico arvoreServico) {
		// 1. Atualiza a Árvore Gráfica
		TarefaNo raizCustom = arvoreServico.buscarNo(1);
		if (raizCustom != null) {
			DefaultMutableTreeNode raizSwing = converterParaNosSwing(raizCustom);
			treeVisualizacao.setModel(new DefaultTreeModel(raizSwing));

			for (int i = 0; i < treeVisualizacao.getRowCount(); i++) {
				treeVisualizacao.expandRow(i);
			}
		}

		// 2. Atualiza o Texto de Fila de Execução
		List<TarefaNo> ordem = arvoreServico.obterOrdemExecucao();
		StringBuilder sb = new StringBuilder("=== FILA DE EXECUÇÃO ===\n\n");
		for (int i = 0; i < ordem.size(); i++) {
			sb.append(String.format("[%02dº] - ID: %d | %s\n", (i + 1), ordem.get(i).getId(), ordem.get(i).getNome()));
		}
		txtAreaOrdem.setText(sb.toString());
	}

	private DefaultMutableTreeNode converterParaNosSwing(TarefaNo noAtual) {
		if (noAtual == null)
			return null;
		DefaultMutableTreeNode noSwing = new DefaultMutableTreeNode(noAtual.getId() + " : " + noAtual.getNome());
		if (noAtual.getNoTarefaEsqueda() != null) {
			noSwing.add(converterParaNosSwing(noAtual.getNoTarefaEsqueda()));
		}
		if (noAtual.getNoTarefaDireita() != null) {
			noSwing.add(converterParaNosSwing(noAtual.getNoTarefaDireita()));
		}
		return noSwing;
	}
}
