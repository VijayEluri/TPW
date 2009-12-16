package noticias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import daos.SiteRSSDAO;

/**
 * Teste do Post
 * @author stapait
 */
public class SiteRSSTest {

	private static ApplicationContext ctx;
	private static SiteRSSDAO dao;

	//RSS usado para teste
	SiteRSS rss1, rss2;

	/*
	 * Prepara acesso ao DAO
	 */
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml", "testContext.xml" });
		dao = (SiteRSSDAO) ctx.getBean("siteRSSDAO");
	}

	/*
	 * Metodo chamado ao final dos testes, limpa a tabela e zera a sequence 
	 */
	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw")
				.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM sites_rss").executeUpdate();
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
		rss1 = (SiteRSS) ctx.getBean("rss1");
		assertNotNull(rss1);

		// Salva no banco
		rss1 = dao.save(rss1);

		// Verifica os dados retornados
		assertNotNull(rss1);
		assertEquals("http://rss.terra.com.br/0,,EI1,00.xml", rss1.getLink());
		assertEquals("Terra", rss1.getSite());

		// Busca no banco o rss gravado
		rss2 = dao.selectByLink(rss1.getLink());

		// Compara os dois
		assertNotNull(rss2);
		assertEquals(rss1.getLink(), rss2.getLink());
		assertEquals(rss1.getSite(), rss2.getSite());
		
		rss2 = (SiteRSS) ctx.getBean("rss2");
		dao.save(rss2);
		List<SiteRSS> sites = dao.selectAll();
		assertEquals(2, sites.size());
	}

	/*
	 * Metodo que testa modificacoes
	 */
	private void update() {
	
		// Altera os dados do rss
		rss1.setLink("link alterado");
		rss1.setSite("site alterado");
		
		// Salva
		rss1 = dao.save(rss1);
		assertNotNull(rss1);
		
		// Busca no banco (o código é 1)
		rss1 = dao.selectByLink("link alterado");
		
		// Verifica se os dados foram alterados
		assertEquals("link alterado", rss1.getLink());
		assertEquals("site alterado", rss1.getSite());
	}

	/*
	 * Metodo que testa exclusao
	 */
	private void delete() {

		// Remove o rss1
		dao.remove(rss1);

		// Busca o rss1 no banco
		rss1 = dao.selectByLink("link alterado");

		// Deve retornar null
		assertNull(rss1);
	}

}
