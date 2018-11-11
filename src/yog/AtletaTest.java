package yog;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AtletaTest {

	private Atleta a;
	private Atleta b;
	private Atleta c;
	private Atleta d;
	private Atleta e;

	
	@Before
	public void setUp(){
		a=new Atleta("Monica","Femenino","Natacion","Mexico");
		b=new Atleta("Morena","Femenino","Basquet","Argentina");
		c=new Atleta("Luciana","Femenino","Jokey","Argentina");
		d=new Atleta("Tatiana","Femenino","Jokey","Brasil");
		e=new Atleta("Mauro","Masculino","Futsal","Costa Rica");
	}
	
	@Test
	public void mismoDeporteTest() {
		assertTrue(c.mismoDeporte(d));
		assertFalse(a.mismoDeporte(e));
	}
	
	@Test
	public void mismaNacionalidadTest() {
		assertTrue(b.mismaNacionalidad(c));
		assertFalse(a.mismaNacionalidad(b));
	}
	
	@Test
	public void mismoGeneroTest() {
		assertTrue(a.mismoGenero(b));
		assertFalse(c.mismoGenero(e));
	}
}

