package daos;

import java.util.List;

import beans.Minicurso;

/**
 * DAO do Minicurso
 * @author stapait
 *
 */
public class MinicursoDAO {

	//dao
	private DAO<Minicurso, Integer> dao;

	/**
	 * Seleciona todos os minicursos
	 * @return Lista de Minicurso
	 */
	public List<Minicurso> selectAll() {
		return dao.listByNamedQuery("minicurso.all", null);
	}

	/**
	 * Seleciona os ultimos minicursos
	 * @return Lista de Minicurso
	 */
	public List<Minicurso> selectLast() {
		return dao.listByNamedQuery("minicurso.last", null);
	}

	/**
	 * Salva o minicurso
	 * @param minicurso
	 * @return Minicurso
	 */
	public Minicurso save(Minicurso minicurso) {
		if (minicurso.getId() != null)
			return dao.merge(minicurso);
		dao.persist(minicurso);
		return minicurso;
	}
	
	/**
	 * Remove o minicurso
	 * @param minicurso
	 */
	public void remove(Minicurso minicurso) {
		dao.remove(minicurso);
	}
	
	/**
	 * Seleciona o Minicurso com id desejado
	 * @param id
	 * @return Minicurso
	 */
	public Minicurso selectById(Integer id) {
		return dao.find(id);
	}
	
	/**
	 * Recebe o dao
	 * @return DAO
	 */
	public DAO<Minicurso, Integer> getDao() {
		return dao;
	}

	/**
	 * Seta o dao
	 * @param dao
	 */
	public void setDao(DAO<Minicurso, Integer> dao) {
		this.dao = dao;
	}

}
