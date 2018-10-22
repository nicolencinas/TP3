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
	public static String jsonConstruct (String path) 
	{
		 Gson gson= new Gson();
		 
			ArrayList <Atleta> lista = new ArrayList <Atleta>();
			ArrayList <String> at = new ArrayList <String>();
			String nombreArchivo=path;
			String rutaArchivo = "C:\\Ficheros-Excel\\"+nombreArchivo;
			XSSFWorkbook worbook=null;
			

			try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
				
				worbook = new XSSFWorkbook(file);
				XSSFSheet sheet = worbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
	 
				Row row;
				while (rowIterator.hasNext()) {
					row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell;
					while (cellIterator.hasNext())
					{
						cell = cellIterator.next();
						at.add(cell.getStringCellValue());
					}
					
					lista.add(new Atleta(at.get(0),at.get(1),at.get(2),at.get(3)));
					at.clear();
				}
			} catch (Exception e) {
				e.getMessage();
					
			}finally 
			{
				try {
					worbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			String retorno="[";
			for (Atleta a : lista) 
			{
				retorno +=gson.toJson(a)+" \n";
			}
		
			retorno+="]";
			 System.out.println(lista.get(2)+" Y  "+lista.get(1)+" Comprueba mismo deporte: "+lista.get(2).mismoDeporte(lista.get(1)));
			 System.out.println(lista.get(2)+" "+lista.get(1)+" Comprueba misma nacionalidad: "+lista.get(2).mismaNacionalidad(lista.get(1)));
			 System.out.println(lista.get(2)+" "+lista.get(1)+" Comprueba mismo genero: "+lista.get(2).mismoGenero(lista.get(1)));
			 System.out.println(lista.get(2)+" "+lista.get(1)+" Compruba misma persona "+lista.get(2).equals(lista.get(1)));
			return retorno;
			
			
			
			
				
		}

	public static void main(String[] args) 
	{
		
		System.out.println(jsonConstruct("YOG.xlsx"));
	}
		

	
		

}


