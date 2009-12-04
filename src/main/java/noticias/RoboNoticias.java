package noticias;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.sql.rowset.spi.XmlReader;

import org.xml.sax.helpers.DefaultHandler;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

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
		
		List <Noticia> noticias = new ArrayList<Noticia>();
		
		// Pega as noticias
		try{
			URL rssurl = new URL(this.url);

			System.out.println(url);
			
			XmlReader reader = new XmlReader(rssurl);
			
			SyndFeed feed = new SyndFeedInput().build(reader);

			for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
				Noticia n = new Noticia();
				SyndEntry entry = (SyndEntry) i.next();
				n.setLink(entry.getLink());
				n.setNoticia(entry.getDescription().getValue());
				n.setTitulo(entry.getTitle());
				noticias.add(n);
				//break;
			}
			if (reader != null)
				reader.close();
			return noticias;
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ArrayList<Noticia>();
	}
	
	
}
