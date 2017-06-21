package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.management.RuntimeErrorException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;

import com.sun.glass.events.KeyEvent;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.IndicadorMaximo;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.reader.CandleFactory;

public class ArgentumUI {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		Locale.setDefault(new Locale("pt", "BR"));
		new ArgentumUI().montaTela();
	}

	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JPanel painelBotoes;
	private JTabbedPane abas;
	private JFormattedTextField campoData;
	private MenuIndicadores menu;

	private void montaTela() {
		preparaJanela();
		preparaMenu();
		preparaPainelPrincipal();
		preparaAbas();
		preparaTitulo();
		preparaTabela();
		preparaPainelBotoes();
		preparaCampoData();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanela();
	}

	private void preparaMenu() {
		menu = new MenuIndicadores();
		janela.setJMenuBar(menu.getMenuBar());
	}

	private void preparaCampoData() {
		try{
			JLabel labelData = new JLabel("Apenas a partir de:");
			
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			campoData = new JFormattedTextField(mascara);
			painelBotoes.add(labelData);
			painelBotoes.add(campoData);
		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}

	private void preparaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tabela", null);
		abas.addTab("Gráfico", null);
		painelPrincipal.add(abas);
	}

	private void preparaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void preparaTitulo() {
		JLabel titulo = new JLabel("Lista de Negócios",SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		painelPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void preparaTabela() {
		tabela = new JTable();
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(tabela);
		
		//painelPrincipal.add(scroll, BorderLayout.CENTER);
		abas.setComponentAt(0, scroll);
	}

	private void preparaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);
	}

	private void preparaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.setMnemonic(KeyEvent.VK_C);
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carregaDados();
			}
		});
		painelBotoes.add(botaoCarregar);
	}
	
	private void carregaDados() {
		List<Negocio> negocios = new EscolhedorDeXML().escolhe();
		//NegociosTableModel ntm = new NegociosTableModel(lista);
		//tabela.setModel(ntm);
		boolean dataValida = true;
		if(campoData.getValue() != null && !campoData.getValue().equals("")){
			try{
				new FiltradorPorData(campoData.getText()).filtra(negocios);
			}catch(RuntimeException e){
				JOptionPane.showMessageDialog(painelPrincipal, "Favor digitar uma data válida.");
				campoData.setValue("");
				dataValida = false;
			}
		}
		if(dataValida){
			ArgentumTableModel model = new ArgentumTableModel(negocios);
			tabela.setModel(model);
			
			CandleFactory fabrica = new CandleFactory();
			List<Candle> candles = fabrica.constroiCandles(negocios);
			SerieTemporal serie = new SerieTemporal(candles);
			
			GeradorDeGrafico gerador = new GeradorDeGrafico(serie, 2, serie.getTotal() - 1);
			List<Indicador> indicadores = menu.getIndicadoresSelecionados();
			for(Indicador indicador : indicadores){
				gerador.plotaIndicador(indicador);
			}
			abas.setComponentAt(1, gerador.getPanel());
		}
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
		painelBotoes.add(botaoSair);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}
}
