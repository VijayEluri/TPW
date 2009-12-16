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

import daos.PostDAO;
import daos.UsuarioDAO;

/**
 * Teste do Post
 * @author tjcampos
 */
public class PostTest {
	private static ApplicationContext ctx;
	private static PostDAO daoPost;
	private static UsuarioDAO daoUsuario;
	
	/*
	 * Prepara acesso ao DAO
	 */
	@BeforeClass
	public static void prepare() {
		clearDatabase();
		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "testContext.xml"});
		daoPost = (PostDAO) ctx.getBean("postDAO");
		daoUsuario = (UsuarioDAO) ctx.getBean("usuarioDAO");
	}

	/*
	 * Metodo chamado ao final dos testes, limpa a tabela e zera a sequence 
	 */
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
		post1 = (Post) ctx.getBean("post1");
		assertNotNull(post1);
		
		usuario = (Usuario) ctx.getBean("usuario1");
		assertNotNull(usuario);
		
		// Salva o usuário no banco
		usuario = daoUsuario.save(usuario);
		usuario = daoUsuario.selectByLogin("login1");
		assertNotNull(usuario);
		
		// Associa o post ao usuário
		post1.setUsuario(usuario);

		// Salva no banco o post1
		post1 = daoPost.save(post1);
		assertEquals(1, post1.getId());
		assertNotNull(post1);
		assertEquals("titulo teste", post1.getTitulo());
		assertEquals("Texto de exemplo", post1.getTexto());
		
		// Recupera do banco o post gravado (id 1) e compara com o post1
		post2 = daoPost.selectById(1);
		assertNotNull(post2);
		assertEquals(post1.getId(), post2.getId());
		assertEquals(post1.getTitulo(), post2.getTitulo());
		assertEquals(post1.getTexto(), post2.getTexto());
		
		// Verifica se o usuário do post foi gravado corretamente
		Usuario usuarioPost = post2.getUsuario();
		
		assertNotNull(usuario);
		assertEquals(usuario.getLogin(), usuarioPost.getLogin());
		assertEquals(usuario.getEmail(), usuarioPost.getEmail());
		assertEquals(usuario.getNome(), usuarioPost.getNome());
		assertEquals(usuario.getPassword(), usuarioPost.getPassword());
		assertEquals(usuario.getTipoUsuario(), usuarioPost.getTipoUsuario());
		
		// Associa o post a um usuário jã existente
		post2 = (Post) ctx.getBean("post2");
		usuario = daoUsuario.selectByLogin("login1");
		post2.setUsuario(usuario);
		post2 = daoPost.save(post2);
		
		post2 = daoPost.selectById(2);
		
		assertNotNull(post2);
		assertEquals("titulo teste2", post2.getTitulo());
		assertEquals("Texto de exemplo2", post2.getTexto());
	}

	/*
	 * Metodo que testa modificacoes
	 */
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
	
	/*
	 * Metodo que testa exclusao
	 */
	private void delete() {
		post1 = daoPost.selectById(1);
		assertNotNull(post1);
		daoPost.remove(post1);
		
		post1 = daoPost.selectById(1);
		
		// Deve retornar null
		assertNull(post1);
	}
	
}