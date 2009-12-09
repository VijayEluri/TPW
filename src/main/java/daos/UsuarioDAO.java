package daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Usuario;

public class UsuarioDAO {

	private DAO<Usuario, String> dao;

	public List<Usuario> selectAll() {
		return dao.list();
	}

	public Usuario save(Usuario usuario) {
		if (usuario.getLogin() != null)
			return dao.merge(usuario);
		dao.persist(usuario);
		return usuario;
	}
	
	public void remove(Usuario usuario) {
		dao.remove(usuario);
	}
	
	public Usuario selectByLogin(String login) {
		return dao.find(login);
	}
	
	public DAO<Usuario, String> getDao() {
		return dao;
	}

	public void setDao(DAO<Usuario, String> dao) {
		this.dao = dao;
	}

}
