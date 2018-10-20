package yog;

public class Atleta 
{

	private String nombre;
	private String nacionalidad;
	private String deporte;
	private String genero;
	
	public Atleta(String nombre,String nacionalidad,String deporte,String genero)
	{
		this.nombre=nombre;
		this.nacionalidad=nacionalidad;
		this.deporte=deporte;
		this.genero=genero;
	}
	
	//getters y setters
	
	public String getName() 
	{
		return nombre;
	}
	public String getNacionality() 
	{
		return nacionalidad;
	}
	public String getSport() 
	{
		return deporte;
	}
	public String getGenre() 
	{
		return genero;
	}
	
	public void setName(String name) 
	{
		nombre=name;
	}
	public void setNacionality(String nacionality) 
	{
		nacionalidad=nacionality;
	}
	public void setSport(String sport) 
	{
		deporte=sport;
	}
	public void setGenre(String genre) 
	{
		genero=genre;
	}
	
	public String toString() 
	{
		return "Atleta: "+nombre +" Deporte: "+deporte+" Nacionalidad: "+nacionalidad + " Genero: "+genero;
	}
	
	
}
