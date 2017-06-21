package br.com.caelum.argentum.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.IndicadorMaximo;
import br.com.caelum.argentum.indicadores.IndicadorMinimo;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class MenuIndicadores {

	private JMenuBar menuBar;
	private HashMap<JCheckBoxMenuItem, Indicador> indicadoresNoMenu;

	public MenuIndicadores() {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		indicadores.add(new IndicadorAbertura());
		indicadores.add(new IndicadorFechamento());
		indicadores.add(new IndicadorMaximo());
		indicadores.add(new IndicadorMinimo());
		indicadores.add(new MediaMovelPonderada(new IndicadorFechamento()));
		indicadores.add(new MediaMovelSimples(3, new IndicadorFechamento()));
		
		menuBar = new JMenuBar();
		JMenu menuIndicadores = new JMenu("Indicadores");
		menuBar.add(menuIndicadores);
		
		indicadoresNoMenu = new HashMap<JCheckBoxMenuItem, Indicador>();
		
		for(Indicador indicador : indicadores){
			JCheckBoxMenuItem opcaoIndicador = new JCheckBoxMenuItem(indicador.toString(), true);
			menuIndicadores.add(opcaoIndicador);
			indicadoresNoMenu.put(opcaoIndicador, indicador);
		}
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public List<Indicador> getIndicadoresSelecionados() {
		ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
		for(JCheckBoxMenuItem menuItem : indicadoresNoMenu.keySet()){
			if(menuItem.isSelected())
				indicadores.add(indicadoresNoMenu.get(menuItem));
		}
		return indicadores;
	}
	
}
