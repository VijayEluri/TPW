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

import daos.MinicursoDAO;

public class MinicursoTest {

	private static ApplicationContext ctx;
	private static MinicursoDAO dao;

	Minicurso minicurso1, minicurso2;
	
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		dao = (MinicursoDAO) ctx.getBean("minicursoDAO");
	}

	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM minicurso").executeUpdate();
		em.createNativeQuery("ALTER SEQUENCE minicurso_id_seq RESTART WITH 1").executeUpdate();
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
		minicurso1 = (Minicurso) ctx.getBean("minicurso1");		
		assertNotNull(minicurso1);
		
		// Salva no banco
		minicurso1 = dao.save(minicurso1);
		
		// Verifica os dados retornados
		assertNotNull(minicurso1);
		assertEquals(1, minicurso1.getId());
		//assertEquals("20/12/2009", minicurso1.getData());
		assertEquals("descricao 1", minicurso1.getDescricao());
		assertEquals("nome 1", minicurso1.getNome());
		assertEquals("responsavel 1", minicurso1.getResponsavel());

		// Busca no banco o usuário gravado
		minicurso2 = dao.selectById(1);
		
		// Compara os dois
		assertNotNull(minicurso2);
		assertEquals(minicurso1.getId(), minicurso2.getId());
		assertEquals(minicurso1.getData(), minicurso2.getData());
		assertEquals(minicurso1.getDescricao(), minicurso2.getDescricao());
		assertEquals(minicurso1.getNome(), minicurso2.getNome());
		assertEquals(minicurso1.getResponsavel(), minicurso2.getResponsavel());				
	}

	private void update() {
	
		// Altera os dados
		minicurso1.setDescricao("descricao alterada");
		minicurso1.setNome("nome alterado");
		minicurso1.setResponsavel("responsavel alterado");
		
		// Salva
		minicurso1 = dao.save(minicurso1);
		assertNotNull(minicurso1);
		
		// Busca no banco
		minicurso1 = dao.selectById(1);
		
		// Verifica se os dados foram alterados
		assertEquals("nome alterado", minicurso1.getNome());
		assertEquals("nome alterado", minicurso1.getNome());
		assertEquals("responsavel alterado", minicurso1.getResponsavel());
	}
	
	private void delete() {
	
		// Remove o usuario
		dao.remove(minicurso1);
		
		// Busca o usuario1 no banco
		minicurso1 = dao.selectById(1);
		
		// Deve retornar null
		assertNull(minicurso1);
	}
	
}
