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
			for (Atleta a : lista) 
			{
				retorno +=gson.toJson(a)+", \n";
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
		Gson gson=new Gson();
		String retorno=jsonConstruct("YOG.xlsx");
		System.out.println(retorno);
		
		Atleta [] ata=gson.fromJson(retorno, Atleta [].class);
		for (Atleta a: ata)
			System.out.println(a);
	}
		

	
		

}


