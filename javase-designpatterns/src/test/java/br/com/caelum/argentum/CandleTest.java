package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class CandleTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle(3, 4, 4, 3, 1, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeTerDataNula(){
		new Candle(3, 3, 3, 3, 3, null);
	}

	@Test
	public void quandoAberturaIgualFechamentoEhAlta(){
		Candle candlestick = new Candle(10, 10, 5, 10, 10, Calendar.getInstance());
		
		assertEquals(true, candlestick.isAlta());
		
	}
}
