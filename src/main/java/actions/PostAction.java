package actions;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Post;

import com.opensymphony.xwork2.ActionSupport;

import daos.PostDAO;

public class PostAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Post post;
	private List<Post> posts;
	ClassPathXmlApplicationContext ctx;
	private PostDAO dao;
	
	public PostAction() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		dao = (PostDAO) ctx.getBean("postDAO");
		post = post == null ? new Post() : post;
	}
	
	public String listPosts() {
		posts = dao.selectAll();
		return "listPosts";
	}
	
	public String insertPost() {
		boolean error = false;
		
		// Testa se o login foi digitado
		if (post.getUsuario() == null) {
			addActionError("Erro ao identificar usuário");
			error = true;
		}
		
		if (error) return "insertError";
		
		dao.save(post);
		posts = dao.selectAll();
		
		return "listPosts";
	}
	
	public String editPost(){
		post = dao.selectById(post.getId());
		return "editPost";
	}
	
	public String updatePost() {
		boolean error = false;

		if (post.getId() == null) {
			addActionError("Erro ao identificar post");
			error = true;
		}
		
		if (post.getUsuario() == null) {
			addActionError("Erro ao identificar usuário");
			error = true;
		}
		
		dao.save(post);
		posts = dao.selectAll();
		
		if (error) return "insertError";
		return "listPosts";
	}
	
	public String deletePost() {
		dao.remove(post);
		posts = dao.selectAll();

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
