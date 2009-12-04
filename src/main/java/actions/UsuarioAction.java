package actions;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.UsuarioDAO;

public class UsuarioAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;

	private UsuarioDAO dao;

	private ApplicationContext ctx;

	// Confirmação da password
	private String confirmacao;
	
	public UsuarioAction() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		dao = (UsuarioDAO) ctx.getBean("usuarioDAO");
		usuario = usuario == null ? new Usuario() : usuario;		
	}

	/**
	 * Retorna a string "listUsuarios" que será lida pelo Struts. O Struts abre
	 * o arquivo struts.xml e vê qual é o result que está sendo enviado (no caso
	 * a listUsuarios) e redireciona para a página listUsuarios.jsp
	 */
	public String listUsuarios() {
		usuarios = dao.selectAll();
		return "listUsuarios";
	}

	
	/**
	 * Insere o usuário no banco de dados. Esse método é chamado pelo form
	 * da página insertUsuario.jsp
	 * @return
	 */
	public String insertUsuario() {
		
		boolean error = false;
		
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

		if (error)
			return "insertError";
		
		dao.save(usuario);
		usuarios = dao.selectAll();
		return "listUsuarios";
	}
	
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
