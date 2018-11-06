package yog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Solver 
{
	private static ArrayList<Atleta> listaAtletas = new ArrayList<Atleta>();
	 ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private LinkedList<Atleta> femenino = new LinkedList<Atleta>();
	private LinkedList<Atleta> masculino = new LinkedList<Atleta>();
	private ArrayList<Atleta> temp = new ArrayList<Atleta>();
	int totalDeptos=0;
	SavesManager save=new SavesManager();
	
	
	
	public Solver(Atleta[] atletas) 
	{
		for (Atleta a :atletas)
		{
			listaAtletas.add(a);
		}
	}
	
	public String estadisticas() 
	{
		Integer fem=Cuantos("Femenino");
		Integer masc=Cuantos("Masculino");
		Integer femComp=fem/4;
		Integer mascComp=masc/4;
		Integer femInc=fem-femComp*4;
		Integer mascInc=masc-mascComp*4;
		totalDeptos+=femComp+mascComp;
		
		StringBuilder builder=new StringBuilder("\n Analisis de la cantidad de habitaciones: \n");
		
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
	public void repartirPorGenero(){
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
		
		
		for (int i=0;i<femenino.size()-1;i++) 
		{
			int contador=1;
			
			for(int j=i+1;j<femenino.size();j++)
			{
				
				
				Atleta comparando=femenino.get(i);
				Atleta comparador=femenino.get(j);
				
				if (comparando.mismaNacionalidad(comparador) && comparando.mismoDeporte(comparador)) 
				{
					contador++;
					
				}
				else contador=1;
				
				if (contador==4)
				{
					Departamento depto=new Departamento();
					for (int e=j;e>j-4;e--)
					{
						depto.agregarAtleta(femenino.remove(e));
						
					}
					
					this.listaDepartamentos.add(depto);
				}
			}
		}
		
		
		
	
			
			
		
		System.out.println("Cantidad de femeninos si acomodar "+ femenino.size());
		System.out.println("Departamentos ideales de mujeres: "+listaDepartamentos.size());		
		

	}
	
	
	
	

	
	

}
