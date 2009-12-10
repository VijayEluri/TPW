package daos;

import java.util.List;

import beans.Minicurso;

public class MinicursoDAO {

	private DAO<Minicurso, Integer> dao;

	public List<Minicurso> selectAll() {
		return dao.list();
	}

	public Minicurso save(Minicurso evento) {
		if (evento.getId() != null)
			return dao.merge(evento);
		dao.persist(evento);
		return evento;
	}
	
	public void remove(Minicurso evento) {
		dao.remove(evento);
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
