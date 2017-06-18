package br.com.caelum.argentum.ui;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ArgentumTableModel extends AbstractTableModel{

	private final List<?> lista;
	private Class<?> classe;
	
	public ArgentumTableModel(List<?> lista) {
		this.lista = lista;
		this.classe = lista.get(0).getClass();
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		int colunas = 0;
		for(Method metodo : classe.getDeclaredMethods()){
			if(metodo.isAnnotationPresent(Coluna.class)){
				colunas++;
			}
		}
		return colunas;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		try{
			Object objeto = lista.get(linha);
			for(Method metodo : classe.getDeclaredMethods()){
				if(metodo.isAnnotationPresent(Coluna.class)){
					Coluna anotacao = metodo.getAnnotation(Coluna.class);
					if(anotacao.posicao() == coluna)
						return String.format(anotacao.formato(), metodo.invoke(objeto));
				}
			}
		}catch(Exception e){
			return "Erro";
		}
		return "";
	}
	
	@Override
	public String getColumnName(int coluna) {
		for(Method metodo : classe.getDeclaredMethods()){
			if(metodo.isAnnotationPresent(Coluna.class)){
				Coluna anotacao = metodo.getAnnotation(Coluna.class);
				if(anotacao.posicao() == coluna)
					return anotacao.nome();
			}
		}
		return "";
	}

	
}
