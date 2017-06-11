package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.CandleBuilder;
import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negocio> negocios){
		double maximo = negocios.isEmpty()?0:Double.MIN_VALUE;
		double minimo = negocios.isEmpty()?0:Double.MAX_VALUE;
		double volume = 0;
		
		for(Negocio negocio : negocios){
			volume += negocio.getVolume();
			
			if(negocio.getPreco() > maximo){
				maximo = negocio.getPreco();
			}
			if(negocio.getPreco() < minimo){
				minimo = negocio.getPreco();
			}
		}
		
		double abertura = negocios.isEmpty()?0:negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty()?0:negocios.get(negocios.size()-1).getPreco();
		
		return new CandleBuilder().comAbertura(abertura).comFechamento(fechamento).comMinimo(minimo).comMaximo(maximo).comVolume(volume).comData(data).geraCandle();
	}

	public List<Candlestick> constroiCandles(List<Negocio> todosNegocios) {
		java.util.Collections.sort(todosNegocios);
		List<Candlestick> candles = new ArrayList<>();
		
		List<Negocio> negociosDoDia = new ArrayList<>();
		Calendar dataAtual = todosNegocios.get(0).getData();
		
		for(Negocio negocio : todosNegocios){
			if(negocio.getData().before(dataAtual)){
				throw new IllegalArgumentException("negocios em ordem errada");
			}
			if(!negocio.isMesmoDia(dataAtual)){
				Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
				candles.add(candleDoDia);
				negociosDoDia = new ArrayList<>();
				dataAtual = negocio.getData();
			}
			negociosDoDia.add(negocio);
		}
		
		Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
		candles.add(candleDoDia);
		
		return candles;
	}
	
	
}
