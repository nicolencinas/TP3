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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


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
		
		
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String json=Main.jsonConstruct("YOG.xlsx");
				
				Gson gson=new Gson();
			
				Atleta[] atletas= gson.fromJson(json, Atleta[].class );
				
		int i=0;
		
		
		Object[][] objetos=new Object[2244][5];
		for (Atleta a: atletas)
		{
			int ub=i+1;
			String ubi=ub+"";
			objetos [i][0]=ubi ;objetos[i][1] =a.getName();objetos[i][2] =a.getGenre();objetos[i][3] =a.getSport();objetos[i][4] =a.getNacionality();
			i++;
		}
		
		JTable table =new JTable();
		table.setBounds(10, 11, 538, 929);
		
		table.setModel(new DefaultTableModel(
			objetos,
			new String[] {
				"Nº" ,"Nombre", "Genero", "Deporte", "Nacionalidad"
			}
		));
		
		System.out.println(Main.Cuantos(atletas, "ciclismo"));
	   JScrollPane pane=new JScrollPane (table);
	   table.getColumnModel().getColumn(0).setMinWidth(30);
	   table.getColumnModel().getColumn(0).setMaxWidth(30);
	   table.getColumnModel().getColumn(1).setMinWidth(200);
	   table.getColumnModel().getColumn(1).setMaxWidth(200);
	   table.getColumnModel().getColumn(2).setMinWidth(60);
	   table.getColumnModel().getColumn(2).setMaxWidth(60);
	   table.getColumnModel().getColumn(3).setMaxWidth(180);
	   table.getColumnModel().getColumn(3).setMinWidth(180);
	   pane = new javax.swing.JScrollPane(); 
	   pane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); pane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	   pane.setViewportView(table); 
	   pane.setBounds(10, 10, 600, 955); 
	   frame.add(pane);
		table.setAutoscrolls(true);
		
		
		
		
				
	    
	}
}
