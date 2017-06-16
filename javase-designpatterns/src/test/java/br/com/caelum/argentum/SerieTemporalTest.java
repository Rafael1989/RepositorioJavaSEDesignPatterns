package br.com.caelum.argentum;

import static org.junit.Assert.*;

import org.junit.Test;

public class SerieTemporalTest {

	@Test(expected=IllegalArgumentException.class)
	public void naoAceitaDataNula() {
		SerieTemporal serieTemporal = new SerieTemporal(null);
	}

}
