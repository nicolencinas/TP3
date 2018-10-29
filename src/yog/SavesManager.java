package yog;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

public class SavesManager 
{

	FileWriter fw=null;
	ArrayList<String> list;
	StringBuilder retorno;
	String elem;
	
	public SavesManager()
	{

		list=new ArrayList<String>();
		elem="";
		
	}
	
	public String jsonConstruct (String path) 
	{
		 Gson gson= new Gson();
		 
			ArrayList <Atleta> lista = new ArrayList <Atleta>();
			ArrayList <String> at = new ArrayList <String>();
			String nombreArchivo=path;
			String rutaArchivo = "C:\\Ficheros-Excel\\"+nombreArchivo;
			XSSFWorkbook worbook=null;
			

			try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
				// leer archivo excel
				worbook = new XSSFWorkbook(file);
				//obtener la hoja que se va leer
				XSSFSheet sheet = worbook.getSheetAt(0);
				//obtener todas las filas de la hoja excel
				Iterator<Row> rowIterator = sheet.iterator();
	 
				Row row;
				// se recorre cada fila hasta el final
				while (rowIterator.hasNext()) {
					row = rowIterator.next();
					//se obtiene las celdas por fila
					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell;
					//se recorre cada celda
					while (cellIterator.hasNext())
					{
						// se obtiene la celda en específico y se la imprime
						cell = cellIterator.next();
						at.add(cell.getStringCellValue());
						//System.out.print(cell.getStringCellValue()+" ");
					}
					
					lista.add(new Atleta(at.get(0),at.get(1),at.get(2),at.get(3)));
					at.clear();
					//System.out.println();
				}
			} catch (Exception e) {
				e.getMessage();
					
			}finally 
			{
				try {
					worbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			String retorno="[";
			int i=0;
			System.out.println(lista.size());
			for (Atleta a : lista) 
			{
				
				retorno +=gson.toJson(a);
				if (i!=lista.size()-1)
				{
					retorno+=",\n";
				}
				i++;
			}
		
			retorno+="]";
		
			return retorno;

		}
	
	public void testAdminrigths() throws IOException 
	{
		File f=new File("C:/test.txt");
		
		 
	
		fw=new FileWriter (f);
		
		fw.write("test");	
		

 	
		
        	if (fw!=null)
        	{
            	try 
            	{
                	fw.close(); //cerramos el archivo
            	} catch (IOException e) {
                	e.printStackTrace();
            	}
            	fw = null;//Cambiamos el valor del FileWriter a null para borrar los datos almacernados en tiempo de ejeccuion
        	}
		f.delete();
		
}
		
	
	public void guardar(String json) throws Exception
	{
		list.clear(); 
		
		File f=new File("output.json");
	

		
		try 
		{
        	fw = new FileWriter(f);
        	
                
                fw.write(System.getProperty("line.separator")); 
            	
            	elem = json; 
            	fw.write(elem);
        	
    	} catch (IOException e) 
		{
        	e.printStackTrace();
    	}
    	finally{
        	if (fw!=null)
        	{
            	try {
                	fw.close(); 
            	} catch (IOException e) {
                	e.printStackTrace();
            	}
            	fw = null;
        	}
    	}
}

	public String cargar()
	{
		String linea="";
	
	File f = new File( "input.json" ); 
	
	if (f.exists())  
	{
		
	BufferedReader entrada = null; 
	try 
	{ 
	entrada = new BufferedReader( new FileReader( f ) ); 
	while(entrada.ready())
	linea+=entrada.readLine();
	System.out.println(linea);
	
	}catch (Exception e)
	{
		
	}
	
	}
	return linea;
	}
	}

