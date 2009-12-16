package actions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDAO;

/**
 * Classe responsável por cuidar das acoes do Usuario.
 * Geralmente chamadas pelo struts. 
 * @author vendra
 *
 */
public class UsuarioAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * Usuario da acao
	 */
	private Usuario usuario;
	
	/**
	 * Lista de usuarios
	 */
	private List<Usuario> usuarios;

	//Dao para acesso com o banco
	private UsuarioDAO dao;

	//Spring
	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	/**
	 * Construtor: Inicializa o usuario, dao e ctx
	 */
	public UsuarioAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (UsuarioDAO) ctx.getBean("usuarioDAO");
		usuario = usuario == null ? new Usuario() : usuario;		
	}

	/**
	 * Retorna a string "listUsuarios" que será lida pelo Struts. O Struts abre
	 * o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	 * a listUsuarios) e redireciona para a página listUsuarios.jsp
	 * @return "listUsuarios" para o struts
	 */
	public String listUsuarios() {
		
		//Verifica se usuario esta logado
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		
		//Verifica o tipo do usuario
		//Se for administrador ira mostrar todos.
		//Senao mostra somente ele.
		String tmpTipoUsuario = (String) session.getAttribute("tipoUsuario");
		if (tmpTipoUsuario != null && tmpTipoUsuario.equals("ADMINISTRADOR")){
			usuarios = dao.selectAll();
		} else {
			if (tmpLogin != null){
				List<Usuario> tmpList = new ArrayList<Usuario>();
				tmpList.add(dao.selectByLogin(tmpLogin));
				usuarios = tmpList;
			} else
				usuarios = null;
		}
		return "listUsuarios";
	}

	/**
	 * Insere o usuário no banco de dados. Esse método é chamado pelo form
	 * da página insertUsuario.jsp
	 * @return "listUsuarios" para o struts
	 */
	public String insertUsuario() {		
		boolean error = false;
		
		// Testa se o login foi digitado
		if (usuario.getLogin().length() == 0) {
			addActionError("Login não pode ficar em branco");
			error = true;
		}
		
		// Verifica se as duas senhas conferem
		if (! usuario.getPassword().equals(confirmacao)) {
			addActionError("Senhas não conferem");
			error = true;
		}
		
		// Verifica se o login já existe
		if (dao.selectByLogin(usuario.getLogin()) != null) {
			addActionError("Login já existente");
			error = true;
		}

		//Faz a criptografia MD5 da senha do usuario
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( usuario.getPassword().getBytes() );  
			BigInteger hash = new BigInteger( 1, md.digest() );  
			usuario.setPassword(hash.toString( 16 ));
		} catch (NoSuchAlgorithmException e) {
			addActionError("Problemas com a senha. Tente novamente.");
			error=true;
			e.printStackTrace();
		}  
		
		if (error)
			return "insertError";
		
		dao.save(usuario);
		listUsuarios();
		return "listUsuarios";
	}

	/**
	 *  Recebe usuario do banco
	 *  Redireciona para a página de edição do usuário
	 * @return "editMinicurso" para o struts
	 */
	public String editUsuario() {
		usuario = dao.selectByLogin(usuario.getLogin());
		return "editUsuario";
	}
	
	/**
	 * Altera os dados do usuario
	 * @return "listUsuarios" para o struts
	 */
	public String updateUsuario() {
		boolean error = false;
		
		// Verifica se as duas senhas conferem
		if (! usuario.getPassword().equals(confirmacao)) {
			addActionError("Senhas não conferem");
			error = true;
		}

		//Faz a criptografia MD5 da senha do usuario
		try {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( usuario.getPassword().getBytes() );  
			BigInteger hash = new BigInteger( 1, md.digest() );  
			usuario.setPassword(hash.toString( 16 ));
		} catch (NoSuchAlgorithmException e) {
			addActionError("Problemas com a senha. Tente novamente.");
			error=true;
			e.printStackTrace();
		}
		
		if (error)
			return "updateError";
		
		dao.save(usuario);
		
		listUsuarios();

		return "listUsuarios";
	}
	
	/**
	 * Exclui usuario do banco
	 * @return "listUsuarios" para o struts
	 */
	public String deleteUsuario() {
		
		//Verifica se usuario logado esta se deletando
		HttpServletRequest request;
		HttpSession session;
		request = ServletActionContext.getRequest();
		session = request.getSession();
		String tmpLogin = (String) session.getAttribute("login");
		
		//Se ele se apagou, entao invalida a secao
		if (usuario.getLogin().equals(tmpLogin))
			session.invalidate();
		
		dao.remove(usuario);
		listUsuarios();

		return "listUsuarios";
	}
	
	
	/*
	 * ==================
	 * Getters e Setters
	 * ==================
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}
	
}
