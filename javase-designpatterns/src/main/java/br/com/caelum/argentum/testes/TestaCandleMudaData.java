package br.com.caelum.argentum.testes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandleFactory;

public class TestaCandleMudaData {
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(45.0, 100, hoje);
		List<Negocio> negocios = Arrays.asList(negocio1);
		CandleFactory fabrica = new CandleFactory();
		hoje.add(Calendar.DATE, 1);
		Candle candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		System.out.println(candle.getAbertura());
		System.out.println(candle.getFechamento());
		System.out.println(candle.getMinimo());
		System.out.println(candle.getMaximo());
		System.out.println(candle.getVolume());
		System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(hoje.getTime()));
	}
}
