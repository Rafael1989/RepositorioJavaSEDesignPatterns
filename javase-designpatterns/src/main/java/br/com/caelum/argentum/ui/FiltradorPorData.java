package br.com.caelum.argentum.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import br.com.caelum.argentum.Negocio;

public class FiltradorPorData {
	
	private Calendar dataInicial;

	public FiltradorPorData(String dataDigitada) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			
			dataInicial = Calendar.getInstance();
			dataInicial.setTime(sdf.parse(dataDigitada));
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
	}

	public void filtra(List<Negocio> negocios) {
		if(dataInicial == null)
			return;
		Iterator<Negocio> it = negocios.iterator();
		while(it.hasNext()){
			if(it.next().getData().before(dataInicial))
				it.remove();
		}
	}

}
