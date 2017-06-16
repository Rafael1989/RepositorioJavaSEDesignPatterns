package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Negocio;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmNegocioEmListaUnitaria() throws FileNotFoundException{
		String xmlDeTeste = "<list>"+
							"	<negocio>"+
							"		<preco>43.5</preco>"+
							"		<quantidade>1000</quantidade>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		assertEquals(1, negocios.size());
		assertEquals(43.5, negocios.get(0).getPreco(),0.00001);
		assertEquals(1000, negocios.get(0).getQuantidade());
	}
	
	@Test
	public void carregaXmlComZeroNegocios() throws FileNotFoundException{
		String xmlDeTeste = "<list></list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		assertEquals(0, negocios.size());
	}
	
	@Test
	public void carregaXmlSemPreco() throws FileNotFoundException{
		String xmlDeTeste = "<list>"+
							"	<negocio>"+
							"		<quantidade>100</quantidade>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		assertEquals(0.0, negocios.get(0).getPreco(),0.00001);
	}
	
	@Test
	public void carregaXmlSemQuantidade() throws FileNotFoundException{
		String xmlDeTeste = "<list>"+
							"	<negocio>"+
							"		<preco>10</preco>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		assertEquals(0.0, negocios.get(0).getQuantidade(),0.00001);
	}
	
	@Test
	public void carregaXmlMaisDeUmNegocio() throws FileNotFoundException{
		String xmlDeTeste = "<list>"+
							"	<negocio>"+
							"		<preco>10</preco>"+
							"		<quantidade>100</quantidade>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"	<negocio>"+
							"		<preco>10</preco>"+
							"		<quantidade>100</quantidade>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"	<negocio>"+
							"		<preco>10</preco>"+
							"		<quantidade>100</quantidade>"+
							"		<data>"+
							"			<time>1322233344455</time>"+
							"		</data>"+
							"	</negocio>"+
							"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		assertEquals(3, negocios.size());
	}
}