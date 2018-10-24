package yog;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
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
		
		JTextArea textArea = new JTextArea();
		
		ScrollPane scroll=new ScrollPane();
		scroll.setBounds(10, 11, 538, 929);
		scroll.add(textArea);
		frame.getContentPane().add(scroll);
		
		String json=Main.jsonConstruct("YOG.xlsx");
				
				Gson gson=new Gson();
			
				Atleta[] atletas= gson.fromJson(json, Atleta[].class );
				
		String e="";
		for (Atleta a: atletas)
		{
			e +=a+"\n";
		}
		
		textArea.setText(e);
		
		
				
	    
	}
}
