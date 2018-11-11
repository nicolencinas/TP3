package yog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import com.google.gson.Gson;

public class Solver 
{
	ArrayList<Atleta> listaAtletas = new ArrayList<Atleta>();
	 ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	private ArrayList<Departamento> ideales=new ArrayList<Departamento>();
	private ArrayList<Departamento> mayorias=new ArrayList<Departamento>();
	private ArrayList<Departamento> almenosDos=new ArrayList<Departamento>();
	private ArrayList<Departamento> ningunoIdeal=new ArrayList<Departamento>();
 	private LinkedList<Atleta> femenino = new LinkedList<Atleta>();
	private LinkedList<Atleta> masculino = new LinkedList<Atleta>();
	private ArrayList<Atleta> temp = new ArrayList<Atleta>();
	private String toJson="[";
	private Gson gson=new Gson();
	int totalDeptos=0;
	int deptosFemeninos=0;
	int deptosMasculinos=0;
	int conteoFemeninos=0;
	int conteoMasculinos=0;
	StringBuilder output= new StringBuilder("\r\nInformacion de las habitaciones: \r\n");
	
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
		
		StringBuilder builder=new StringBuilder("\r\n Analisis previo de las habitaciones: \r\n");
		
		builder.append("Cantidad de atletas femeninos: ");
		builder.append(fem+"\r\n");
		builder.append("Departamentos de 4 : "+femComp+"\r\n");
		if (femInc!=0) 
		{
			builder.append("Departamento adicional de : " +femInc+" mujeres"+"\r\n");
			totalDeptos++;
		}
		
		builder.append("\r\n");
		
		builder.append("Cantidad de atletas masculinos: ");
		builder.append(masc+"\r\n");
		builder.append("Departamentos de 4 : "+mascComp+"\r\n");
		if (mascInc!=0) 
		{
			builder.append("Departamento adicional de : " +mascInc+" varones"+"\r\n");
			totalDeptos++;
		}
		
		builder.append("\r\nCantidad de habitaciones necesarias: "+ totalDeptos);
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
		
		for (Atleta a:listaAtletas)
			temp.add(a);
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
		
		output.append("\r\nCantidad de departamentos ideales \r\n(Mismo Genero,Nacioanalidad y deporte): \r\n\r\n");
		int IdealesFemeninos=ideales.size();
		conteoFemeninos+=IdealesFemeninos;
		output.append("Femeninos ideales :"+IdealesFemeninos+"\r\n");
		
		crearIdeales(masculino);
		
		int IdealesMasculinos=ideales.size()-IdealesFemeninos;
		conteoMasculinos+=IdealesMasculinos;
		output.append("Masculinos ideales: "+IdealesMasculinos+"\r\n");
		output.append("Ideales totales: "+ideales.size()+"\r\n");
	
		LimpiarAgregados();
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		crearMayorias(femenino,0);
		
		output.append("\r\nCantidad de departamentos con mayoria ideales: \r\n");
		int MayoriaFemeninos=mayorias.size();
		conteoFemeninos+=MayoriaFemeninos;
		output.append("Femeninos mayoria: "+MayoriaFemeninos+"\r\n");
		
		crearMayorias (masculino,1);
		
		int MayoriaMasculinos=mayorias.size()-MayoriaFemeninos;
		conteoMasculinos+=MayoriaMasculinos;
		output.append("Masculinos mayoria: "+MayoriaMasculinos+"\r\n");
		output.append("Mayorias totales: "+mayorias.size()+"\r\n");
		
		LimpiarAgregados();
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		crearAlmenosDos(femenino,0);
		output.append("\nCantidad de departamentos con mitad ideales: \r\n");
		int AMDFemeninos=almenosDos.size();
		conteoFemeninos+=AMDFemeninos;
		output.append("Femeninos al menos dos: "+AMDFemeninos+"\r\n");
		
		crearAlmenosDos(masculino,1);
		
		int AMDMasculinos=almenosDos.size()-AMDFemeninos;
		conteoMasculinos+=AMDMasculinos;
		output.append("Masculinos al menos dos: "+AMDMasculinos+"\r\n");
		
		output.append("Al menos dos totales: "+almenosDos.size()+"\r\n");
		
		LimpiarAgregados();
		
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		completar();
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		LimpiarAgregados();
		crearNoIdeales(femenino);
		
		output.append("\r\nCantidad de departamentos no Ideales:\r\n(Mismo Genero y Nacioanalidad o solo ordenados por genero): \r\n");
		int NoIdealesFem=ningunoIdeal.size();
		conteoFemeninos+=NoIdealesFem;
		
		output.append("Femeninos NO Ideales : "+NoIdealesFem+"\r\n");
		
		LimpiarAgregados();
		
		crearNoIdeales (masculino);
		
		int NoIdealesMasculinos=ningunoIdeal.size()-NoIdealesFem;
		conteoMasculinos+=NoIdealesMasculinos;
		output.append("Masculinos NO Ideales: "+NoIdealesMasculinos+"\r\n");
		LimpiarAgregados();
		
		output.append("La lista de NO ideales es :" +ningunoIdeal.size());
		
		
		ordenarPorGenero();
		repartirPorGenero();
		ordenarPorNacionalidad();
		
		CrearDeptosEspeciales(femenino);
		CrearDeptosEspeciales(masculino);
		LimpiarAgregados();
		
		
		
		
		
		
		


	}
	
	
	



	private void CrearDeptosEspeciales(LinkedList<Atleta> atletas)
	{
		
			Departamento depto=new Departamento();
			for (Atleta a : atletas)
			{
				depto.agregarAtleta(a);

			}
			
			
			listaDepartamentos.add(depto);
			
		
	
	}

	private void crearNoIdeales(LinkedList <Atleta> atletas) 
	{
		int i=0;
		while (i<atletas.size()-3 )
		{
			Departamento depto=new Departamento();
			depto.agregarAtleta(atletas.get(i));
			depto.agregarAtleta(atletas.get(i+1));
			depto.agregarAtleta(atletas.get(i+2));
			depto.agregarAtleta(atletas.get(i+3));
			
			ningunoIdeal.add(depto);
			i+=4;
			
		}
		
		for (Departamento d: ningunoIdeal) 
		{
			if (!listaDepartamentos.contains(d))
			listaDepartamentos.add(d);
		}
		
		
		
		
	}

	private void LimpiarAgregados() 
	{
		for (Departamento dep: listaDepartamentos)
		{
			for (Atleta a: dep.getIntegrantes())
			{
				if (listaAtletas.contains(a))
							this.listaAtletas.remove(a);
			}
		}
	}
	
	public ArrayList<Atleta> getListaAtletas()
	{
		return listaAtletas;
		
	}
	private void completar() 
	{
		for (Departamento d: mayorias) 
		{
			Atleta a=mejorCandidato(d);
			if (a!=null)
			{
				d.agregarAtleta(a);
				
			}
			else d.agregarAtleta(candidatoPordeporte(d));
		}
		
		for (int i=0;i<2;i++)
		{
			Atleta a=null;
			for (Departamento d: almenosDos) 
			{
			 a=mejorCandidato(d);
			if (a!=null)
			{
				d.agregarAtleta(a);
				
			}
			else 
			{
				a=candidatoPordeporte(d);
				if (a!=null) 
				{
				d.agregarAtleta(a);	
				}
				else d.agregarAtleta(candidatoPorGenero(d));
			}
				
			}
			
		}
		
		
		
	}
	
	private Atleta candidatoPorGenero(Departamento d) 
	{
		for (Atleta a: listaAtletas) 
		{
			if (d.getIntegrantes().get(0).mismoGenero(a))
			{
				Atleta ret=a;
				listaAtletas.remove(a);
				return ret;
				
			}
		}
		return null;
		
	}

	private Atleta candidatoPordeporte(Departamento d) 
	{
		
		for (Atleta a: listaAtletas) 
		{
			if (d.getIntegrantes().get(0).mismoDeporte(a) && d.getIntegrantes().get(0).mismoGenero(a))
			{
				Atleta ret=a;
				listaAtletas.remove(a);
				return ret;
				
			}
		}
		return null;
	}

	private Atleta mejorCandidato(Departamento d) 
	{
		for (Atleta a: listaAtletas) 
		{
			if (d.getIntegrantes().get(0).mismaNacionalidad(a) && d.getIntegrantes().get(0).mismoGenero(a))
			{
				Atleta ret=a;
				listaAtletas.remove(a);
				return ret;
				
			}
		}
		return null;
		
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
		while (i<atletas.size()-3 )
		{
			
				if (sonTodosIguales(i,i+3,atletas))
				{
					Departamento depto=new Departamento();
					depto.agregarAtleta(atletas.get(i));
					depto.agregarAtleta(atletas.get(i+1));
					depto.agregarAtleta(atletas.get(i+2));
					depto.agregarAtleta(atletas.get(i+3));
					ideales.add(depto);
					i+=4;	
				}
				
				else i++;
			
		}
		
		for (Departamento d: ideales)
		{
			if (!listaDepartamentos.contains(d))
			listaDepartamentos.add(d);
		}
		
		
		

	}
	
	public void crearMayorias(LinkedList<Atleta> atletas, int j)
	{
		int i=0;
		
		while (i<atletas.size()-2)
		{
			
				if (sonTodosIguales(i,i+2,atletas))
				{
					Departamento depto=new Departamento();
					depto.agregarAtleta(atletas.get(i));
					depto.agregarAtleta(atletas.get(i+1));
					depto.agregarAtleta(atletas.get(i+2));
					mayorias.add(depto);
					i+=3;
					
					
				}
				
				else i++;
			
		}
		
		for (Departamento d: mayorias)
		{
			if (!listaDepartamentos.contains(d))
			listaDepartamentos.add(d);
		}
		
	}
	
	public void crearAlmenosDos(LinkedList<Atleta> atletas, int j)
	{
		int i=0;		
		while (i<atletas.size()-1)
		{
			
				if (atletas.get(i).mismaNacionalidad(atletas.get(i+1)) && atletas.get(i).mismoDeporte(atletas.get(i+1)))
				{
					Departamento depto=new Departamento();
					depto.agregarAtleta(atletas.get(i));
					depto.agregarAtleta(atletas.get(i+1));
					almenosDos.add(depto);
					i+=2;
					
					
				}
				
				else i++;
			
		}
		
		for (Departamento d: almenosDos)
		{
			if (!listaDepartamentos.contains(d))
			listaDepartamentos.add(d);
		}
		
	}
	
	public String estadisticasFinales()
	{
		return output.toString();
	}

	public Object getlistaDepartamentos()
	{
		
		return listaDepartamentos;
	}

	public String log()
	{
		StringBuilder retorno=new StringBuilder("");
		 SimpleDateFormat formateador = new SimpleDateFormat(
				   "dd 'de' MMMM 'de' yyyy 'a las' hh:mm:ss" , new Locale("es_ES"));
		 Date date =new Date();
		retorno.append(formateador.format(date));
		int i=1;
		retorno.append(this.estadisticasFinales()+"\r\n\r\n");
		for (Departamento depto :listaDepartamentos) 
		{
			
			retorno.append("Departamento Nª"+i+"\r\n");
			retorno.append(depto);
			
			
			toJson +=gson.toJson(depto);
				if (i!=listaDepartamentos.size())
				{
					toJson+=",\r\n";
				}
				
				toJson+="\r\n";
			i++;	
			
		}
		toJson+="]";
		
		
		return retorno.toString();
	}
	
	public String toJSon() 
	{
		return toJson;
	}

	public LinkedList<Atleta> getFemenino()
	{
		return femenino;
		
	}

	public LinkedList <Atleta> getMasculino() 
	{
		return masculino;
		
	}

	public ArrayList <Atleta> getTemp() 
	{
		return temp;
	}

	
	

}
