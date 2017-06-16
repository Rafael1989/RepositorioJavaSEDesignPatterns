package br.com.caelum.argentum.grafico;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;
import org.jfree.data.xy.OHLCDataset;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.indicadores.Indicador;

public class GeradorDeGrafico {
	
	
	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private DefaultCategoryDataset dados;
	private JFreeChart grafico;

	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.dados = new DefaultCategoryDataset();
		this.grafico = ChartFactory.createLineChart("Indicadores", "Dias", "Valores", dados, PlotOrientation.VERTICAL, true, true, false);
		this.grafico.setBackgroundPaint(new java.awt.Color(0, 255, 0));
		this.grafico.setBorderPaint(new java.awt.Color(0, 0, 255));
	}
	
	public GeradorDeGrafico(List<Candle> candles){
		OHLCDataItem[] data = null;
		int posicao = 0;
		for(Candle candle : candles){
			OHLCDataItem ohlcDataItem = new OHLCDataItem(candle.getData().getTime(), candle.getAbertura(), candle.getMaximo(), candle.getMinimo(), candle.getFechamento(), candle.getVolume());
			data[posicao] = ohlcDataItem;
			posicao++;
		}
		Comparable cnull = null;
		OHLCDataset dataset = new DefaultOHLCDataset(cnull, data);
		ChartFactory.createCandlestickChart("Candle", "Data", "Valor", dataset, true);
	}
	
	public void plotaIndicador(Indicador ind){
		for(int i = comeco; i <= fim; i++){
			double valor = ind.calcula(i, serie);
			dados.addValue(valor, ind.toString(), Integer.valueOf(i));
		}
	}
	
	public void salva(OutputStream out) throws IOException{
		ChartUtilities.writeChartAsPNG(out, grafico, 500, 500);
	}
	
	public JPanel getPanel(){
		return new ChartPanel(grafico);
	}
}
