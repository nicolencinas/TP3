package yog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Solver 
{
	private ArrayList<Atleta> listaAtletas = new ArrayList<Atleta>();
	private ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private LinkedList<Atleta> femenino = new LinkedList<Atleta>();
	private LinkedList<Atleta> masculino = new LinkedList<Atleta>();
	private ArrayList<Atleta> temp = new ArrayList<Atleta>();
	
	
	public Solver(Atleta[] atletas) 
	{
		for (Atleta a :atletas)
		{
			listaAtletas.add(a);
		}
		
	}
	
	
	public void imprimir() 
	{
		for (Atleta a :listaAtletas)
		{
			System.out.println(a);
		}
	}
	public static class compararGenero implements Comparator<Atleta> 
	{

		@Override
		public int compare(Atleta arg0, Atleta arg1) {

			return arg0.getGenre().compareTo(arg1.getGenre());
		}

	}
	
	public static class compararNacionalidad implements Comparator<Atleta> {

		@Override
		public int compare(Atleta arg0, Atleta arg1) {
			return arg0.getNacionality().compareTo(arg1.getNacionality());
		}

	}
	
	public static class compararDeporte implements Comparator<Atleta>
	 {

		@Override
		public int compare(Atleta o1, Atleta o2) {
			return o1.getSport().compareTo(o2.getSport());
			}
	}
	
	

	//ordenamos la  lista por genero, primero femenino y luego masculinos
	public void ordenarPorGenero() {
		Collections.sort(listaAtletas, new compararGenero());
	}

	//duplicamos el array original 
	public ArrayList<Atleta> duplicarListaAtletas() {
		Collections.copy(temp, listaAtletas);
		return temp;
	}

	//llena el array femenino con atletas femaninas y array masculino con altetas masculinos
	public void repartirPorGenero(){
		for(Atleta a:temp){
			if(a.getGenre()=="femenino"){
				femenino.add(a);
			}else{
				masculino.add(a);
			}
		}
	}
	
	//ordena por nacionalidad tanto el array femenino como masculino
	public void ordenarPorNacionalidad()
	{
		Collections.sort(femenino,new compararNacionalidad());
		Collections.sort(masculino,new compararNacionalidad());
	}
//falta transformar el json en arraylist
	public void resolvedor()
	{
		duplicarListaAtletas();
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();

	}
	
	
	public Departamento[] solver()
	{
		return null;
		
		
	}
	
	

}
