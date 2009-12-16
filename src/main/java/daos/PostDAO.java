package daos;

import java.util.List;
import beans.Post;

/**
 * 
 * @author tjcampos
 *
 */
public class PostDAO {

	//dao
	private DAO<Post, Integer> dao;

	/**
	 * Seleciona todos os Posts
	 * @return Lista de Posts
	 */
	public List<Post> selectAll() {
		return dao.listByNamedQuery("post.lastPosts", null);
	}
	
	/**
	 * Salva o post
	 * @param post
	 * @return Post
	 */
	public Post save(Post post){
		return dao.merge(post);	
	}
	
	/**
	 * Remove o post
	 * @param post
	 */
	public void remove (Post post) {
		dao.remove(post);
	}
	
	/**
	 * Seleciona o post pelo id
	 * @param id
	 * @return Post
	 */
	public Post selectById(int id) {
		return dao.find(id);
	}
	
	/**
	 * Pega o dao
	 * @return DAO
	 */
	public DAO<Post , Integer> getDao() {
		return dao;
	}

	/**
	 * Seta o dao
	 * @param dao
	 */
	public void setDao(DAO<Post, Integer> dao) {
		this.dao = dao;
	}

}

