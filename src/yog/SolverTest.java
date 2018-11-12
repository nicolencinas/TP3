package yog;



import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yog.Solver.compararGenero;

public class SolverTest {
	private static Atleta a;
	private static Atleta a1;
	private static Atleta a2;
	private static Atleta a3;
	private static Atleta b;
	private static Atleta c;
	private static Atleta d;
	private static Atleta e;
	private static Atleta f;
	private static Atleta[] arregloAtletas;
	private Solver sol;
	private Solver sol1;
	
	private LinkedList<Atleta> femenino;
	private LinkedList<Atleta> masculino;
	private LinkedList<Atleta> femenino2;
	private LinkedList<Atleta> masculino2;
	private Atleta[] arregloAtletas2;
	

	
	@Before
	public void setUp() {
		a = new Atleta("Monica", "Femenino", "Atletismo", "Argentina");
		a1=new Atleta("Gabriela", "Femenino", "Atletismo", "Argentina");
		a2=new Atleta("Marcela","Femenino","Atletismo","Argentina");
		a3=new Atleta("Fernanda","Femenino","Atletismo","Argentina");
		b = new Atleta("Roberto", "Masculino", "Nataciom", "Jordania");
		c = new Atleta("Romina", "Femenino", "ciclismo", "Argelia");
		d = new Atleta("Carlos", "Masculino", "Futsal", "USA");
		e = new Atleta("Oracio", "Masculino", "Basquet", "Rusia");
		f= new Atleta("Oracio", "Masculino", "Basquet", "Moldavia");
		
		arregloAtletas = new Atleta[] { a, b, c, d, e,f };
		arregloAtletas2=new Atleta[] {a,a1,a2,a3,b,c,d,e,f};
		femenino = new LinkedList<Atleta>();
		femenino.add(a);
		femenino.add(c);
		masculino = new LinkedList<Atleta>();
		masculino.add(b);
		masculino.add(d);
		masculino.add(e);
		masculino.add(f);
		
		femenino2 = new LinkedList<Atleta>();
		femenino2.add(c);
		femenino2.add(a);
		masculino2 = new LinkedList<Atleta>();
		masculino2.add(b);
		masculino2.add(f);
		masculino2.add(e);
		masculino2.add(d);
		
		
	}

	@Test
	public void comparaPorGeneroTest() {
		compararGenero porGenero = new compararGenero();
		assertTrue(porGenero.compare(a, c) == 0);
		assertTrue(porGenero.compare(a, b) != 0);
	}

	@Test
	public void comparaNacionalidadTest() {
		compararGenero porGenero = new compararGenero();
		assertTrue(porGenero.compare(a, c) == 0);
		assertTrue(porGenero.compare(a, b) != 0);
	}

	@Test
	public void comparaDeporteTest() {
		compararGenero porGenero = new compararGenero();
		assertTrue(porGenero.compare(a, c) == 0);
		assertTrue(porGenero.compare(a, b) != 0);
	}

	@Test
	public void ordenarPorGeneroTest() {
		sol = new Solver(arregloAtletas);
		sol1 = new Solver(arregloAtletas);
		sol1.ordenarPorGenero();
		assertFalse(sol.getListaAtletas().equals(sol1.getListaAtletas()));
		sol.ordenarPorGenero();
		assertTrue(sol.getListaAtletas().equals(sol1.getListaAtletas()));
	}

	@Test
	public void repartirPorGeneroTest() {

		sol = new Solver(arregloAtletas);
		sol.repartirPorGenero();
		assertEquals(true, sol.getFemenino().equals(femenino));
		assertEquals(true, sol.getMasculino().equals(masculino));

	}
	
	@Test
	public void duplicarListaAtleta() {
		sol = new Solver(arregloAtletas);
		sol.duplicarListaAtletas();
		Assert.assertEquals(sol.getListaAtletas(), sol.getTemp());
	}
	
	@Test 
	public void ordenrPorNacionalidadTest() {
		sol = new Solver(arregloAtletas);
		sol.ordenarPorGenero();
		sol.repartirPorGenero();
		sol.ordenarPorNacionalidad();
		Assert.assertEquals(sol.getMasculino(), masculino2);
		Assert.assertEquals(sol.getFemenino(), femenino2);
	}
	
	@Test
	public void cuantosTest() {
		sol = new Solver(arregloAtletas);
		assertEquals(sol.Cuantos("Masculino"),4);
		assertEquals(sol.Cuantos("Femenino"),2);
		assertEquals(sol.Cuantos("Argentina"),1);
		assertEquals(sol.Cuantos("Basquet"),2);
		
		assertNotEquals(sol.Cuantos("Argentina"),5);
		assertNotEquals(sol.Cuantos("Masculino"),2);
	}
	
	
	
	@Test
	public void crearIdealesTest() 
	{
		sol=new Solver(arregloAtletas2);
		sol.aplicarSolucion();
		
		ArrayList <Atleta> at=new ArrayList <Atleta>();
		at.add(a);
		at.add(a1);
		at.add(a2);
		at.add(a3);
		ArrayList <Departamento> dep=sol.getlistaDepartamentos();
		
		assertTrue(at.equals(dep.get(0).getIntegrantes()));
	}
}
	
	
	
	
	
	
	
	
	
	