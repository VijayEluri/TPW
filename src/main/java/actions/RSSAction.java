package actions;

import java.util.List;

import noticias.SiteRSS;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.Seguranca;

import com.opensymphony.xwork2.ActionSupport;

import daos.SiteRSSDAO;

/**
 * Classe chamada para adicionar, ou remover fontes de RSS
 * Geralmente chamadas pelo struts. 
 * @author tjcampos
 */
public class RSSAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * RSS da ação
	 */
	private SiteRSS siteRSS;
	/**
	 * Lista de actions
	 */
	private List<SiteRSS> sitesRSS;
	
	//spring
	ClassPathXmlApplicationContext ctx;
	
	//Dao
	private SiteRSSDAO siterssDao;
	
	/**
	 * Construtor: Inicializa o post, dao e ctx
	 */
	RSSAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		siterssDao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		siteRSS = siteRSS == null ? new SiteRSS() : siteRSS;
	}
	
	/**
	 * Lista todas as fontes de RSS 
	 * @return "listSiteRSS" para o struts
	 */
	public String listSiteRSS(){
		if (!Seguranca.checkAdministrador(this)) return "listError";
		
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	/**
	 * adicona fonte de RSS
	 * @return "listSiteRSS" para o struts 
	 */
	public String insertSiteRSS() {
		if (!Seguranca.checkAdministrador(this)) return "insertError";
		
		siterssDao.save(siteRSS);
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	/**
	 * Remove site da fonte de RSS
	 * @return "listSiteRSS" para o struts
	 */
	public String deleteSiteRSS(){
		if (!Seguranca.checkAdministrador(this)) return "deleteError";
		
		siterssDao.remove(siteRSS);
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}

	/*
	 * =================
	 * Setters e Getters
	 * =================
	 */
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