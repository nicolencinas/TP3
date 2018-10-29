package yog;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
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
	SavesManager save=new SavesManager();
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
		
		
		frame.add(text);
		
		
		JComboBox<String> combo=new JComboBox<String>();
		combo.addItem("Seleccionar...");
		combo.addItem("[Genero]");
		combo.addItem("Femenino");
		combo.addItem("Masculino");
		combo.addItem("[Deporte]");
		combo.addItem("Atletismo");
		combo.addItem("Badminton");
		combo.addItem("Baloncesto 3x3");
		combo.addItem("Balonmano de playa");
		combo.addItem("Boxeo");
		combo.addItem("Breaking");
		combo.addItem("Ciclismo");
		combo.addItem("Ecuestre");
		combo.addItem("Escalada deportiva");
		combo.addItem("Esgrima");
		combo.addItem("Futsal");
		combo.addItem("Gimnasia acrobatica");
		combo.addItem("Gimnasia artistica");
		combo.addItem("Gimnasia ritmica");
		combo.addItem("Gimnasia Trampolin");
		combo.addItem("Gimnasia trampolin");
		combo.addItem("Golf");
		combo.addItem("Hockey 5");
		combo.addItem("Judo");
		combo.addItem("Karate");
		combo.addItem("Levantamiento de pesas");
		combo.addItem("Lucha");
		combo.addItem("Natacion");
		combo.addItem("Patinaje de velocidad sobre ruedas");
		combo.addItem("Pentatlon moderno");
		combo.addItem("Piraguismo");
		combo.addItem("Remo");
		combo.addItem("Rugby siete");
		combo.addItem("Saltos");
		combo.addItem("Taekwondo");
		combo.addItem("Tenis");
		combo.addItem("Tenis de mesa");
		combo.addItem("Tiro con arco");
		combo.addItem("Tiro deportivo");
		combo.addItem("Triatlon");
		combo.addItem("Vela");
		combo.addItem("Voley-playa");
		combo.addItem("[Pais]");
		combo.addItem("Afganistan");
		combo.addItem("Albania");
		combo.addItem("Alemania");
		combo.addItem("Andorra");
		combo.addItem("Angola");
		combo.addItem("Antigua y Barbuda");
		combo.addItem("Arabia Saudi");
		combo.addItem("Argelia");
		combo.addItem("Argentina");
		combo.addItem("Armenia");
		combo.addItem("Aruba");
		combo.addItem("Australia");
		combo.addItem("Austria");
		combo.addItem("Azerbaiyan");
		combo.addItem("Bahamas");
		combo.addItem("Bahrein");
		combo.addItem("Bangladesh");
		combo.addItem("Barbados");
		combo.addItem("Belgica");
		combo.addItem("Belice");
		combo.addItem("Benin");
		combo.addItem("Bermudas");
		combo.addItem("Bhutan");
		combo.addItem("Bielorrusia");
		combo.addItem("Bolivia");
		combo.addItem("Bosnia");
		combo.addItem("Botsuana");
		combo.addItem("Brasil");
		combo.addItem("Brunei");
		combo.addItem("Bulgaria");
		combo.addItem("Burkina Faso");
		combo.addItem("Burundi");
		combo.addItem("Cabo Verde");
		combo.addItem("Camboya");
		combo.addItem("Camerun");
		combo.addItem("Canada");
		combo.addItem("Chad");
		combo.addItem("Chile");
		combo.addItem("China Taipei");
		combo.addItem("Chipre");
		combo.addItem("Colombia");
		combo.addItem("Comoras");
		combo.addItem("Congo");
		combo.addItem("Costa de marfil");
		combo.addItem("Costa Rica");
		combo.addItem("Croacia");
		combo.addItem("Cuba");
		combo.addItem("Dinamarca");
		combo.addItem("Djibouti");
		combo.addItem("Dominica");
		combo.addItem("Ecuador");
		combo.addItem("Egipto");
		combo.addItem("El salvador");
		combo.addItem("Emiratos arabes unidos");
		combo.addItem("Eritrea");
		combo.addItem("Eslovaquia");
		combo.addItem("Eslovenia");
		combo.addItem("Espania");
		combo.addItem("Estados Federados de micronesia");
		combo.addItem("Estados Unidos");
		combo.addItem("Estonia");
		combo.addItem("Eswatini");
		combo.addItem("Etiopia");
		combo.addItem("Ex republica Yugoslava");
		combo.addItem("Federacion Rusa");
		combo.addItem("Filipinas");
		combo.addItem("Finlandia");
		combo.addItem("Fiyi");
		combo.addItem("Francia");
		combo.addItem("Gabon");
		combo.addItem("Gambia");
		combo.addItem("Georgia");
		combo.addItem("Ghana");
		combo.addItem("Gran Bretana");
		combo.addItem("Granada");
		combo.addItem("Grecia");
		combo.addItem("Guam");
		combo.addItem("Guatemala");
		combo.addItem("Guinea");
		combo.addItem("Guinea ecuatorial");
		combo.addItem("Guinea-Bissau");
		combo.addItem("Guyana");
		combo.addItem("Haiti");
		combo.addItem("Honduras");
		combo.addItem("Hong Kong");
		combo.addItem("Hungria");
		combo.addItem("India");
		combo.addItem("Indonesia");
		combo.addItem("Iraq");
		combo.addItem("Irlanda");
		combo.addItem("Islandia");
		combo.addItem("Islas Caiman");
		combo.addItem("Islas Cook");
		combo.addItem("Islas Marshall");
		combo.addItem("Islas Salomon");
		combo.addItem("Islas Virgenes Britanicas");
		combo.addItem("Islas Virgenes EstadoUnidenses");
		combo.addItem("Israel");
		combo.addItem("Italia");
		combo.addItem("Jamaica");
		combo.addItem("Japon");
		combo.addItem("Jordania");
		combo.addItem("Kazajstan");
		combo.addItem("Kenia");
		combo.addItem("Kirguistan");
		combo.addItem("Kiribati");
		combo.addItem("Kosovo");
		combo.addItem("Kuwait");
		combo.addItem("Lesotho");
		combo.addItem("Letonia");
		combo.addItem("Libano");
		combo.addItem("Liberia");
		combo.addItem("Libia");
		combo.addItem("Liechtenstein");
		combo.addItem("Lituania");
		combo.addItem("Luxemburgo");
		combo.addItem("Madagascar");
		combo.addItem("Malasia");
		combo.addItem("Malawi");
		combo.addItem("Maldivas");
		combo.addItem("Mali");
		combo.addItem("Malta");
		combo.addItem("Marruecos");
		combo.addItem("Mauricio");
		combo.addItem("Mauritania");
		combo.addItem("Mexico");
		combo.addItem("Monaco");
		combo.addItem("Mongolia");
		combo.addItem("Montenegro");
		combo.addItem("Mozambique");
		combo.addItem("Myanmar");
		combo.addItem("Namibia");
		combo.addItem("Nauru");
		combo.addItem("Nepal");
		combo.addItem("Nicaragua");
		combo.addItem("Niger");
		combo.addItem("Nigeria");
		combo.addItem("Noruega");
		combo.addItem("Nueva Zelanda");
		combo.addItem("Oman");
		combo.addItem("Paises Bajos");
		combo.addItem("Pakistan");
		combo.addItem("Palaos");
		combo.addItem("Palestina");
		combo.addItem("Panama");
		combo.addItem("Papua-Nueva Guinea");
		combo.addItem("Paraguay");
		combo.addItem("Peru");
		combo.addItem("Polonia");
		combo.addItem("Portugal");
		combo.addItem("Puerto Rico");
		combo.addItem("Qatar");
		combo.addItem("Republica Arabe Siria");
		combo.addItem("Republica Centroafricana");
		combo.addItem("Republica Checa");
		combo.addItem("Republica de Corea");
		combo.addItem("Republica de Moldavia");
		combo.addItem("Republica de Timor Oriental");
		combo.addItem("Republica del Congo");
		combo.addItem("Republica de Lao");
		combo.addItem("Republica Dominicana");
		combo.addItem("Republica de Iran");
		combo.addItem("Republica de China");
		combo.addItem("Republica democratica de Corea");
		combo.addItem("Republica de Tanzania");
		combo.addItem("Ruanda");
		combo.addItem("Rumania");
		combo.addItem("Samoa");
		combo.addItem("Samoa Americana");
		combo.addItem("San Cristobal Y nieves");
		combo.addItem("San Marino");
		combo.addItem("San Vicente y las Granadinas");
		combo.addItem("Santa Lucia");
		combo.addItem("Santo Tome y principe");
		combo.addItem("Senegal");
		combo.addItem("Serbia");
		combo.addItem("Seychelles");
		combo.addItem("Sierra Leona");
		combo.addItem("Singapur");
		combo.addItem("Somalia");
		combo.addItem("Sri Lanka");
		combo.addItem("Sudafrica");
		combo.addItem("Sudan");
		combo.addItem("Sudan del Sur");
		combo.addItem("Suecia");
		combo.addItem("Suiza");
		combo.addItem("Surinam");
		combo.addItem("Tailandia");
		combo.addItem("Tayikistan");
		combo.addItem("Togo");
		combo.addItem("Tonga");
		combo.addItem("Trinidad y Tobago");
		combo.addItem("Tunez");
		combo.addItem("Turkmenistan");
		combo.addItem("Turquia");
		combo.addItem("Tuvalu");
		combo.addItem("Ucrania");
		combo.addItem("Uganda");
		combo.addItem("Uruguay");
		combo.addItem("Uzbekistan");
		combo.addItem("Vanuatu");
		combo.addItem("Venezuela");
		combo.addItem("Vietnam");
		combo.addItem("Yemen");
		combo.addItem("Zambia");
		combo.addItem("Zimbabue");
		
		
		combo.setBounds(780,5,120,50);
		combo.setBorder(new TitledBorder("Seleccione filtro:"));
		text.setBounds(620,10,150,40);	   
		frame.add(combo);
		
		JButton limpiar=new JButton("Clear");
		limpiar.setBounds(905,20,65,20);
		frame.add(limpiar);
	
		
		
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
		
		String json=save.cargar();
				
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
 					
 					if (esValorDeterminado(formatear(value),atletas))
 						value=formatear(value);
 					
 					if (!text.getText().equals("")) 
 					{
 					
 						value=capitalizar(value);
 						
 						
 					if	(esValorDeterminado(value,atletas)) 
 					{
 							text.setText(value);
 						filtro(value,table);
 						
 						render.setInput(value);
 						combo.setSelectedItem(value);
 						table.updateUI();
 					JOptionPane.showMessageDialog(frame, "Se encontraron: "+Main.Cuantos(atletas, text.getText())+" resultados","Busqueda para: "+text.getText(), JOptionPane.INFORMATION_MESSAGE);
 					
 					
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
	   
		
		combo.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) 
			   {
				   
			      if (esValorDeterminado(combo.getSelectedItem().toString(),atletas) )
			      {
			    	  String value=combo.getSelectedItem().toString();
			    	  text.setText(value);
						filtro(value,table);
						
						render.setInput(value);
						table.updateUI();
					
					
					JOptionPane.showMessageDialog(frame, "Se encontraron: "+Main.Cuantos(atletas, text.getText())+" resultados","Busqueda para: "+text.getText(), JOptionPane.INFORMATION_MESSAGE);
					frame.requestFocus();
			      }
			      else 
					{
			    	  combo.setSelectedItem("Seleccionar...");
						render.setInput("");
						
					    text.setText("");
	 					
					}
			   }
			   });
		
		
	   
	   frame.add(pane);
	   
		table.setAutoscrolls(true);
		
		
		limpiar.addActionListener(new ActionListener() 
		{
			   @Override
			   public void actionPerformed(ActionEvent e) 
			   {
				 combo.setSelectedItem("Seleccionar...");
				 text.setText("");
				 filtro("",table);
			   }
			   
			   
		});
		
				
	    
	}
}
