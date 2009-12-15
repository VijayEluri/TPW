package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import noticias.SiteRSS;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.SiteRSSDAO;
import daos.UsuarioDAO;

public class RSSAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private SiteRSS siteRSS;
	private List<SiteRSS> sitesRSS;
	ClassPathXmlApplicationContext ctx;
	private SiteRSSDAO siterssDao;
	private UsuarioDAO usuarioDao;
	
	private HttpServletRequest request;
	private HttpSession session;
	
	RSSAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		siterssDao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		usuarioDao = (UsuarioDAO) ctx.getBean("usuarioDAO");
		siteRSS = siteRSS == null ? new SiteRSS() : siteRSS;


	}

	protected boolean validateUser(){
		Usuario usuario = null;
		boolean error = false;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		String login = (String) session.getAttribute("login");
		
		if (login == null) {
			addActionError("Você deve estar logado");
			error = true;
		}
		else {
			usuario = (Usuario) usuarioDao.selectByLogin(login);
			
			// Testa se o login foi digitado
			if (usuario == null) {
				addActionError("Erro ao identificar usuário");
				error = true;
			}
			else if (!usuario.getTipoUsuario().equals("ADMINISTRADOR")) {
				addActionError("Erro você deve estar logado como administrador");
				error = true;
			}
		}
		return error;
	}
	
	public String listSiteRSS(){
		if (validateUser()) return "listError";
		
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	public String insertSiteRSS() {
		if (validateUser()) return "insertError";
		
		siterssDao.save(siteRSS);
		sitesRSS = siterssDao.selectAll();
		return "listSiteRSS";
	}
	
	public String deleteSiteRSS(){
		if (validateUser()) return "deleteError";
		
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