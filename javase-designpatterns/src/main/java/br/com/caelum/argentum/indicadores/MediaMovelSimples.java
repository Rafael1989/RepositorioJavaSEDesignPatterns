package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	
	private int intervalo;

	public MediaMovelSimples(int intervalo) {
		this.intervalo = intervalo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.caelum.argentum.indicadores.Indicador#calcula(int, br.com.caelum.argentum.SerieTemporal)
	 */
	@Override
	public double calcula(int posicao, SerieTemporal serie){
		double soma = 0.0;
		for(int i = posicao - 2; i <= posicao; i++){
			soma += serie.getCandle(i).getFechamento();
		}
		return soma / this.intervalo;
	}
	
	@Override
	public String toString() {
		return "Média móvel simple do fechamento.";
	}
}
