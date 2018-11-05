package yog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
public class Solver {
	
	public static class compararGenero implements Comparator<Atleta> {

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
	
	private ArrayList<Atleta> listaAtletas = new ArrayList<Atleta>();
	private ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private LinkedList<Atleta> femenino = new LinkedList<Atleta>();
	private LinkedList<Atleta> masculino = new LinkedList<Atleta>();
	private ArrayList<Atleta> temp = new ArrayList<Atleta>();
	private static SavesManager save= new SavesManager();

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
	public void ordenarPorNacionalidad(){
		Collections.sort(femenino,new compararNacionalidad());
		Collections.sort(masculino,new compararNacionalidad());
	}
//falta transformar el json en arraylist
	public void resolvedor(){
		duplicarListaAtletas();
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();

	}
	
	
	public static void main(String[] args)
	{
		Gson gson=new Gson();
		String json=save.cargar("input.json");
		Atleta[] atlet=gson.fromJson(json, Atleta[].class);
		
		List <Atleta> atletas=new ArrayList <Atleta>();
		for (Atleta a :atlet)
		{
			atletas.add(a);
		}
		
		Collections.sort( atletas,new compararGenero());
		String e="";
		for(Atleta a : atletas) 
		e+=(a)+"\n";
		
		System.out.println(atletas.size());
		try {
			save.guardar(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			
			
		}
		Departamento dep=new Departamento();
			System.out.println(dep.agregarAtleta(atletas.get(0)));
			System.out.println(dep.agregarAtleta(atletas.get(1)));
			System.out.println(dep.agregarAtleta(atletas.get(2)));
			System.out.println(dep.agregarAtleta(atletas.get(3)));
			System.out.println(dep.agregarAtleta(atletas.get(4)));
			System.out.println(dep.agregarAtleta(atletas.get(5)));
		
			System.out.println(dep);
		
			
	}
	

}
