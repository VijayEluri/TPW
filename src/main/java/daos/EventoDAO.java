package daos;

import java.util.List;

import beans.Evento;
import beans.Usuario;

public class EventoDAO {

	private DAO<Evento, Integer> dao;

	public List<Evento> selectAll() {
		return dao.listByNamedQuery("evento.all", null);
	}

	public Evento save(Evento evento) {
		if (evento.getId() != null)
			return dao.merge(evento);
		dao.persist(evento);
		return evento;
	}
	
	public void remove(Evento evento) {
		dao.remove(evento);
	}
	
	public Evento selectById(Integer id) {
		return dao.find(id);
	}
	
	public DAO<Evento, Integer> getDao() {
		return dao;
	}

	public void setDao(DAO<Evento, Integer> dao) {
		this.dao = dao;
	}

}
