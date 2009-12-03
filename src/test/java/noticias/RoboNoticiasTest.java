package noticias;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class RoboNoticiasTest {

	
	@Test
	public void runTests() {
		
		//Acessando noticias do RSS
		RoboNoticias rn = new RoboNoticias("http://br-linux.org/feed/");
		List<Noticia> noticias = rn.getNoticias();
		assertTrue(noticias.size() > 0);
				
	}
	
}
