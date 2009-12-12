package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Post;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.PostDAO;
import daos.UsuarioDAO;

public class PostAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Post post;
	private List<Post> posts;
	ClassPathXmlApplicationContext ctx;
	private PostDAO postDao;
	private UsuarioDAO usuarioDao;
	
	private HttpServletRequest request;
	private HttpSession session;
	
	public PostAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		postDao = (PostDAO) ctx.getBean("postDAO");
		usuarioDao = (UsuarioDAO) ctx.getBean("usuarioDAO");
		post = post == null ? new Post() : post;
	}
	
	public String listPosts() {
		posts = postDao.selectAll();
		return "listPosts";
	}
	
	public String insertPost() {
		Usuario usuario = null;
		boolean error = false;

		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		// Pega o login do usuário na sessão
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
		
		if (error) return "insertError";

		post.setUsuario(usuario);
		
		postDao.save(post);
		posts = postDao.selectAll();
		
		return "listPosts";
	}
	
	public String editPost(){
		post = postDao.selectById(post.getId());
		return "editPost";
	}
	
	public String updatePost() {
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
		
		if (error) return "updateError";

		post.setUsuario(usuario);
		postDao.save(post);
		posts = postDao.selectAll();
		
		return "listPosts";
	}
	
	public String deletePost() {
		
		boolean error = false;

		request = ServletActionContext.getRequest();
		session = request.getSession();

		String login = (String) session.getAttribute("login");

		if (login == null) {
			addActionError("Você deve estar logado");
			error = true;
		}
		else {
			
			Usuario usuario = (Usuario) usuarioDao.selectByLogin(login);
			
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
		
		if (error) return "deleteError";
		
		postDao.remove(post);
		posts = postDao.selectAll();
		return "listPosts";
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
