package br.com.caelum.argentum;

import java.util.Calendar;

public class CandleBuilder {

	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;
	private boolean aberturaChamada;
	private boolean fechamentoChamado;
	private boolean minimoChamado;
	private boolean maximoChamado;
	private boolean volumeChamado;
	private boolean dataChamada;
	
	public CandleBuilder comAbertura(double abertura){
		this.abertura = abertura;
		aberturaChamada = true;
		return this;
	}
	
	public CandleBuilder comFechamento(double fechamento){
		this.fechamento = fechamento;
		fechamentoChamado = true;
		return this;
	}
	
	public CandleBuilder comMinimo(double minimo){
		this.minimo = minimo;
		minimoChamado = true;
		return this;
	}
	
	public CandleBuilder comMaximo(double maximo){
		this.maximo = maximo;
		maximoChamado = true;
		return this;
	}
	
	public CandleBuilder comVolume(double volume){
		this.volume = volume;
		volumeChamado = true;
		return this;
	}
	
	public CandleBuilder comData(Calendar data){
		this.data = data;
		dataChamada = true;
		return this;
	}
	
	public Candlestick geraCandle(){
		if(aberturaChamada && fechamentoChamado && minimoChamado && maximoChamado && volumeChamado && dataChamada){
			return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
		}else{
			throw new IllegalArgumentException();
		}
	}
	
}
