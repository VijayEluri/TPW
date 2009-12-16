package daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * Dao genérico
 * 
 * @author stapait
 *
 * @param <PersistentObject> Objeto a ser persistido
 * @param <IDType> Tipo da chave primaria
 */
public class GenericJPADAO<PersistentObject, IDType extends Serializable>
		implements DAO<PersistentObject, IDType> {

	//Nome da unidade
	private static final String PERSISTENCE_UNIT = "tpw";

	//Entity Manager Factory
	private Class<PersistentObject> persistentClass;
	private EntityManager em;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

	//Construtor
	public GenericJPADAO(Class<PersistentObject> clazz) {
		persistentClass = clazz;
	}

	//Retorna uma entidade para utilizacao
	protected EntityManager getEntityManager() {
		if ((emf == null) || (! emf.isOpen()))
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		
		return emf.createEntityManager();
	}

	/**
	 * Fecha conexao
	 */
	public void close() {
		if (em != null && em.isOpen())
			em.close();
	}

	/**
	 * Encontra por ID
	 * @param id
	 * @return Objeto
	 */
	public PersistentObject find(IDType id) {
		em = getEntityManager();
		return em.find(persistentClass, id);
	}
	
	/**
	 * Executa a query "queryName"
	 * @param queryName
	 * @param params (Map<String, Object>)
	 * @return Objeto
	 */
	public PersistentObject getByNamedQuery(String queryName, Map<String, Object> params) {
		
		//Pega uma entidade
		em = getEntityManager();
		Query query = em.createNamedQuery(queryName);
		
		//Seta os parametros para a query
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				query.setParameter(key, params.get(key));
		}

		//Resultado - Executa a query
		PersistentObject result;
		try {
			result = (PersistentObject) query.getSingleResult(); 
		} catch (NoResultException nre){
			try {
				return persistentClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException("Error in named query: " + queryName + " - InstantiationException");
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Error in named query: " + queryName + " - IllegalAccessException");
			}
		}
		
		return result;
	}

	/**
	 * Faz selecao de todos os dados
	 * @return Lista de Objetos
	 */
	public List<PersistentObject> list() {
		em = getEntityManager();
		
		//Select all
		Query query = em.createQuery("SELECT obj FROM " + persistentClass.getSimpleName() + " obj");
		List<PersistentObject> list = query.getResultList();
		return list;
	}

	/**
	 * Faz a selecao dos dados retornados pela "queryName"
	 * @param queryName
	 * @param params (Map<String, Object>)
	 * @return List<PersistentObject> Lista dos resultados
	 */
	public List<PersistentObject> listByNamedQuery(String queryName, Map<String, Object> params) {		
		em = getEntityManager();
		
		//Cria query
		Query query = em.createNamedQuery(queryName);
		
		//Seta os parametros
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				query.setParameter(key, params.get(key));
		}
		
		//Executa
		List<PersistentObject> list = query.getResultList();
		
		return list;
	}

	/**
	 * Metodo para fazer merge (grava) no banco
	 * @param Objeto a ser persistido
	 * @return Obeto persistido
	 */
	public PersistentObject merge(PersistentObject obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//Tenta exectar o merge e o commit
		try {
			tx.begin();
			obj = em.merge(obj);
			tx.commit();
			
		//Em caso de problemas faz o rollback
		} catch (PersistenceException pe) {
			if (tx.isActive())
				tx.rollback();
			pe.printStackTrace();
		}
		return obj;
	}

	/**
	 * Metodo para fazer o persist (grava) no banco
	 * @param Objeto a ser persistido
	 * @return Objeto persistido
	 */
	public PersistentObject persist(PersistentObject obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//Tenta executar o persist e o commit
		try {
			tx.begin();
			em.persist(obj);
			tx.commit();
			em.refresh(obj);
			
		//Em caso de problemas
		} catch (PersistenceException pe) {
			if (tx.isActive())
				tx.rollback();
			pe.printStackTrace();
		}
		return obj;
	}

	/**
	 * Remover um objeto
	 * @param Objeto a ser removido
	 */
	public void remove(PersistentObject obj) {
		em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		//Tenta remover o objeto
		try {
			tx.begin();
			obj = em.merge(obj);
			em.remove(obj);
			tx.commit();
			
		//Em caso de erro faz o RollBack
		} catch (RuntimeException re) {
			tx.rollback();
			re.printStackTrace();
			throw re;
		}
	}

}
