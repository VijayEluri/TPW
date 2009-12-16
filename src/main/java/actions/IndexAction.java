package actions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import noticias.Noticia;
import noticias.RoboNoticias;
import noticias.SiteRSS;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Minicurso;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.MinicursoDAO;
import daos.SiteRSSDAO;
import daos.UsuarioDAO;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Noticia> noticias, noticiasAux;
	private ApplicationContext ctx; 

	private Usuario usuario;

	private UsuarioDAO usuarioDAO;
	private MinicursoDAO minicursoDAO;

	private HttpServletRequest request;
	private HttpSession session;

	private List<Minicurso> minicursos;

	// Número máximo de notícias a ser pego de cada RSS
	private final int MAX_NOTICIAS = 5;

	public IndexAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		usuarioDAO = (UsuarioDAO) ctx.getBean("usuarioDAO");
		minicursoDAO = (MinicursoDAO) ctx.getBean("minicursoDAO");
		usuario = usuario == null ? new Usuario() : usuario;
	}

	public String index() {		
		fillRSS();
		minicursos = minicursoDAO.selectLast();
		return "index";
	}

	private void fillRSS() {
		SiteRSSDAO dao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		noticias = new ArrayList<Noticia>();

		// Pega todos os links de notícias cadastrados no banco
		List<SiteRSS> sites = dao.selectAll(); 

		for (SiteRSS site : sites) {

			RoboNoticias robo = new RoboNoticias(site.getLink());
			noticiasAux = robo.getNoticias();

			int total = 0;
			for (Noticia noticia : noticiasAux) {
				noticias.add(noticia);

				// Se já pegou o número máximo de notícias pega o próximo feed
				if (total++ >= MAX_NOTICIAS-1)
					break;
			}

		}
	}

	public String login() {
		// Busca no banco para ver se login e senha são válidos
		Usuario u = usuarioDAO.selectByLogin(usuario.getLogin());

		request = ServletActionContext.getRequest();
		session = request.getSession();

		if (u != null){

			String hashedPassword="";
			//Faz a criptografia MD5 da senha do usuario
			try {
				MessageDigest md = MessageDigest.getInstance( "MD5" );
				md.update( usuario.getPassword().getBytes() );  
				BigInteger hash = new BigInteger( 1, md.digest() );  
				hashedPassword = (hash.toString( 16 ));
			} catch (NoSuchAlgorithmException e) {
				addActionError("Problemas com a senha. Tente novamente.");
				e.printStackTrace();
			}

			if (u.getPassword().equals(hashedPassword)) {			
				//Grava os dados na sessão
				session.setAttribute("login", u.getLogin());
				session.setAttribute("password", u.getPassword());
				session.setAttribute("nome", u.getNome());
				session.setAttribute("tipoUsuario", u.getTipoUsuario());
				session.setAttribute("status", "logado");
			}
			else { 
				addActionError("Usuario e Senha nao conferem!!!");
				session.invalidate();
			}
		} else {
			addActionError("Usuario e Senha nao conferem!!!");
			session.invalidate();
		}
		fillRSS();
		return "index";
	}

	public String logout() {
		request = ServletActionContext.getRequest();
		session = request.getSession();		
		session.invalidate();
		fillRSS();
		return "index";
	}


	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<Minicurso> getMinicursos() {
		return minicursos;
	}

	public void setMinicursos(List<Minicurso> minicursos) {
		this.minicursos = minicursos;
	}

}
