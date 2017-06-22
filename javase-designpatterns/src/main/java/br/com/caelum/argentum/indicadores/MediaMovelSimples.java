package br.com.caelum.argentum.indicadores;

import org.apache.log4j.Logger;

import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	
	private static final Logger logger = Logger.getLogger(MediaMovelSimples.class);
	
	private int intervalo;
	private Indicador outroIndicador;

	public MediaMovelSimples(int intervalo, Indicador outroIndicador) {
		this.intervalo = intervalo;
		this.outroIndicador = outroIndicador;
	}
	
	/* (non-Javadoc)
	 * @see br.com.caelum.argentum.indicadores.Indicador#calcula(int, br.com.caelum.argentum.SerieTemporal)
	 */
	@Override
	public double calcula(int posicao, SerieTemporal serie){
		logger.info("Calculando m�dia m�vel simples para posi��o " + posicao);
		double soma = 0.0;
		for(int i = posicao - 2; i <= posicao; i++){
			soma += outroIndicador.calcula(i, serie);
		}
		return soma / this.intervalo;
	}
	
	@Override
	public String toString() {
		return "M�dia m�vel simple do " + outroIndicador;
	}
}
