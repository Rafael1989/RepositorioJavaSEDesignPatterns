package br.com.caelum.argentum;

import java.util.Calendar;

import br.com.caelum.argentum.ui.Coluna;

public final class Negocio implements Comparable<Negocio>{

	private final double preco;
	private final int quantidade;
	private final Calendar data;
	private final double volume;

	public Negocio(double preco, int quantidade, Calendar data) {
		if(quantidade > 0 && quantidade != 0 && data != null){
			this.preco = preco;
			this.quantidade = quantidade;
			this.data = data;
		}else{
			throw new IllegalArgumentException();
		}
	}

	@Coluna(nome="Preço", posicao=0, formato="R$ %,#.2f")
	public double getPreco() {
		return preco;
	}

	@Coluna(nome="Quantidade", posicao=1)
	public int getQuantidade() {
		return quantidade;
	}

	@Coluna(nome="Data", posicao=3, formato="%1$td/%1$tm/%1$tY")
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	@Coluna(nome="Volume", posicao=2, formato="R$ %,#.2f")
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
