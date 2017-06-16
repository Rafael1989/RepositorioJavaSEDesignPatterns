package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandleFactory;

public class TestaCandleComDataNula {
	public static void main(String[] args) {
		Negocio negocio1 = new Negocio(10, 5, null);
		
		List<Negocio> negocios = Arrays.asList(negocio1);
		
		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(null, negocios);
		
		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());
		
		
	}
}
