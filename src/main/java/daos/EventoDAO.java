package daos;

import java.util.List;

import beans.Evento;
import beans.Usuario;

public class EventoDAO {

	private DAO<Evento, Long> dao;

	public List<Evento> selectAll() {
		return dao.list();
	}

	public Evento save(Evento evento) {
		if (evento.getId() != null)
			return dao.merge(evento);
		dao.merge(evento);
		return evento;
	}
	
	public void remove(Evento evento) {
		dao.remove(evento);
	}
	
	public Evento selectById(Long id) {
		return dao.find(id);
	}
	
	public DAO<Evento, Long> getDao() {
		return dao;
	}

	public void setDao(DAO<Evento, Long> dao) {
		this.dao = dao;
	}

}
