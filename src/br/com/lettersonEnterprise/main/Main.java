package br.com.lettersonEnterprise.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.lettersonEnterprise.views.JanelaPrincipal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new JanelaPrincipal().setVisible(true);
        });
    }

	}


