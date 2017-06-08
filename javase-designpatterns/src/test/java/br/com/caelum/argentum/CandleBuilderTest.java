package br.com.caelum.argentum;

import org.junit.Test;


public class CandleBuilderTest {

	@Test(expected=IllegalArgumentException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios(){
		new CandleBuilder().comAbertura(10).comFechamento(10).geraCandle();
	}

}
