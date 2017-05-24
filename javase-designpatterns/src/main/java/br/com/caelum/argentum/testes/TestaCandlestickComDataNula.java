package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class TestaCandlestickComDataNula {
	public static void main(String[] args) {
		Negocio negocio1 = new Negocio(10, 5, null);
		
		List<Negocio> negocios = Arrays.asList(negocio1);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(null, negocios);
		
		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());
		
		
	}
}
