package yog;

import java.util.ArrayList;

public class Departamento
{
	private ArrayList <Atleta> dep;

	Departamento(){
		dep=new ArrayList <Atleta>();
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
			re+=a+"\n";
		}
		return re;
	}
}
