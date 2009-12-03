package noticias;

import java.util.ArrayList;
import java.util.List;

public class RoboNoticias {

	String url;
	
	List<Noticia> noticias;
	
	public RoboNoticias(String url) {
		this.url = url;
		noticias = new ArrayList<Noticia>();
	}
	
	// Retorna as notícias da URL
	public List<Noticia> getNoticias() {
		
		// Pega as noticias
		Noticia noticia = new Noticia();
		noticia.setLink("");
		noticia.setNoticia("");
		noticia.setTitulo("");
		noticias.add(noticia);
		
		return noticias;
	}
	
}
