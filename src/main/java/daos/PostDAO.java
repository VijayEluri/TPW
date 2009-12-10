package daos;

import java.util.List;
import beans.Post;

public class PostDAO {
	private DAO<Post, Integer> dao;

	public List<Post> selectAll() {
		return dao.list();
	}
	
	public Post save(Post post){
		if (post.getId() != null)
			return dao.merge(post);
		dao.persist(post);
		return post;
	}
	
	public void remove (Post post) {
		dao.remove(post);
	}
	
	public Post selectById(int id) {
		return dao.find(id);
	}
	
	public DAO<Post , Integer> getDao() {
		return dao;
	}

	public void setDao(DAO<Post, Integer> dao) {
		this.dao = dao;
	}

}

