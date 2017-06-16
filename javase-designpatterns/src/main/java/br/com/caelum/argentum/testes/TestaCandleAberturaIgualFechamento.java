package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandleFactory;

public class TestaCandleAberturaIgualFechamento {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.0, 100, hoje);
		Negocio negocio2 = new Negocio(40.0, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1,negocio2);
		
		CandleFactory fabrica = new CandleFactory();
		Candle candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		System.out.println(candle.isBaixa());
	}
}
