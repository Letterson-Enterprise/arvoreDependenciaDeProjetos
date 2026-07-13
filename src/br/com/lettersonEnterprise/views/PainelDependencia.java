package br.com.lettersonEnterprise.views;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.lettersonEnterprise.enumeradores.Posicao;
import br.com.lettersonEnterprise.modelos.TarefaNo;
import br.com.lettersonEnterprise.servicos.ArvoreDeDependenciaServico;
import br.com.lettersonEnterprise.servicos.TarefaServico;

public class PainelDependencia extends JPanel {
	private JTextField txtDepIdPai, txtDepIdFilho;
	private JComboBox<Posicao> cbPosicao;

	private final TarefaServico tarefaServico;
	private final ArvoreDeDependenciaServico arvoreServico;
	private final JanelaPrincipal janelaPrincipal; // O coordenador

	public PainelDependencia(TarefaServico tServico, ArvoreDeDependenciaServico aServico, JanelaPrincipal janela) {
		this.tarefaServico = tServico;
		this.arvoreServico = aServico;
		this.janelaPrincipal = java.util.Objects.requireNonNull(janela);

		setLayout(new GridLayout(4, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder("2. Inserir Nó na Árvore"));

		add(new JLabel("ID do Nó Pai:"));
		txtDepIdPai = new JTextField();
		add(txtDepIdPai);

		add(new JLabel("ID da Tarefa Filho:"));
		txtDepIdFilho = new JTextField();
		add(txtDepIdFilho);

		add(new JLabel("Posição/Lado:"));
		cbPosicao = new JComboBox<>(Posicao.values());
		add(cbPosicao);

		JButton btnVincular = new JButton("Conectar Dependência");
		btnVincular.addActionListener(e -> executarVinculo());
		add(new JLabel());
		add(btnVincular);
	}

	private void executarVinculo() {
		try {
			int idPai = Integer.parseInt(txtDepIdPai.getText().trim());
			int idFilho = Integer.parseInt(txtDepIdFilho.getText().trim());
			Posicao posicao = (Posicao) cbPosicao.getSelectedItem();

			TarefaNo original = tarefaServico.buscarNo(idFilho);
			if (original == null) {
				JOptionPane.showMessageDialog(this, "A tarefa " + idFilho + " precisa ser criada primeiro.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			TarefaNo novoNo = new TarefaNo(original.getId(), original.getNome());
			arvoreServico.inserirNo(idPai, novoNo, posicao);

			// AQUI ESTÁ A COMUNICAÇÃO: Avisa a janela principal que a árvore mudou!
			janelaPrincipal.notificarMudancaNaArvore();

			txtDepIdPai.setText("");
			txtDepIdFilho.setText("");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "IDs devem ser números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
