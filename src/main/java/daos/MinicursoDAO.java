package daos;

import java.util.List;

import beans.Minicurso;

public class MinicursoDAO {

	private DAO<Minicurso, Long> dao;

	public List<Minicurso> selectAll() {
		return dao.list();
	}

	public Minicurso save(Minicurso evento) {
		if (evento.getId() != null)
			return dao.merge(evento);
		dao.merge(evento);
		return evento;
	}
	
	public void remove(Minicurso evento) {
		dao.remove(evento);
	}
	
	public Minicurso selectById(Long id) {
		return dao.find(id);
	}
	
	public DAO<Minicurso, Long> getDao() {
		return dao;
	}

	public void setDao(DAO<Minicurso, Long> dao) {
		this.dao = dao;
	}

}
