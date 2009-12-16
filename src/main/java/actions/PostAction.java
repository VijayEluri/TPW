package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.Seguranca;
import beans.Post;
import beans.Usuario;

import com.opensymphony.xwork2.ActionSupport;

import daos.PostDAO;
import daos.UsuarioDAO;

/**
 * Classe responsável por cuidar das acoes dos Posts (Blog).
 * Geralmente chamadas pelo struts. 
 * @author tjcampos
 *
 */
public class PostAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Post da acao
	 */
	private Post post;
	
	/**
	 * Lista de posts
	 */
	private List<Post> posts;
	
	//Spring
	ClassPathXmlApplicationContext ctx;
	
	//Daos
	private PostDAO postDao;
	private UsuarioDAO usuarioDao;
	
	//Sessao
	private HttpServletRequest request;
	private HttpSession session;
	
	/**
	 * Construtor: Inicializa o post, dao e ctx
	 */
	public PostAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		postDao = (PostDAO) ctx.getBean("postDAO");
		usuarioDao = (UsuarioDAO) ctx.getBean("usuarioDAO");
		post = post == null ? new Post() : post;
	}
	
	/**
	 * Prepara a lista de posts com os posts do banco
	 * @return "listPosts" para o struts
	 */
	public String listPosts() {
		posts = postDao.selectAll();
		return "listPosts";
	}
	
	/**
	 * Insere um post no banco
	 * @return "listPosts" para o struts
	 */
	public String insertPost() {
		if (!Seguranca.checkAdministrador(this)) return "insertError";
		
		//Recupera o usuário
		Usuario usuario = Seguranca.getUsuario();

		//Seta o usuario que postou o Blog
		post.setUsuario(usuario);
		
		//Salva no banco
		postDao.save(post);
		posts = postDao.selectAll();
		
		return "listPosts";
	}
	
	/**
	 * Recebe Post do banco
	 * Redireciona para a página de edição do post
	 * @return "editMinicurso" para o struts
	 */
	public String editPost(){
		post = postDao.selectById(post.getId());
		return "editPost";
	}
	
	/**
	 * Altera os dados do post
	 * @return "listPosts" para o struts
	 */
	public String updatePost() {
		if (!Seguranca.checkAdministrador(this)) return "updateError";

		//Recupera o usuario
		Usuario usuario = Seguranca.getUsuario();

		//Seta o usuario que fez o post
		post.setUsuario(usuario);
		
		//Salva os dados no banco
		postDao.save(post);
		posts = postDao.selectAll();
		
		return "listPosts";
	}
	
	/**
	 * Remove os dados do post
	 * @return "listPosts" para o struts
	 */
	public String deletePost() {
		if (!Seguranca.checkAdministrador(this)) return "deleteError";
		
		//Executa a remocao no banco
		postDao.remove(post);
		posts = postDao.selectAll();
		return "listPosts";
	}

	/*
	 * =================
	 * Setters e Getters
	 * =================
	 */
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