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

import daos.TesteDAO;

public class TesteTest {

	private static ApplicationContext ctx;
	private static TesteDAO testeDAO;

	Teste teste1, teste2;
	
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		testeDAO = (TesteDAO) ctx.getBean("testeDAO");
	}

	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("ALTER SEQUENCE seq_teste RESTART WITH 1").executeUpdate();
		em.createNativeQuery("DELETE FROM teste").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void runTests() {
		
		// Testa o save
		save();
		
		// Testa o update
		update();
		
		// Remove o teste gravado anteriormente
		delete();
				
		
	}
	
	private void save() {		
		teste1 = (Teste) ctx.getBean("teste1");		
		assertNotNull(teste1);
		
		// Salva no banco
		teste1 = testeDAO.save(teste1);		
		
		// Verifica os dados retornados
		assertNotNull(teste1);
		assertEquals(1, teste1.getCodigo());
		assertEquals("descricao 1", teste1.getDescricao());
		assertEquals(10000.0, teste1.getValor());

		// Busca no banco o teste gravado
		teste2 = testeDAO.selectByCodigo(1);
		
		// Compara os dois
		assertNotNull(teste2);
		assertEquals(teste1.getCodigo(), teste2.getCodigo());
		assertEquals(teste1.getDescricao(), teste2.getDescricao());
		assertEquals(teste1.getValor(), teste2.getValor());		
	}

	private void update() {
	
		// Altera os dados do teste
		teste1.setDescricao("descricao alterada");
		teste1.setValor(50000.0);
		
		// Salva
		teste1 = testeDAO.save(teste1);
		assertNotNull(teste1);
		
		// Busca no banco (o código é 1)
		teste1 = testeDAO.selectByCodigo(1);
		
		// Verifica se os dados foram alterados
		assertEquals(1, teste1.getCodigo());
		assertEquals("descricao alterada", teste1.getDescricao());
		assertEquals(50000.0, teste1.getValor());
	}
	
	private void delete() {
	
		// Remove o teste1
		testeDAO.remove(teste1);
		
		// Busca o teste1 no banco
		teste1 = testeDAO.selectByCodigo(1);
		
		// Deve retornar null
		assertNull(teste1);
	}
	
}
