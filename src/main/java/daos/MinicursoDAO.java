package daos;

import java.util.List;

import beans.Minicurso;

public class MinicursoDAO {

	private DAO<Minicurso, Integer> dao;

	public List<Minicurso> selectAll() {
		return dao.listByNamedQuery("minicurso.all", null);
	}

	public List<Minicurso> selectLast() {
		return dao.listByNamedQuery("minicurso.last", null);
	}

	public Minicurso save(Minicurso minicurso) {
		if (minicurso.getId() != null)
			return dao.merge(minicurso);
		dao.persist(minicurso);
		return minicurso;
	}
	
	public void remove(Minicurso minicurso) {
		dao.remove(minicurso);
	}
	
	public Minicurso selectById(Integer id) {
		return dao.find(id);
	}
	
	public DAO<Minicurso, Integer> getDao() {
		return dao;
	}

	public void setDao(DAO<Minicurso, Integer> dao) {
		this.dao = dao;
	}

}
