package actions;

import java.util.ArrayList;
import java.util.List;

import noticias.Noticia;
import noticias.SiteRSS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import daos.SiteRSSDAO;

public class RssAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Noticia> noticias;
	private ApplicationContext ctx; 
	
	public String index() {
		ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		SiteRSSDAO dao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		noticias = new ArrayList<Noticia>();
		
		// Pega todos os links de notícias cadastrados no banco
		List<SiteRSS> sites = dao.selectAll(); 
		
		for (int i = 0; i < sites.size(); i++) {
			Noticia noticia = new Noticia();
			noticia.setLink(sites.get(i).getLink());
			noticia.setNoticia("bla bla asksdajkf jasdkj fksadjk f");
			noticia.setTitulo("titulo " + i);
			noticias.add(noticia);
		}

		return "index";
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
	
}
