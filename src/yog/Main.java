package yog;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

	public static void main(String[] args) 
	{
	
		ArrayList <Atleta> lista = new ArrayList <Atleta>();
		ArrayList <String> at = new ArrayList <String>();
		String nombreArchivo="YOG.xlsx";
		String rutaArchivo = "C:\\Ficheros-Excel\\"+nombreArchivo;
		
		

		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
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
			//worbook.close();
		}
		
		for (Atleta a :lista)
			System.out.println(a);
		
			
	}

	
		

	}


