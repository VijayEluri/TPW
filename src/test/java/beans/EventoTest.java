package beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import daos.EventoDAO;

/**
 * Teste do Evento
 * @author vendra
 *
 */
public class EventoTest {

	private static ApplicationContext ctx;
	private static EventoDAO dao;

	Evento evento1, evento2;
	
	/*
	 * Prepara acesso ao DAO
	 */
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		dao = (EventoDAO) ctx.getBean("eventoDAO");
	}

	/*
	 * Metodo chamado ao final dos testes, limpa a tabela e zera a sequence 
	 */
	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM evento").executeUpdate();
		em.createNativeQuery("ALTER SEQUENCE evento_id_seq RESTART WITH 1").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 * Metodo contendo os testes
	 */
	@Test
	public void runTests() {
		
		// Testa o save
		save();
		
		// Testa o update
		update();
		
		// Remove o teste gravado anteriormente
		delete();
				
		
	}
	
	/*
	 * Metodo que testa ao salvar no banco
	 */
	private void save() {		
		evento1 = (Evento) ctx.getBean("evento1");		
		assertNotNull(evento1);
		
		// Salva no banco
		evento1 = dao.save(evento1);
		
		// Verifica os dados retornados
		assertNotNull(evento1);
		assertEquals(1, evento1.getId());
		//assertEquals("20/12/2009", evento1.getData());
		assertEquals("descricao 1", evento1.getDescricao());
		assertEquals("nome 1", evento1.getNome());
		assertEquals("responsavel 1", evento1.getResponsavel());

		// Busca no banco o usuário gravado
		evento2 = dao.selectById(1);
		
		// Compara os dois
		assertNotNull(evento2);
		assertEquals(evento1.getId(), evento2.getId());
		assertEquals(evento1.getData(), evento2.getData());
		assertEquals(evento1.getDescricao(), evento2.getDescricao());
		assertEquals(evento1.getNome(), evento2.getNome());
		assertEquals(evento1.getResponsavel(), evento2.getResponsavel());				
	}

	/*
	 * Metodo que testa modificacoes
	 */
	private void update() {
	
		// Altera os dados
		evento1.setDescricao("descricao alterada");
		evento1.setNome("nome alterado");
		evento1.setResponsavel("responsavel alterado");
		
		// Salva
		evento1 = dao.save(evento1);
		assertNotNull(evento1);
		
		// Busca no banco
		evento1 = dao.selectById(1);
		
		// Verifica se os dados foram alterados
		assertEquals("nome alterado", evento1.getNome());
		assertEquals("nome alterado", evento1.getNome());
		assertEquals("responsavel alterado", evento1.getResponsavel());
	}
	
	/*
	 * Metodo que testa exclusao
	 */
	private void delete() {
	
		// Remove o usuario
		dao.remove(evento1);
		
		// Busca o usuario1 no banco
		evento1 = dao.selectById(1);
		
		// Deve retornar null
		assertNull(evento1);
	}
	
}
