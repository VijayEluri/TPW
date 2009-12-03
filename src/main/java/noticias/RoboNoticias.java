package noticias;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Attributes;

public class RoboNoticias extends DefaultHandler {

	String url;
	StringBuffer valor;

    String title;
    String link;
    String description;
	
	List<Noticia> noticias;
	
	public RoboNoticias(String url) {
		this.url = url;
		noticias = new ArrayList<Noticia>();
	}
	
	// Retorna as notícias da URL
	public List<Noticia> getNoticias() {
		
		// Pega as noticias
		try {
			URL site = new URL(url);
			URLConnection sld = site.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(sld.getInputStream()));
			BufferedWriter out = 
				new BufferedWriter(new FileWriter("/tmp/file.xml"));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null)  {
				out.write(inputLine+"\n");
			}
			out.close();
			in.close();
			
			InputStream ins = new BufferedInputStream(
					new FileInputStream(new File("/tmp/file.xml")));
			this.readXML(ins);
		} catch (Exception e){
			e.printStackTrace();
		}
		return noticias;
	}
	
	/** Read XML from input stream and parse, generating SAX events */
    public void readXML(InputStream inStream) {
        try {
            System.setProperty("org.xml.sax.driver", "org.apache.crimson.parser.XMLReaderImpl");
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(this);
            reader.parse(new InputSource(new InputStreamReader(inStream)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void characters(char[] buffer, int start, int length) {
	    valor.append(buffer, start, length);
    }
    
    public void startElement (String namespaceURI,
                              String localName,
                              String qName, 
                              Attributes atts)
        throws SAXException {

    	valor.setLength(0);
    }

    public void endElement(String namespaceURI, 
                           String localName,
                           String qName)
        throws SAXException {

    	if (qName.equals("title")) {
    		title = valor.toString();
    	} else if (qName.equals("link")) {
    		link = valor.toString();
    	} else if (qName.equals("description")) {
    		description = valor.toString();
    	} else if (qName.equals("item")) {
    		try {
    			Noticia noticia = new Noticia();
    			noticia.setLink(link);
    			noticia.setNoticia(title);
    			noticia.setTitulo(description);
    			noticias.add(noticia);
    		} catch (Exception e) { e.printStackTrace(); }
    	}
    	valor.setLength(0);
    }
}
