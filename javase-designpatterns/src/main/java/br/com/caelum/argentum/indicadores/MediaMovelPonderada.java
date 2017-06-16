package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelPonderada implements Indicador{
	
	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	
	public double calcula(int posicao, SerieTemporal serie){
		double soma = 0.0;
		int peso = 1;
		for(int i = posicao - 2; i <= posicao; i++){
			Candle c = serie.getCandle(i);
			soma += outroIndicador.calcula(i, serie) * peso;
			peso++;
		}
		return soma / 6;
	}
	
	@Override
	public String toString() {
		return "Média ponderada do " + outroIndicador;
	}
}
