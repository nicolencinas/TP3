package yog;

public class Atleta 
{

	private String nombre;
	private String nacionalidad;
	private String deporte;
	private String genero;
	
	public Atleta(String nombre,String genero,String deporte,String nacionalidad)
	{
		this.nombre=nombre;
		this.genero=genero;
		this.deporte=deporte;
		this.nacionalidad=nacionalidad;
		
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
		return "Atleta: "+nombre + "\r\nGenero: "+genero+"\r\nDeporte: "+deporte+"\r\nNacionalidad: "+nacionalidad ;
	}
	
	public boolean mismoDeporte(Atleta at)
	{
		return this.deporte.equals(at.deporte);
	}
	
	public boolean mismaNacionalidad(Atleta at)
	{
		return this.nacionalidad.equals(at.nacionalidad);
	}
	
	public boolean mismoGenero(Atleta at)
	{
		return this.genero.equals(at.genero);
	}
	
	public boolean equals(Atleta at)
	{
		return this.nombre.equals(at.nombre);
	}
	
	
}
