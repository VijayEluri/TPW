package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDAO;

/**
 * Classe responsável por checagens de segurança 
 * @author tjcampos
 */
public class Seguranca extends ActionSupport {
	private static final long serialVersionUID = 1L;
	static ClassPathXmlApplicationContext ctx;
	static private UsuarioDAO usuarioDao;
	
	static private HttpServletRequest request;
	static private HttpSession session;
	
	static {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		usuarioDao = (UsuarioDAO) ctx.getBean("usuarioDAO");
	}
	
	/**
	 * Checa se o usuário é administrador e em caso de erro configura action support
	 * @return true em caso de administrador, false caso contrário.
	 */
	public static boolean checkAdministrador(ActionSupport as) {
		Usuario usuario = null;
		boolean error = false;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		String login = (String) session.getAttribute("login");
		
		if (login == null) {
			if (as != null) as.addActionError("Você deve estar logado");
			error = true;
		}
		else {
			usuario = (Usuario) usuarioDao.selectByLogin(login);
			
			// Testa se o login foi digitado
			if (usuario == null) {
				if (as != null) as.addActionError("Erro ao identificar usuário");
				error = true;
			}
			else if (!usuario.getTipoUsuario().equals("ADMINISTRADOR")) {
				if (as != null) as.addActionError("Erro você deve estar logado como administrador");
				error = true;
			}
		}
		return !error;
	}
	
	public static boolean checkUsuario(ActionSupport as) {
		Usuario usuario = null;
		boolean error = false;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		String login = (String) session.getAttribute("login");
		
		if (login == null) {
			if (as != null) as.addActionError("Você deve estar logado");
			error = true;
		}
		else {
			usuario = (Usuario) usuarioDao.selectByLogin(login);
			
			// Testa se o login foi digitado
			if (usuario == null) {
				if (as != null) as.addActionError("Erro ao identificar usuário");
				error = true;
			}
		}
		return !error;
	}
	
	public static Usuario getUsuario(){
		Usuario usuario = null;
		
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		String login = (String) session.getAttribute("login");
		
		if (login != null) {
			usuario = (Usuario) usuarioDao.selectByLogin(login);
		}
		return usuario;
	}
}
