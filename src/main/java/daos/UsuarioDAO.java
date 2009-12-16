package daos;

import java.util.List;

import beans.Usuario;

/**
 * Dao do usuario
 * @author stapait
 *
 */
public class UsuarioDAO {

	//dao
	private DAO<Usuario, String> dao;

	/**
	 * Lista todos os usuarios
	 * @return Lista de Usuarios
	 */
	public List<Usuario> selectAll() {
		return dao.list();
	}

	/**
	 * Salva o usuario
	 * @param usuario
	 * @return Usuario
	 */
	public Usuario save(Usuario usuario) {
		if (usuario.getLogin() != null)
			return dao.merge(usuario);
		dao.persist(usuario);
		return usuario;
	}
	
	/**
	 * Remove o usuario
	 * @param usuario
	 */
	public void remove(Usuario usuario) {
		dao.remove(usuario);
	}
	
	/**
	 * Seleciona o usuario pelo login
	 * @param login
	 * @return Usuario
	 */
	public Usuario selectByLogin(String login) {
		return dao.find(login);
	}
	
	/**
	 * Pega o dao
	 * @return DAO
	 */
	public DAO<Usuario, String> getDao() {
		return dao;
	}

	/**
	 * Seta o dao
	 * @param dao
	 */
	public void setDao(DAO<Usuario, String> dao) {
		this.dao = dao;
	}

}
