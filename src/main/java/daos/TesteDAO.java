package daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Teste;

public class TesteDAO {

	private DAO<Teste, Integer> dao;

	public List<Teste> selectAll() {
		return dao.list();
	}

	public List<Teste> selectByDescricao(String descricao) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("descricao", "%" + descricao + "%");

		List<Teste> result = dao.listByNamedQuery("Teste.selectByDescricao", params);
		return result;
	}

	public void remove(Teste teste) {
		dao.remove(teste);
	}

	public Teste save(Teste teste) {
		return dao.merge(teste);
	}

	public Teste selectByCodigo(int codigo) {
		return dao.find(codigo);
	}

	public DAO<Teste, Integer> getDao() {
		return dao;
	}

	public void setDao(DAO<Teste, Integer> dao) {
		this.dao = dao;
	}

}
