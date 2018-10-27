package yog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;


public class Main 
{
	
	public static int Cuantos(Atleta[] at,String dato) 
	{
		int i=0;
	
		String d=dato.toLowerCase();
		for (Atleta a:at) 
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
	public static String jsonConstruct (String path) 
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

	public static void main(String[] args) 
	{
		
	
		
	
	}
		

	
		

}


