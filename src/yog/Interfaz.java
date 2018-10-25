package yog;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.google.gson.Gson;

import javax.swing.JTextArea;

public class Interfaz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		String json=Main.jsonConstruct("YOG.xlsx");
				
				Gson gson=new Gson();
			
				Atleta[] atletas= gson.fromJson(json, Atleta[].class );
				
		String e="";
		int i=0;
		Object[][] objetos=new Object[2141][4];
		for (Atleta a: atletas)
		{
			
			objetos[i][0] =a.getName();objetos[i][1] =a.getGenre();objetos[i][2] =a.getSport();objetos[i][3] =a.getNacionality();
			i++;
		}
		
		JTable table =new JTable();
		table.setBounds(10, 11, 538, 929);
		
		table.setModel(new DefaultTableModel(
			objetos,
			new String[] {
				"Nombre", "Genero", "Deporte", "Nacionalidad"
			}
		));
	   JScrollPane pane=new JScrollPane (table);
		
	   table.getColumnModel().getColumn(0).setPreferredWidth(200);
	   table.getColumnModel().getColumn(1).setPreferredWidth(20);
	   pane = new javax.swing.JScrollPane(); 
	   pane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); pane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	   pane.setViewportView(table); 
	   pane.setBounds(10, 10, 600, 955); 
	   frame.add(pane);
		table.setAutoscrolls(true);
		
		
		
		
				
	    
	}
}
