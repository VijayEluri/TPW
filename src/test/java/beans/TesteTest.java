package beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import daos.GenericJPADAO;
import daos.TesteDAO;

public class TesteTest {

	private static ApplicationContext ctx;
	private static TesteDAO testeDAO;

	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		testeDAO = (TesteDAO) ctx.getBean("testeDAO");
	}

	//@AfterClass
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
		
		//Testa o save
		save();
		
	}
	
	private static void save() {		
		Teste teste1 = (Teste) ctx.getBean("teste1");
		Teste teste2 = (Teste) ctx.getBean("teste2");
		
		assertNotNull(teste1);
		assertNotNull(teste2);
		
		// Salva no banco
		teste1 = testeDAO.save(teste1);		
		
		// Verifica os dados retornados
		assertNotNull(teste1);
		assertEquals(1, teste1.getCodigo());
		assertEquals("descricao 1", teste1.getDescricao());
		assertEquals(10000.0, teste1.getValor());
				
	}


}
