package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.CandleBuilder;
import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandleFactory {
	
	public Candle constroiCandleParaData(Calendar data, List<Negocio> negocios){
		double maximo = negocios.isEmpty()?0:Double.MIN_VALUE;
		double minimo = negocios.isEmpty()?0:Double.MAX_VALUE;
		double volume = 0;
		
		for(Negocio negocio : negocios){
			volume += negocio.getVolume();
			
			double preco = negocio.getPreco();
			if(preco > maximo){
				maximo = preco;
			}
			if(preco < minimo){
				minimo = preco;
			}
		}
		
		double abertura = negocios.isEmpty()?0:negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty()?0:negocios.get(negocios.size()-1).getPreco();
		
		return new CandleBuilder().comAbertura(abertura).comFechamento(fechamento).comMinimo(minimo).comMaximo(maximo).comVolume(volume).comData(data).geraCandle();
	}

	public List<Candle> constroiCandles(List<Negocio> todosNegocios) {
		java.util.Collections.sort(todosNegocios);
		List<Candle> candles = new ArrayList<>();
		
		List<Negocio> negociosDoDia = new ArrayList<>();
		Calendar dataAtual = todosNegocios.get(0).getData();
		
		for(Negocio negocio : todosNegocios){
			if(negocio.getData().before(dataAtual)){
				throw new IllegalArgumentException("negocios em ordem errada");
			}
			if(!negocio.isMesmoDia(dataAtual)){
				criaEGuardaCandle(candles, negociosDoDia, dataAtual);
				negociosDoDia = new ArrayList<>();
				dataAtual = negocio.getData();
			}
			negociosDoDia.add(negocio);
		}
		
		criaEGuardaCandle(candles, negociosDoDia, dataAtual);
		
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles, List<Negocio> negociosDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
		candles.add(candleDoDia);
	}
	
	
}
