package yog;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableModel;
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
	private JTextArea ta = new JTextArea("",33,42);
	String jsonFile="";
	Object[][] objetos=new Object[5][5];
	Atleta[] atletas=new Atleta[0];
	JTable table=new JTable();
	
	//Recursos para el timer
	static int contador=0;
	int dir=0;
	Timer timer;
	TimerTask tarea;
	
	

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
		frame.setBounds(300, 0, 1050, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField text=new JTextField();
		text.setVisible(false);
		text.setBorder(new TitledBorder("Ingrese Busqueda:"));
		
		
		frame.add(text);
		frame.setTitle("Gestor de habitaciones");
	Image titleIcon=new ImageIcon("Title.png").getImage();
		
		frame.setIconImage(titleIcon);
		

		JLabel selector=new JLabel();
		selector.setBounds(10, 10, 600, 955);;
		
		tarea=new TimerTask()
		{

			@Override
			public void run() 
			{
				
				ImageIcon icono;
				
				if (dir==1) 
				{
					System.out.println(contador);
					switch(contador) 
				{
					
				case 0:
					contador=1;
					icono=new ImageIcon("f00.png");
					selector.setIcon(icono);
				case 1:
					contador=2;
					icono=new ImageIcon(("f01.png"));
					selector.setIcon(icono);
				case 2:
					contador=3;
					icono=new ImageIcon(("f02.png"));
					selector.setIcon(icono);
					break;
				case 3:
					contador=4;
					icono=new ImageIcon(("f03.png"));
					selector.setIcon(icono);
					break;
				case 4:
					contador=5;
					icono=new ImageIcon(("f04.png"));
					selector.setIcon(icono);
					break;
				case 5:
					contador=6;
					icono=new ImageIcon(("f05.png"));
					selector.setIcon(icono);
					break;
				case 6:
					contador=7;
					icono=new ImageIcon(("f06.png"));
					selector.setIcon(icono);
					break;
				case 7:
					contador=8;
					icono=new ImageIcon(("f07.png"));
					selector.setIcon(icono);
					break;
				case 8:
					contador=9;
					icono=new ImageIcon(("f08.png"));
					selector.setIcon(icono);
					break;
				case 9:
					contador=10;
					icono=new ImageIcon(("f09.png"));
					selector.setIcon(icono);
					break;
				case 10:
					contador=11;
					icono=new ImageIcon(("f10.png"));
					selector.setIcon(icono);
					break;
				case 11:
					
					icono=new ImageIcon(("f11.png"));
					selector.setIcon(icono);
					break;
				
				}
				
			}
				
				else  
				{
					System.out.println(contador);
					switch(contador) 
				{
				case 11:
					contador=10;
					icono=new ImageIcon("f11.png");
					selector.setIcon(icono);
				case 10:
					contador=9;
					icono=new ImageIcon(("f10.png"));
					selector.setIcon(icono);
				case 9:
					contador=8;
					icono=new ImageIcon(("f09.png"));
					selector.setIcon(icono);
					break;
				case 8:
					contador=7;
					icono=new ImageIcon(("f08.png"));
					selector.setIcon(icono);
					break;
				case 7:
					contador=6;
					icono=new ImageIcon(("f07.png"));
					selector.setIcon(icono);
					break;
				case 6:
					contador=5;
					icono=new ImageIcon(("f06.png"));
					selector.setIcon(icono);
					break;
				case 5:
					contador=4;
					icono=new ImageIcon(("f05.png"));
					selector.setIcon(icono);
					break;
				case 4:
					contador=3;
					icono=new ImageIcon(("f04.png"));
					selector.setIcon(icono);
					break;
				case 3:
					contador=2;
					icono=new ImageIcon(("f03.png"));
					selector.setIcon(icono);
					break;
				case 2:
					contador=1;
					icono=new ImageIcon(("f02.png"));
					selector.setIcon(icono);
					break;
				case 1:
					contador=0;
					icono=new ImageIcon(("f01.png"));
					selector.setIcon(icono);
				break;
				case 0:
					
					icono=new ImageIcon(("f00.png"));
					selector.setIcon(icono);
					break;
				
				}
				
			}
				}
				
			
		};
		
		timer=new Timer();
		timer.scheduleAtFixedRate(tarea,30, 30);
		
		frame.add(selector);
		
		
		JFileChooser fc=new JFileChooser();
		FileNameExtensionFilter filtro2= new FileNameExtensionFilter("JavaScript Object Notation Archive", "json", "JSON"); 
		
		
		
		
		fc.setFileFilter(filtro2);
		fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
		
		ta.setFocusable(false);
	     ta.setLineWrap(true);
		 ta.setSize(300, 300);
		 ta.setLocation(200,200);
		// ta.setText(consoleOut.toString());
		 
		 JLabel consoleIcon=new JLabel("");
			consoleIcon.setBounds(666,294,25,25);
			Image im2=new ImageIcon("console.png").getImage();
			consoleIcon.setIcon(new ImageIcon(im2.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
			consoleIcon.setToolTipText("Consola de informacion");
			frame.add(consoleIcon);
		 		
		JScrollPane	scroll = new JScrollPane(ta);
		scroll.setBounds(615, 300, 400, 650);
		scroll.setBorder(new TitledBorder("Console: "));
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
		
			JLabel imagen = new JLabel("");
			imagen.setSize(157,241);
			imagen.setLocation(740,60);
			Image im=new ImageIcon("Yog.png").getImage();
			imagen.setIcon(new ImageIcon( im.getScaledInstance(157, 241, Image.SCALE_SMOOTH)));
			imagen.setToolTipText("Ir a la pagina oficial de las olimpiadas");
			frame.add(imagen);
		//consola.add(scroll);
		frame.add(scroll);
		
		JComboBox<String> combo=new JComboBox<String>();
		combo.setVisible(false);
		
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
		limpiar.setVisible(false);
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
		
		
		System.out.println(Main.Cuantos(atletas, "ciclismo"));
	   JScrollPane pane=new JScrollPane (table);
	  
	  // table.setDefaultRenderer(Object.class, render);
	   
	
	
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
		
		imagen.addMouseListener(new MouseAdapter() 
		{

			public void mouseReleased(MouseEvent e)
			{
				String uri="https://www.buenosaires2018.com/results/es/all-sports/atletas.htm?lng=es";
				try 
				{
					Desktop.getDesktop().browse(java.net.URI.create(uri));
				} catch (IOException e1)
				{
			
					e1.printStackTrace();
				}
			}
			
			public void mouseEntered(MouseEvent e)
			{
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1)
				{
				
					e1.printStackTrace();
				}
				imagen.setLocation(741, 60);
				imagen.setBorder(BorderFactory.createLineBorder(new Color(58,76,186)));
			}
			
			public void  mouseExited(MouseEvent e)
			{
				imagen.setLocation(740, 60);
				imagen.setBorder(null);
			}

		});
	   
	   
	   
		table.setAutoscrolls(true);
		double r=8400/3098;
		System.out.println(r);
		
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
		
		selector.addMouseListener(new MouseAdapter() 
		{

			public void mouseEntered(MouseEvent e)
			{
				dir=1;
			}
			public void mouseExited(MouseEvent e)
			{
				dir=0;
			}
			public void mouseReleased(MouseEvent e)
			{
				int seleccion= fc.showOpenDialog(selector);
				  
				  if (seleccion==JFileChooser.APPROVE_OPTION) 
				  {
					  File fichero=fc.getSelectedFile();
					 
					  jsonFile = fichero.getAbsolutePath();
					  
					  
						 String json=save.cargar(jsonFile);
							
							Gson gson=new Gson();
						
							 atletas= gson.fromJson(json, Atleta[].class );
							
					int i=0;
					
					
					objetos=new Object[atletas.length][5];
					for (Atleta a: atletas)
					{
						
						int ub=i+1;
						String ubi=ub+"";
						objetos [i][0]=ubi ;objetos[i][1] =a.getName();objetos[i][2] =a.getGenre();objetos[i][3] =a.getSport();objetos[i][4] =a.getNacionality();
						i++;
					}
					
					
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
					   frame.add(pane);
					   selector.setVisible(false);
					   combo.setVisible(true);
					   text.setVisible(true);
					   limpiar.setVisible(true);
					   timer.cancel();
				  }
			}
			
		});
		
				
	    
	}
}
