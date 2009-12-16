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

/**
 * Classe para a pagina principal do Sanca Livre
 * Geralmente chamadas pelo struts. 
 * @author vendra
 *
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * Lista de Noticias
	 */
	private List<Noticia> noticias, noticiasAux;
	
	//spring
	private ApplicationContext ctx; 

	/**
	 * Usuario
	 */
	private Usuario usuario;

	/*
	 * Daos
	 */
	private UsuarioDAO usuarioDAO;
	private MinicursoDAO minicursoDAO;

	//Sessao
	private HttpServletRequest request;
	private HttpSession session;

	/**
	 * Lista de Minicursos
	 */
	private List<Minicurso> minicursos;

	// Número máximo de notícias a ser pego de cada RSS
	private final int MAX_NOTICIAS = 5;

	/**
	 * Construtor: Inicializa o dao e ctx
	 */
	public IndexAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		usuarioDAO = (UsuarioDAO) ctx.getBean("usuarioDAO");
		minicursoDAO = (MinicursoDAO) ctx.getBean("minicursoDAO");
		usuario = usuario == null ? new Usuario() : usuario;
	}

	/**
	 * Metodo que preenche os dados do RSS, pega os ultimos posts, minicursos, etc
	 * @return
	 */
	public String index() {		
		fillRSS();
		minicursos = minicursoDAO.selectLast();
		return "index";
	}

	/*
	 * Preenche a lista de Noticias com os dados dos RSS
	 */
	private void fillRSS() {
		SiteRSSDAO dao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
		noticias = new ArrayList<Noticia>();

		// Pega todos os links de notícias cadastrados no banco
		List<SiteRSS> sites = dao.selectAll(); 

		//Para link cadastrado no banco, pega o XML correspondente
		for (SiteRSS site : sites) {

			//Chama o RoboNoticias que ira retornar a lista de Noticias que contem no link
			RoboNoticias robo = new RoboNoticias(site.getLink());
			noticiasAux = robo.getNoticias();

			int total = 0;
			
			//Para cada noticia neste Link, adiciona um numero MAX_NOTICIAS na lista de noticias
			for (Noticia noticia : noticiasAux) {
				noticias.add(noticia);

				// Se já pegou o número máximo de notícias pega o próximo feed
				if (total++ >= MAX_NOTICIAS-1)
					break;
			}

		}
	}

	/**
	 * Realiza o login do usuario
	 * @return "index" para o struts
	 */
	public String login() {
		
		// Busca no banco para ver se login e senha são válidos
		Usuario u = usuarioDAO.selectByLogin(usuario.getLogin());

		request = ServletActionContext.getRequest();
		session = request.getSession();

		if (u != null){

			//Faz a criptografia MD5 da senha do usuario
			String hashedPassword="";
			try {
				MessageDigest md = MessageDigest.getInstance( "MD5" );
				md.update( usuario.getPassword().getBytes() );  
				BigInteger hash = new BigInteger( 1, md.digest() );  
				hashedPassword = (hash.toString( 16 ));
			} catch (NoSuchAlgorithmException e) {
				addActionError("Problemas com a senha. Tente novamente.");
				e.printStackTrace();
			}

			//Verifica se os passwords bateram
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
		
		//Se tudo ocorreu normalmente, tera sido criado uma sessao

		//Preenche as noticias
		fillRSS();
		return "index";
	}

	/**
	 * Realiza o logout do usuario
	 * @return
	 */
	public String logout() {
		
		//Sessao
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		//Invalida
		session.invalidate();
		
		//Preenche as noticias
		fillRSS();
		return "index";
	}


	/*
	 * =================
	 * Setters e Getters
	 * =================
	 */
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
