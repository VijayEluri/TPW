package noticias;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoboNoticiasTest {

	@BeforeClass
	public static void prepare() {
		
	}

	@AfterClass
	public static void clearDatabase() {
		
	}
	
	@Test
	public void runTests() {
		
		//Acessando noticias do RSS
		try{
			RoboNoticias rn = new RoboNoticias("http://br-linux.org/feed/");
			System.out.println(rn.getNoticias().size());
		} catch (Exception e){
			assertEquals(1,2);
		}
		
	}
	
}
