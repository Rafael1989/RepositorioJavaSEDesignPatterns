package br.com.caelum.argentum.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.glass.events.KeyEvent;

import br.com.caelum.argentum.Negocio;

public class ArgentumUI {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Locale.setDefault(new Locale("pt", "BR"));
		new ArgentumUI().montaTela();
	}

	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;

	private void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaTitulo();
		preparaTabela();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanela();
	}

	private void preparaTitulo() {
		JLabel titulo = new JLabel("Lista de Negócios",SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		painelPrincipal.add(titulo);
	}

	private void preparaTabela() {
		tabela = new JTable();
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(tabela);
		
		painelPrincipal.add(scroll);
	}

	private void preparaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.setMnemonic(KeyEvent.VK_C);
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Negocio> lista = new EscolhedorDeXML().escolhe();
				NegociosTableModel ntm = new NegociosTableModel(lista);
				tabela.setModel(ntm);
			}
		});
		painelPrincipal.add(botaoCarregar);
	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setMnemonic(KeyEvent.VK_S);
		botaoSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		painelPrincipal.add(botaoSair);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}
}
