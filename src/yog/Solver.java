package yog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Solver 
{
	private ArrayList<Atleta> listaAtletas = new ArrayList<Atleta>();
	 ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private LinkedList<Atleta> femenino = new LinkedList<Atleta>();
	private LinkedList<Atleta> masculino = new LinkedList<Atleta>();
	private ArrayList<Atleta> temp = new ArrayList<Atleta>();
	int totalDeptos=0;
	StringBuilder output= new StringBuilder("\nInformacion de las habitaciones: \n");
	
	SavesManager save=new SavesManager();
	
	
	
	public Solver(Atleta[] atletas) 
	{
		for (Atleta a :atletas)
		{
			listaAtletas.add(a);
		}
	}
	
	public String estadisticasIniciales() 
	{
		Integer fem=Cuantos("Femenino");
		Integer masc=Cuantos("Masculino");
		Integer femComp=fem/4;
		Integer mascComp=masc/4;
		Integer femInc=fem-femComp*4;
		Integer mascInc=masc-mascComp*4;
		totalDeptos=0;
		totalDeptos+=femComp+mascComp;
		
		StringBuilder builder=new StringBuilder("\n Analisis previo de las habitaciones: \n");
		
		builder.append("Cantidad de atletas femeninos: ");
		builder.append(fem+"\n");
		builder.append("Departamentos de 4 : "+femComp+"\n");
		if (femInc!=0) 
		{
			builder.append("Departamento adicional de : " +femInc+" mujeres"+"\n");
			totalDeptos++;
		}
		
		builder.append("\n");
		
		builder.append("Cantidad de atletas masculinos: ");
		builder.append(masc+"\n");
		builder.append("Departamentos de 4 : "+mascComp+"\n");
		if (mascInc!=0) 
		{
			builder.append("Departamento adicional de : " +mascInc+" varones"+"\n");
			totalDeptos++;
		}
		
		builder.append("\nCantidad de habitaciones necesarias: "+ totalDeptos);
		return builder.toString();
		
	}
	
	
	public int Cuantos(String dato) 
	{
		int i=0;
	
		String d=dato.toLowerCase();
		for (Atleta a:listaAtletas) 
		{
			String nacion=a.getNacionality().toLowerCase();
			String gen=a.getGenre().toLowerCase();
			String dep=a.getSport().toLowerCase();
			
			if (nacion.equals(d)) 
			{
				i++;
			}
			if (gen.equals(d)) 
			{
				i++;
			}
			if (dep.equals(d)) 
			{
				i++;
			}
			
		}
		return i;
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
	public void ordenarPorGenero() 
	{
		Collections.sort(listaAtletas, new compararGenero());
	}

	//duplicamos el array original 
	public ArrayList<Atleta> duplicarListaAtletas() 
	{
		Collections.copy(temp, listaAtletas);
		return temp;
	}

	//llena el array femenino con atletas femaninas y array masculino con altetas masculinos
	public void repartirPorGenero()
	{
		femenino=new LinkedList <Atleta>();
		masculino=new LinkedList <Atleta>();
		for(Atleta a:listaAtletas){
			if(a.getGenre().equals("Femenino")){
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
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		crearIdeales(femenino);
		output.append("\nCantidad de departamentos ideales \n(Mismo Genero,Nacioanalidad y deporte): \n");
		
		int IdealesFemeninos=listaDepartamentos.size();
		output.append("Femeninos ideales :"+IdealesFemeninos+"\n");
		
		crearIdeales(masculino);
		
		int IdealesMasculinos=listaDepartamentos.size()-IdealesFemeninos;
		output.append("Masculinos ideales: "+IdealesMasculinos+"\n");
		output.append("Ideales totales: "+(IdealesFemeninos+IdealesMasculinos)+"\n");
		
		
		for (Departamento dep: listaDepartamentos)
		{
			for (Atleta a: dep.getIntegrantes())
			{
				this.listaAtletas.remove(a);
			}
		}
		
		System.out.println("La cantidad de atletas sin asignar es: "+listaAtletas.size());
		
		


	}
	
	
	private boolean sonTodosIguales(int inicio,int fin,LinkedList <Atleta> atletas) 
	{
		boolean retorno=true; 
		for (int i=inicio;i<fin;i++) 
		{
			retorno &= atletas.get(i).mismaNacionalidad(atletas.get(i+1)) && atletas.get(i).mismoDeporte(atletas.get(i+1)) ;
		}
		
		return retorno;
		
	}		
		
	public void crearIdeales(LinkedList<Atleta> atletas)
	{
		int i=0;
		while (i<atletas.size()-3)
		{
			
				if (sonTodosIguales(i,i+3,atletas))
				{
					Departamento depto=new Departamento();
					depto.agregarAtleta(atletas.get(i));
					depto.agregarAtleta(atletas.get(i+1));
					depto.agregarAtleta(atletas.get(i+2));
					depto.agregarAtleta(atletas.get(i+3));
					listaDepartamentos.add(depto);
					i+=4;
					
					
				}
				
				else i++;
			
		}
		

	}
	
	public String estadisticasFinales()
	{
		return output.toString();
	}

	
	

}
