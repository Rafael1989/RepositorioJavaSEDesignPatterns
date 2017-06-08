package br.com.caelum.argentum.testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class TestaCandlestickOrdemDecrescente {
	public static void main(String[] args) {
		
		Calendar natal = new GregorianCalendar(2016, 11, 25);
		Calendar aniversario = new GregorianCalendar(2016, 6, 21);
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(60.5, 100, natal);
		Negocio negocio2 = new Negocio(55.5, 100, natal);
		Negocio negocio3 = new Negocio(57.0, 100, natal);
	
		Negocio negocio4 = new Negocio(60.0, 100, aniversario);
		Negocio negocio5 = new Negocio(45.0, 100, aniversario);
		Negocio negocio6 = new Negocio(57.5, 100, aniversario);
		
		List<Negocio> negocios = Arrays.asList(negocio1,negocio2,negocio3,negocio4,negocio5,negocio6);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		System.out.println(candle);
	}
}
