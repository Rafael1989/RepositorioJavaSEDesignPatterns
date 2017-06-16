package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class IndicadorMinimo implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getMinimo();
	}
	
	@Override
	public String toString() {
		return "Indicador Mínimo";
	}

}
