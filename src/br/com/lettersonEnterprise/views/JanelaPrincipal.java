package br.com.lettersonEnterprise.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.lettersonEnterprise.servicos.ArvoreDeDependenciaServico;
import br.com.lettersonEnterprise.servicos.TarefaServico;

public class JanelaPrincipal extends JFrame {
	private final TarefaServico tarefaServico;
	private final ArvoreDeDependenciaServico arvoreServico;

	// Referência ao painel de exibição para poder atualizá-lo
	private PainelVisualizacao painelVisualizacao;

	public JanelaPrincipal() {
		// Inicializa a lógica básica
		this.tarefaServico = new TarefaServico();
		this.arvoreServico = new ArvoreDeDependenciaServico();

		// Configuração inicial padrão solicitada
		tarefaServico.criarNo(1, "Módulo Principal (Raiz)");
		arvoreServico.CriarArvoreDeDependencia(1, "Módulo Principal (Raiz)");

		// Configurações do Frame
		setTitle("Gerenciador de Dependências Decoupled");
		setSize(950, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		// Construção dos componentes visuais separados
		painelVisualizacao = new PainelVisualizacao();

		// Criamos o painel da esquerda agrupando os dois formulários
		JPanel painelEsquerdoForms = new JPanel();
		painelEsquerdoForms.setLayout(new BoxLayout(painelEsquerdoForms, BoxLayout.Y_AXIS));
		painelEsquerdoForms.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		painelEsquerdoForms.setPreferredSize(new Dimension(320, 550));

		// Passamos 'this' (a própria JanelaPrincipal) para o PainelDependencia
		// conseguir se comunicar
		painelEsquerdoForms.add(new PainelCadastro(tarefaServico));
		painelEsquerdoForms.add(Box.createRigidArea(new Dimension(0, 15)));
		painelEsquerdoForms.add(new PainelDependencia(tarefaServico, arvoreServico, this));

		// Adiciona tudo na tela
		add(painelEsquerdoForms, BorderLayout.WEST);
		add(painelVisualizacao, BorderLayout.CENTER);

		// Renderização do estado inicial
		notificarMudancaNaArvore();
	}

	/**
	 * Este é o método de ligação central (Mediator). Qualquer painel externo pode
	 * chamar esse método para forçar a atualização dos dados na tela.
	 */
	public void notificarMudancaNaArvore() {
		painelVisualizacao.renderizarDados(arvoreServico);
	}
}
