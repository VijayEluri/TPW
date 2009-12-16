package daos;

import java.util.List;

import beans.Evento;

/**
 * DAO do Evento
 * @author stapait
 *
 */
public class EventoDAO {

	//Dao
	private DAO<Evento, Integer> dao;

	/**
	 * Seleciona todos os eventos
	 * @return Lista de Evento
	 */
	public List<Evento> selectAll() {
		return dao.listByNamedQuery("evento.all", null);
	}

	/**
	 * Seleciona os ultimos eventos
	 * @return Lista de Evento
	 */
	public List<Evento> selectLast() {
		return dao.listByNamedQuery("evento.last", null);
	}
	
	/**
	 * Salva o evento
	 * @param evento
	 * @return Evento
	 */
	public Evento save(Evento evento) {
		if (evento.getId() != null)
			return dao.merge(evento);
		dao.persist(evento);
		return evento;
	}
	
	/**
	 * Remove o evento
	 * @param evento
	 */
	public void remove(Evento evento) {
		dao.remove(evento);
	}
	
	/**
	 * Seleciona o evento pelo id
	 * @param id
	 * @return Evento
	 */
	public Evento selectById(Integer id) {
		return dao.find(id);
	}
	
	/**
	 * Pega o dao
	 * @return DAO
	 */
	public DAO<Evento, Integer> getDao() {
		return dao;
	}

	/**
	 * Seta o dao
	 * @param dao
	 */
	public void setDao(DAO<Evento, Integer> dao) {
		this.dao = dao;
	}

}
