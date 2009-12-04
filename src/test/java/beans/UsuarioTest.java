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

import daos.UsuarioDAO;

public class UsuarioTest {

	private static ApplicationContext ctx;
	private static UsuarioDAO dao;

	Usuario usuario1, usuario2;
	
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		dao = (UsuarioDAO) ctx.getBean("usuarioDAO");
	}

	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM usuario").executeUpdate();
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
		usuario1 = (Usuario) ctx.getBean("usuario1");		
		assertNotNull(usuario1);
		
		// Salva no banco
		usuario1 = dao.save(usuario1);		
		
		// Verifica os dados retornados
		assertNotNull(usuario1);
		assertEquals("login1", usuario1.getLogin());
		assertEquals("password1", usuario1.getPassword());
		assertEquals("nome 1", usuario1.getNome());
		assertEquals("email@email.com", usuario1.getEmail());
		assertEquals(TipoUsuario.ADMINISTRADOR, usuario1.getTipoUsuario());

		// Busca no banco o usuário gravado
		usuario2 = dao.selectByLogin("login1");
		
		// Compara os dois
		assertNotNull(usuario2);
		assertEquals(usuario1.getLogin(), usuario2.getLogin());
		assertEquals(usuario1.getPassword(), usuario2.getPassword());
		assertEquals(usuario1.getNome(), usuario2.getNome());
		assertEquals(usuario1.getEmail(), usuario2.getEmail());
		assertEquals(usuario1.getTipoUsuario(), usuario2.getTipoUsuario());				
	}

	private void update() {
	
		// Altera os dados do usuário
		usuario1.setNome("nome alterado");
		usuario1.setEmail("xxx@xxx.com");
		usuario1.setPassword("*****");
		usuario1.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		
		// Salva
		usuario1 = dao.save(usuario1);
		assertNotNull(usuario1);
		
		// Busca no banco
		usuario1 = dao.selectByLogin("login1");
		
		// Verifica se os dados foram alterados
		assertEquals("nome alterado", usuario1.getNome());
		assertEquals("xxx@xxx.com", usuario1.getEmail());
		assertEquals("*****", usuario1.getPassword());
		assertEquals(TipoUsuario.ADMINISTRADOR, usuario1.getTipoUsuario());
	}
	
	private void delete() {
	
		// Remove o usuario
		dao.remove(usuario1);
		
		// Busca o usuario1 no banco
		usuario1 = dao.selectByLogin("login1");
		
		// Deve retornar null
		assertNull(usuario1);
	}
	
}
