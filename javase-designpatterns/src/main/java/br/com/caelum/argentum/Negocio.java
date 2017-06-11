package br.com.caelum.argentum;

import java.util.Calendar;

public final class Negocio implements Comparable<Negocio>{

	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(double preco, int quantidade, Calendar data) {
		if(quantidade > 0 && quantidade != 0 && data != null){
			this.preco = preco;
			this.quantidade = quantidade;
			this.data = data;
		}else{
			throw new IllegalArgumentException();
		}
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	public double getVolume(){
		return preco * quantidade;
	}

	public boolean isMesmoDia(Calendar outraData) {
		//return this.data.equals(outraData); errado pois compara o timestamp, e hora diferente e mesmo dia é igual
		return data.get(Calendar.DATE) == outraData.get(Calendar.DATE) && 
				data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH) &&
				data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}

	@Override
	public int compareTo(Negocio outroNegocio) {
		return this.data.getTime().compareTo(outroNegocio.getData().getTime());
	}

}
