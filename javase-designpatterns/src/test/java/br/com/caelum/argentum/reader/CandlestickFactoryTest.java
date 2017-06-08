package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegocios() {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1,negocio2,negocio3,negocio4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(42.3, candle.getFechamento(), 0.00001);
		assertEquals(39.8, candle.getMinimo(), 0.00001);
		assertEquals(45.0, candle.getMaximo(), 0.00001);
		assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void semNegociosGeraCandleComZeros(){
		Calendar hoje = Calendar.getInstance();
		
		List<Negocio> negocios = Arrays.asList();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(0.0, candle.getAbertura(), 0.00001);
		assertEquals(0.0, candle.getFechamento(), 0.00001);
		assertEquals(0.0, candle.getMinimo(), 0.00001);
		assertEquals(0.0, candle.getMaximo(), 0.00001);
		assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void apenasUmNegocioGeraCandleComValoresIguais(){
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(40.5, candle.getFechamento(), 0.00001);
		assertEquals(40.5, candle.getMinimo(), 0.00001);
		assertEquals(40.5, candle.getMaximo(),0.00001);
		assertEquals(4050.0, candle.getVolume(),0.00001);
	}
	
	@Test
	public void negociosEmOrdemCrescenteDeValor(){
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(1, 1, hoje);
		Negocio negocio2 = new Negocio(2, 1, hoje);
		Negocio negocio3 = new Negocio(3, 1, hoje);
		Negocio negocio4 = new Negocio(4, 1, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1,negocio2,negocio3,negocio4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(1, candle.getAbertura(), 0.00001);
		assertEquals(4, candle.getFechamento(), 0.00001);
		assertEquals(1, candle.getMinimo(), 0.00001);
		assertEquals(4, candle.getMaximo(), 0.00001);
		assertEquals(10, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void negociosEmOrdemDecrescenteDeValor(){
		Calendar aniversario = new GregorianCalendar(2017, 6, 21);
		
		Negocio negocio1 = new Negocio(4, 1, aniversario);
		Negocio negocio2 = new Negocio(3, 1, aniversario);
		Negocio negocio3 = new Negocio(2, 1, aniversario);
		Negocio negocio4 = new Negocio(1, 1, aniversario);
		
		List<Negocio> negocios = Arrays.asList(negocio1,negocio2,negocio3,negocio4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(aniversario, negocios);
		
		assertEquals(4, candle.getAbertura(), 0.00001);
		assertEquals(1, candle.getFechamento(),0.00001);
		assertEquals(1,candle.getMinimo(),0.00001);
		assertEquals(4, candle.getMaximo(), 0.00001);
		assertEquals(10, candle.getVolume(), 0.00001);
	}

}
