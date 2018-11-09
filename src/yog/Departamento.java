package yog;

import java.util.ArrayList;

public class Departamento
{
	private ArrayList <Atleta> dep;

	Departamento(){
		dep=new ArrayList <Atleta>();
	}
	
	public ArrayList <Atleta> getIntegrantes()
	{
		return dep;
	}
	public boolean agregarAtleta(Atleta a) 
	{
		if (dep.size()==4)
			return false;
		else 
		{
			dep.add(a);
			return true;
		}
	}

	public boolean estaLleno()
	{
	   return (dep.size()==4);
	}
	
	public String toString() 
	{
		
		String re="";
		for (Atleta a :dep) 
		{
			re+="\r\n"+a+"\r\n\r\n------------------\r\n";
			
		}
		return re;
	}
}
