package actions;

import java.util.List;

import noticias.SiteRSS;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.Seguranca;

import com.opensymphony.xwork2.ActionSupport;

import daos.SiteRSSDAO;

public class RSSAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private SiteRSS siteRSS;
	private List<SiteRSS> sitesRSS;
	ClassPathXmlApplicationContext ctx;
	private SiteRSSDAO siterssDao;
	
	RSSAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		siterssDao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		siteRSS = siteRSS == null ? new SiteRSS() : siteRSS;
	}
	
	public String listSiteRSS(){
		if (Seguranca.checkAdministrador(this)) return "listError";
		
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	public String insertSiteRSS() {
		if (Seguranca.checkAdministrador(this)) return "insertError";
		
		siterssDao.save(siteRSS);
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	public String deleteSiteRSS(){
		if (Seguranca.checkAdministrador(this)) return "deleteError";
		
		siterssDao.remove(siteRSS);
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}

	public SiteRSS getSiteRSS() {
		return siteRSS;
	}

	public void setSiteRSS(SiteRSS siteRSS) {
		this.siteRSS = siteRSS;
	}

	public List<SiteRSS> getSitesRSS() {
		return sitesRSS;
	}

	public void setSitesRSS(List<SiteRSS> sitesRSS) {
		this.sitesRSS = sitesRSS;
	}
}