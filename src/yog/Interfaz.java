package yog;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.google.gson.Gson;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interfaz {

	private JFrame frame;
	MiRender render=new MiRender();
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

	public String capitalizar(String str) 
	{
		return Character.toUpperCase(str.charAt(0))+str.substring(1, str.length());
		
	}
	private boolean esValorDeterminado(String val, Atleta[] atletas)
	{
		String value=val.toLowerCase();
		for (Atleta a: atletas) 
		{
			if (value.equals(a.getGenre().toLowerCase()))
				return true;
			if (value.equals(a.getNacionality().toLowerCase()))
				return true;
			
			if (value.equals(a.getName().toLowerCase()))
				return true;
				
		     if (value.equals(a.getSport().toLowerCase()))
		    	 return true;
			
		}
		return false;
	}
	
	private void filtro(String consulta, JTable jtableBuscar)
	{
		DefaultTableModel dm;
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
       
		tr.setRowFilter(RowFilter.regexFilter(consulta));
}
	
	private String formatear(String consulta) 
	{
		String ret=consulta;
		String ret2="";
		
		for (int i=0;i<ret.length();i++)
		{
			char ch=ret.charAt(i);
			int j=(int)ch;
			
			if ((j>=65 && j<=90) || (j>=97 && j<=122)) 
			{
				System.out.println("letra"+ch+ " codigo" +j);
				ret2+=ch;
			}
		}
		
		String ret3=Character.toUpperCase(ret2.charAt(0))+ret2.substring(1, ret2.length());
		return ret3;
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(300, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField text=new JTextField();
		text.setBorder(new TitledBorder("Ingrese Busqueda:"));
		
		text.setBounds(800,10,150,40);	   
		frame.add(text);
		
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
		
		
		Object[][] objetos=new Object[atletas.length][5];
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
				"N�" ,"Nombre", "Genero", "Deporte", "Nacionalidad"
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
	   table.setEnabled(false);
	   pane = new javax.swing.JScrollPane(); 
	   pane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); pane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
	   pane.setViewportView(table); 
	   pane.setBounds(10, 10, 600, 955); 
	   table.setDefaultRenderer(Object.class, render);
	   
	   text.addKeyListener(new KeyAdapter() 
	   {
 			public void keyReleased(KeyEvent e)
 			{
 				if (e.getKeyCode()==KeyEvent.VK_ENTER) 
 				{
 					String value = text.getText();
 					
 					if (!text.getText().equals("")) 
 					{
 					
 						value=capitalizar(value);
 						
 						
 					if	(esValorDeterminado(value,atletas)) 
 					{
 							text.setText(value);
 						filtro(value,table);
 						
 						render.setInput(value);
 						table.updateUI();
 					JOptionPane.showMessageDialog(frame, "Se encontraron: "+Main.Cuantos(atletas, text.getText())+" resultados","Busqueda para: "+text.getText(), JOptionPane.OK_OPTION);
 					
 					
 					System.out.println(render.input);
 					
 					frame.requestFocus();
 					}
 					else 
 					{
 						render.setInput("");
 						JOptionPane.showMessageDialog(frame, "No se encontraron resultados");
 					    text.setText("");
 	 					
 					}
 				
 				}
 					
 			
 					
 				}
 				
 				if (text.getText().equals("")) 
 				{
 					render.setInput("");
 					filtro("",table);
 				}
 			}

		
 			
	   });
	   
	   
	   frame.add(pane);
	   
		table.setAutoscrolls(true);
		
		
		
		
				
	    
	}
}
