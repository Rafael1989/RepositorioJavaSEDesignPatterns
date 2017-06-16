package br.com.caelum.argentum.testes;

import java.util.List;

import javax.swing.JFrame;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.reader.CandleFactory;
import br.com.caelum.argentum.ui.EscolhedorDeXML;

public class TestaGraficoCandle {
	public static void main(String[] args) {
		List<Negocio> negocios = new EscolhedorDeXML().escolhe();
		List<Candle> candles = new CandleFactory().constroiCandles(negocios);
		GeradorDeGrafico g = new GeradorDeGrafico(candles);
		JFrame frame = new JFrame("Minha Janela");
		frame.add(g.getPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
