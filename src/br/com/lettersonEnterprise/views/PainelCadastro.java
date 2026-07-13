package br.com.lettersonEnterprise.views;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.lettersonEnterprise.servicos.TarefaServico;

public class PainelCadastro extends JPanel {
	private JTextField txtCadId, txtCadNome;
	private final TarefaServico tarefaServico;

	public PainelCadastro(TarefaServico tarefaServico) {
		this.tarefaServico = tarefaServico;

		setLayout(new GridLayout(3, 2, 5, 5));
		setBorder(BorderFactory.createTitledBorder("1. Cadastrar Nova Tarefa"));

		add(new JLabel("ID da Tarefa:"));
		txtCadId = new JTextField();
		add(txtCadId);

		add(new JLabel("Nome/Descrição:"));
		txtCadNome = new JTextField();
		add(txtCadNome);

		JButton btnCadastrar = new JButton("Cadastrar na Memória");
		btnCadastrar.addActionListener(e -> executarCadastro());
		add(new JLabel());
		add(btnCadastrar);
	}

	private void executarCadastro() {
		try {
			int id = Integer.parseInt(txtCadId.getText().trim());
			String nome = txtCadNome.getText().trim();

			if (nome.isEmpty()) {
				JOptionPane.showMessageDialog(this, "O nome não pode ser vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (tarefaServico.criarNo(id, nome)) {
				JOptionPane.showMessageDialog(this, "Tarefa '" + nome + "' registrada com sucesso!");
				txtCadId.setText("");
				txtCadNome.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Erro: ID " + id + " já existe.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
