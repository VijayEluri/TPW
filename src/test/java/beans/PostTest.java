package beans;

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

import daos.PostDAO;
import daos.UsuarioDAO;

public class PostTest {
	private static ApplicationContext ctx;
	private static PostDAO daoPost;
	private static UsuarioDAO daoUsuario;
	
	
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		daoPost = (PostDAO) ctx.getBean("postDAO");
	}

	@AfterClass
	public static void clearDatabase() {
		EntityManager em = Persistence.createEntityManagerFactory("tpw").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM post").executeUpdate();
		em.createNativeQuery("DELETE FROM usuario").executeUpdate();
		em.createNativeQuery("ALTER SEQUENCE seq_post_id RESTART WITH 1").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	private Post post1, post2;
	private Usuario usuario;
	
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
		post1 = (Post) ctx.getBean("post1");
		assertNotNull(post1);

		
		// Salva no banco
		post1 = daoPost.save(post1);
		usuario = post1.getUsuario();
		assertNotNull(usuario);
	
		// Verifica os dados retornados
		assertNotNull(post1);
		assertEquals("titulo teste", post1.getTitulo());
		assertEquals("Texto de exemplo", post1.getTexto());
		assertEquals("login1", usuario.getLogin());
		assertEquals(1, post1.getId());

		// Busca no banco o usuário gravado
		post2 = usuario.getPosts().get(0);
		
		// Compara os dois
		assertNotNull(post2);
		assertEquals(post1.getId(), post2.getId());
		assertEquals(post1.getTexto(), post2.getTexto());
		assertEquals(post1.getUsuario().getLogin(), post2.getUsuario().getLogin());			
	}

	private void update() {
		post1.setTitulo("Novo Titulo");
		post1.setTexto("Novo texto de teste");
		
		//Salva
		post1 = daoPost.save(post1);
		assertNotNull(post1);
		
		//Busca no Banco
		post1 = daoPost.selectById(1);
		
		// Verifica se os dados foram alterados
		assertEquals("Novo Titulo", post1.getTitulo());
		assertEquals("Novo texto de teste", post1.getTexto());
	}
	
	private void delete() {
		// Remove o usuario
		daoPost.remove(post1);
		
		// Busca o usuario1 no banco
		post1 = daoPost.selectById(1);
		
		// Deve retornar null
		assertNull(post1);
	}
	
}