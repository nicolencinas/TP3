package yog;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DepartamentoTest {

	private Atleta a;
	private Atleta b;
	private Atleta c;
	private Atleta d;
	private Atleta e;
	private Departamento departamento;

	@Before
	public void setUp(){
		a=new Atleta("Monica","Femenino","Natacion","Mexico");
		b=new Atleta("Morena","Femenino","Basquet","Argentina");
		c=new Atleta("Luciana","Femenino","Jokey","Argentina");
		d=new Atleta("Tatiana","Femenino","Jokey","Brasil");
		e=new Atleta("Mauro","Masculino","Futsal","Costa Rica");
		departamento=new Departamento();	
	}
	
	@Test
	public void AgregadoYvalidezDeDeptoTest() {
		departamento.agregarAtleta(a);
		departamento.agregarAtleta(b);
		departamento.agregarAtleta(c);
		assertTrue(departamento.agregarAtleta(d));
		assertTrue(departamento.estaLleno());
		departamento.agregarAtleta(e);
		assertFalse(departamento.agregarAtleta(e));
		assertFalse(departamento.estaLleno()==false);
	}

}