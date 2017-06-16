package br.com.caelum.argentum.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.Negocio;

public class LeitorXML {

	public List<Negocio> carrega(Reader fonte) throws FileNotFoundException{
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		return (List<Negocio>)stream.fromXML(fonte);
	}
}
