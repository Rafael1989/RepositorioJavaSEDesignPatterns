package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NegocioTest {

	@Test
	public void dataDoNegocioEHImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negocio n = new Negocio(10, 5, c);
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegocioComDataNula(){
		new Negocio(10, 5, null);
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia(){
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negocio negocio = new Negocio(40.0, 100, agora);
		assertTrue(negocio.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void mesmoDiaHorasDiferentesEhDoMesmoDia(){
		Calendar manha = new GregorianCalendar(2011,10,20,8,30);
		Calendar tarde = new GregorianCalendar(2011,10,20,15,30);
		
		Negocio negocio = new Negocio(40.0, 100, manha);
		assertTrue(negocio.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia(){
		Calendar diaDesseMes = new GregorianCalendar(2011,6,14);
		Calendar diaDoMesPassado = new GregorianCalendar(2011,5,14);
		
		Negocio negocio = new Negocio(40.0, 100, diaDesseMes);
		assertFalse(negocio.isMesmoDia(diaDoMesPassado));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia(){
		Calendar diaDesseAno = new GregorianCalendar(2011,6,14);
		Calendar diaDoAnoPassado = new GregorianCalendar(2010,6,14);
		
		Negocio negocio = new Negocio(40.5, 100, diaDesseAno);
		assertFalse(negocio.isMesmoDia(diaDoAnoPassado));
	}

}
