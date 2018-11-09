package yog;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.google.gson.Gson;
import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interfaz {

	private JFrame frame;
	BlueForegroundCell render=new BlueForegroundCell();

	SavesManager save=new SavesManager();
	private JTextArea ta = new JTextArea("",33,42);
	String jsonFile="";
	Object[][] objetos=new Object[5][5];
	Atleta[] atletas=new Atleta[0];
	JTable table=new JTable();
	
	//Recursos para las animaciones
	static int contador=0;
	int dir=0;
	Timer timer;
	TimerTask tarea;
	
	static int contador2=0;
	int dir2=0;
	Timer timer2;
	TimerTask tarea2;
	
	boolean activo=true;
	boolean  archivojson=true;
	String tipoArchivo="json";
	
	StringBuilder consoleOut=new StringBuilder();
	boolean adminRights=true;
	
	
	//Logica del solver
	Solver solver=null;
	
	String[] paises={"Femenino","Masculino","Atletismo","Badminton","Baloncesto3x3",
"Balonmano de playa","Boxeo","Breaking","Ciclismo","Ecuestre","Escalada deportiva","Esgrima","Futsal",
"Gimnasia acrobatica","Gimnasia artistica","Gimnasia ritmica","Gimnasia Trampolin","Gimnasia trampolin","Golf","Hockey 5",
"Judo","Karate","Levantamientodepesas","Lucha","Natacion","Patinaje de velocidad sobre ruedas",
"Pentatlonmoderno","Piraguismo","Remo","Rugby siete","Saltos","Taekwondo","Tenis","Tenis de mesa","Tiro con arco",
"Tirodeportivo","Triatlon","Vela","Voley-playa","Afganistan","Albania","Alemania","Andorra","Angola","Antigua y Barbuda",
"Arabia Saudi","Argelia","Argentina","Armenia","Aruba","Australia","Austria","Azerbaiyan","Bahamas","Bahrein","Bangladesh",
"Barbados","Belgica","Belice","Benin","Bermudas","Bhutan","Bielorrusia","Bolivia","Bosnia","Botsuana","Brasil","Brunei","Bulgaria","Burkina Faso",
"Burundi","Cabo Verde","Camboya","Camerun","Canada","Chad","Chile","China Taipei","Chipre","Colombia","Comoras","Congo","Costa de marfil",
"CostaRica","Croacia","Cuba","Dinamarca","Djibouti","Dominica","Ecuador","Egipto","El salvador","Emiratos arabes unidos", 
"Eritrea","Eslovaquia","Eslovenia","Espania","Estados Federados de micronesia","Estados Unidos","Estonia","Eswatini",
"Etiopia","Ex republica Yugoslava","Federacion Rusa","Filipinas","Finlandia","Fiyi","Francia","Gabon","Gambia","Georgia","Ghana",
"Gran Bretana","Granada","Grecia","Guam","Guatemala","Guinea","Guineaecuatorial","Guinea-Bissau","Guyana","Haiti","Honduras",
"Hong Kong","Hungria","India","Indonesia","Iraq","Irlanda","Islandia","Islas Caiman","Islas Cook","Islas Marshall","Islas Salomon","Islas Virgenes Britanicas",
"Islas Virgenes Estado Unidenses","Israel","Italia","Jamaica","Japon","Jordania","Kazajstan","Kenia","Kirguistan","Kiribati","Kosovo",
"Kuwait","Lesotho","Letonia","Libano","Liberia","Libia","Liechtenstein","Lituania","Luxemburgo","Madagascar","Malasia","Malawi","Maldivas","Mali",
"Malta","Marruecos","Mauricio","Mauritania","Mexico","Monaco","Mongolia","Montenegro","Mozambique","Myanmar","Namibia","Nauru","Nepal","Nicaragua","Niger","Nigeria",
"Noruega","Nueva Zelanda","Oman","PaisesBajos","Pakistan","Palaos","Palestina","Panama","Papua-Nueva Guinea","Paraguay","Peru","Polonia","Portugal","PuertoRico","Qatar","Republica Arabe Siria","Republica Centroafricana",
"Republica Checa","Republica de Corea","Republica de Moldavia","Republica de Timor Oriental","Republica del Congo","Republica de Lao","Republica Dominicana",
"Republica de Iran","Republica de China","Republica democratica de Corea","Republica de Tanzania","Ruanda","Rumania","Samoa","Samoa Americana","San Cristobal Y nieves","San Marino","San Vicentey las Granadinas",
"Santa Lucia","Santo Tome y principe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Somalia","Sri Lanka","Sudafrica",
"Sudan","SudandelSur","Suecia","Suiza","Surinam","Tailandia","Tayikistan","Togo","Tonga","Trinidad y Tobago","Tunez","Turkmenistan","Turquia","Tuvalu",
"Ucrania","Uganda","Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","Yemen","Zambia","Zimbabue",};
	

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
				
				ret2+=ch;
			}
		}
		
		String ret3=Character.toUpperCase(ret2.charAt(0))+ret2.substring(1, ret2.length());
		return ret3;
	}

	public void addConsoleLine(String message) 
	{
		consoleOut.append(message);
		consoleOut.append("\n");
		ta.setText(consoleOut.toString());
		
	}
	private void initialize()
	{
		
		frame = new JFrame();
		frame.setBounds(300, 0, 1050, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JFileChooser fc=new JFileChooser();
		FileNameExtensionFilter filtro2= new FileNameExtensionFilter("JavaScript Object Notation File", "json", "JSON"); 
		fc.setFileFilter(filtro2);
		
		JTextField text=new JTextField();
		text.setVisible(false);
		text.setBorder(new TitledBorder("Ingrese Busqueda:"));
		
		
		frame.add(text);
		frame.setTitle("Gestor de asignacion de habitaciones");
	Image titleIcon=new ImageIcon("Title.png").getImage();
		
	
	
	try 
	{
		save.testAdminrigths();
	} catch (IOException e2) {
		
		JOptionPane.showMessageDialog(frame, "Este programa necesita derechos de administrador para ejecutar ciertas acciones.\n Podrian ocasionarse errores de lectura/escritura", "Administrator Rigths Required", JOptionPane.WARNING_MESSAGE);
		adminRights=false;
	}
	
		
		
	
	frame.setIconImage(titleIcon);
		
		JMenuBar menubar=new JMenuBar();
		menubar.setBounds(0,0,1040,20);
		menubar.setBorder(new LineBorder(Color.black));
		JMenu menu= new JMenu("Archivo");
		menu.setMnemonic('a');
		
		Image icon=new ImageIcon("close.png").getImage();
		JMenuItem itemCerrar=new JMenuItem("Cerrar",new ImageIcon(icon.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		itemCerrar.setMnemonic('c');
		
		Image icon2=new ImageIcon("minimize.png").getImage();
		JMenuItem itemMin=new JMenuItem("Minimizar",new ImageIcon(icon2.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		itemMin.setMnemonic('m');
		
		Image icon3=new ImageIcon("maximize.png").getImage();
		JMenuItem itemMax=new JMenuItem("Maximizar",new ImageIcon(icon3.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		
		JMenu menu2=new JMenu("Herramientas");
		menu2.setMnemonic('h');
		JCheckBoxMenuItem jcheckBox= new JCheckBoxMenuItem("Desactivar Animaciones");
		jcheckBox.setMnemonic('d');
	
		JMenuItem modelo=new JMenuItem("Modelo de excel...");
		menu2.add(modelo);
		
		
			
		
		JMenu menu3=new JMenu("Tipo de archivo");
		menu3.setMnemonic('t');
		
		Image icon4=new ImageIcon("excel.png").getImage();
		JRadioButtonMenuItem excel= new JRadioButtonMenuItem("Excel",new ImageIcon(icon4.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		excel.setMnemonic('e');
		
		Image icon5=new ImageIcon("json.png").getImage();
		JRadioButtonMenuItem json= new JRadioButtonMenuItem("Json",new ImageIcon(icon5.getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
		json.setMnemonic('j');
		
		JMenu check=new JMenu("Derechos de administrador");
		
		JButton asignar=new JButton ("Asignar...");
		asignar.setEnabled(false);
		asignar.setVisible(false);
		asignar.setBounds(620,290,100,20);
		frame.add(asignar);
		
		if (adminRights)
		{
			Image yes=new ImageIcon("Yes.png").getImage();
			ImageIcon yesicon=new ImageIcon(yes.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
			check.setIcon(yesicon);
		}
			
		else 
		{
			Image no=new ImageIcon("No.png").getImage();
			ImageIcon noicon=new ImageIcon(no.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
			check.setIcon(noicon);
		}
			
		
		json.setSelected(true);
		json.setEnabled(false);
		
		
		
		addConsoleLine("Bienvenido al sistema de asiganacion de habitaciones: ");
		json.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if (json.isSelected()) 
				{
					
					tipoArchivo="json";
					
					System.out.println(tipoArchivo);
					archivojson=true;
					excel.setSelected(false);
					 
					 FileNameExtensionFilter filtro2= new FileNameExtensionFilter("JavaScript Object Notation File", "json", "JSON"); 
					 fc.resetChoosableFileFilters();
					 addConsoleLine("El tipo de archivo seleccionado es Json");
					fc.setFileFilter(filtro2);
					json.setEnabled(false);
					excel.setEnabled(true);
					
				}
				
			}
			
		});
		
		excel.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if (excel.isSelected()) 
				{
					tipoArchivo="xlsx";
					archivojson=false;
					json.setSelected(false);
					 FileNameExtensionFilter filtro2= new FileNameExtensionFilter("Excel file", "xlsx", "XLSX","xls","XLS"); 
					 fc.resetChoosableFileFilters();
					 addConsoleLine("El tipo de archivo seleccionado es Excel");
						 fc.setFileFilter(filtro2);
						 
						 excel.setEnabled(false);
						 json.setEnabled(true);
					
				}
				
			}
			
		});
		
		
		menu3.add(json);
		menu3.add(excel);
		
		menu.add(itemMax);
		menu.add(itemMin);
		itemMax.setEnabled(false);
		menu.add(itemCerrar);
		menu2.add(jcheckBox);
		menubar.add(menu);
		menubar.add(menu2);
		menubar.add(menu3);
		
		menubar.add(Box.createHorizontalGlue());
		menubar.add(check);
		
	
		
		frame.getContentPane().add(menubar);

		JLabel selector=new JLabel();
		selector.setBounds(10, 30, 600, 955);;
		
		itemCerrar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			
				frame.dispose();
				
			}
			
		});
		
		itemMax.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				
			}
			
		});
		
		itemMin.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
			
				frame.setExtendedState(1);
				
			}
			
		});
		
		tarea=new TimerTask()
		{

			@Override
			public void run() 
			{
				
				
					
				ImageIcon icono;
				String im= (archivojson) ? "f" : "e";
				
				
				if (activo)
				{
				if (dir==1) 
				{
					
					switch(contador) 
				{
					
				case 0:
					contador=1;
					icono=new ImageIcon(im+"00.png");
					selector.setIcon(icono);
				case 1:
					contador=2;
					icono=new ImageIcon(im+"01.png");
					selector.setIcon(icono);
				case 2:
					contador=3;
					icono=new ImageIcon(im+"02.png");
					selector.setIcon(icono);
					break;
				case 3:
					contador=4;
					icono=new ImageIcon(im+"03.png");
					selector.setIcon(icono);
					break;
				case 4:
					contador=5;
					icono=new ImageIcon(im+"04.png");
					selector.setIcon(icono);
					break;
				case 5:
					contador=6;
					icono=new ImageIcon((im+"05.png"));
					selector.setIcon(icono);
					break;
				case 6:
					contador=7;
					icono=new ImageIcon((im+"06.png"));
					selector.setIcon(icono);
					break;
				case 7:
					contador=8;
					icono=new ImageIcon((im+"07.png"));
					selector.setIcon(icono);
					break;
				case 8:
					contador=9;
					icono=new ImageIcon((im+"08.png"));
					selector.setIcon(icono);
					break;
				case 9:
					contador=10;
					icono=new ImageIcon((im+"09.png"));
					selector.setIcon(icono);
					break;
				case 10:
					contador=11;
					icono=new ImageIcon((im+"10.png"));
					selector.setIcon(icono);
					break;
				case 11:
					
					icono=new ImageIcon((im+"11.png"));
					selector.setIcon(icono);
					break;
				
				}
				
			}
				
				else  
				{
					switch(contador) 
				{
				case 11:
					contador=10;
					icono=new ImageIcon(im+"11.png");
					selector.setIcon(icono);
				case 10:
					contador=9;
					icono=new ImageIcon((im+"10.png"));
					selector.setIcon(icono);
				case 9:
					contador=8;
					icono=new ImageIcon((im+"09.png"));
					selector.setIcon(icono);
					break;
				case 8:
					contador=7;
					icono=new ImageIcon((im+"08.png"));
					selector.setIcon(icono);
					break;
				case 7:
					contador=6;
					icono=new ImageIcon(im+"07.png");
					selector.setIcon(icono);
					break;
				case 6:
					contador=5;
					icono=new ImageIcon((im+"06.png"));
					selector.setIcon(icono);
					break;
				case 5:
					contador=4;
					icono=new ImageIcon((im+"05.png"));
					selector.setIcon(icono);
					break;
				case 4:
					contador=3;
					icono=new ImageIcon((im+"04.png"));
					selector.setIcon(icono);
					break;
				case 3:
					contador=2;
					icono=new ImageIcon((im+"03.png"));
					selector.setIcon(icono);
					break;
				case 2:
					contador=1;
					icono=new ImageIcon((im+"02.png"));
					selector.setIcon(icono);
					break;
				case 1:
					contador=0;
					icono=new ImageIcon((im+"01.png"));
					selector.setIcon(icono);
				break;
				case 0:
					
					icono=new ImageIcon((im+"00.png"));
					selector.setIcon(icono);
					break;
				
				}
				
			}	
				}
				else 
				{
					
					icono=new ImageIcon((im+"11.png"));
					selector.setIcon(icono);
					
					
					
				}
				
				}
				
			
		};
		
		
		timer=new Timer();
		timer.scheduleAtFixedRate(tarea,40, 40);
		
		
		JLabel imagen =new JLabel("",SwingConstants.CENTER)
		{

			private static final long serialVersionUID = 1L;
			public Point getToolTipLocation(MouseEvent event)
		      {
				
		        return new Point(60, 200);
		        
		      }

		 };
		    
		 
		 
		ToolTipManager.sharedInstance().setInitialDelay(0);
		imagen.setSize(157,241);
		imagen.setLocation(740,80);
		Image im=new ImageIcon("Yog.png").getImage();
		imagen.setIcon(new ImageIcon( im.getScaledInstance(140, 220, Image.SCALE_SMOOTH)));
		imagen.setToolTipText("Ir a la pagina oficial de las olimpiadas");
		frame.add(imagen);
		
		TextAutoCompleter textAutoCompleter = new TextAutoCompleter(text);
		for (String e: paises) 
		{
			textAutoCompleter.addItem(e);
		}
		
		
		tarea2=new TimerTask()
		{

			@Override
			public void run() 
			{
				
				Image im=new ImageIcon("Yog.png").getImage();;
				
				
				if (activo)
				{
					if (dir2==1) 
					{
					
						switch(contador2) 
					{
						
					case 0:
						contador2=1;
					
						imagen.setIcon(new ImageIcon( im.getScaledInstance(140, 220, Image.SCALE_SMOOTH)));
						break;
							
					case 1:
						contador2=2;
						
						imagen.setIcon(new ImageIcon( im.getScaledInstance(142, 222, Image.SCALE_SMOOTH)));
						break;
						
					case 2:
					
						contador2=3;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(144, 224, Image.SCALE_SMOOTH)));
						break;
					case 3:
						
						contador2=4;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(146, 226, Image.SCALE_SMOOTH)));
						break;
					case 4:
						
						contador2=5;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(148, 228, Image.SCALE_SMOOTH)));
						break;
					case 5:
						
						contador2=6;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(150, 230, Image.SCALE_SMOOTH)));
						break;
					case 6:
						
						contador2=7;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(152, 232, Image.SCALE_SMOOTH)));
						break;
					case 7:
						
						contador2=8;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(154, 234, Image.SCALE_SMOOTH)));
						break;
					case 8:
						
						contador2=9;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(156, 236, Image.SCALE_SMOOTH)));
						break;
					case 9:
						im=new ImageIcon("Yog.png").getImage();
						
						imagen.setIcon(new ImageIcon( im.getScaledInstance(158, 240, Image.SCALE_SMOOTH)));
						break;
					
						
					
					}
					
				}
					
					else  
					{
						
						switch(contador2) 
						{
						
					case 9:
						contador2=8;
						
						imagen.setIcon(new ImageIcon( im.getScaledInstance(158, 240, Image.SCALE_SMOOTH)));
						break;
							
					case 8:
						contador2=7;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(156, 236, Image.SCALE_SMOOTH)));
						break;
						
					case 7:
						contador2=6;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(154, 234, Image.SCALE_SMOOTH)));
						break;
					case 6:
						contador2=5;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(152, 232, Image.SCALE_SMOOTH)));
						break;
					case 5:
						contador2=4;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(150, 230, Image.SCALE_SMOOTH)));
						break;
					case 4:
						contador2=3;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(148, 228, Image.SCALE_SMOOTH)));
						break;
					case 3:
						contador2=2;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(146, 226, Image.SCALE_SMOOTH)));
						break;
					case 2:
						contador2=1;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(144, 224, Image.SCALE_SMOOTH)));
						break;
					case 1:
						contador2=0;
						imagen.setIcon(new ImageIcon( im.getScaledInstance(142, 222, Image.SCALE_SMOOTH)));
						break;
					case 0:
						
						imagen.setIcon(new ImageIcon( im.getScaledInstance(140, 220, Image.SCALE_SMOOTH)));
						break;
					
					
					}
					
				}
				}
				
				}
				
			
		};
		
		
		timer2=new Timer();
		timer2.scheduleAtFixedRate(tarea2,20, 20);
		
		frame.add(selector);
		
		
	

		fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
		
		ta.setFocusable(false);
	     ta.setLineWrap(true);
		 ta.setSize(300, 300);
		 ta.setLocation(200,200);
		// ta.setText(consoleOut.toString());
		 
		 JLabel consoleIcon=new JLabel("");
			consoleIcon.setBounds(666,314,25,25);
			Image im2=new ImageIcon("console.png").getImage();
			consoleIcon.setIcon(new ImageIcon(im2.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
			consoleIcon.setToolTipText("Consola de informacion");
			frame.add(consoleIcon);
		 		
		JScrollPane	scroll = new JScrollPane(ta);
		scroll.setBounds(615, 320, 400, 650);
		scroll.setBorder(new TitledBorder("Console: "));
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
		
			
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
		
		
		combo.setBounds(780,25,120,50);
		combo.setBorder(new TitledBorder("Seleccione filtro:"));
		text.setBounds(620,30,150,40);	   
		frame.add(combo);
		
	
		
		JButton limpiar=new JButton("Clear");
		limpiar.setVisible(false);
		limpiar.setBounds(905,50,65,20);
		frame.add(limpiar);
	
		
		
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) 
		 {
			
		 }
			
			
		JDialog dialog=new JDialog(frame,"Informacion de salida" );
		dialog.setBounds(frame.getBounds());
		
		JTextArea info=new JTextArea();
		info.setFocusable(false);
	     info.setLineWrap(true);
		 info.setSize(frame.getSize());
		 info.setLocation(0,0);
		 
			JScrollPane	scroll2 = new JScrollPane(info);
			scroll2.setAutoscrolls(false);
			scroll2.setBounds(0, 0, 1000, 950);
			scroll2.setBorder(new TitledBorder("Informacion Final: "));
				scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		dialog.add(scroll2);
		
		
		dialog.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				
				int i=JOptionPane.showConfirmDialog(dialog, "Desea guardar el infome final en informe.log");
				
				if (i==0) 
				{
					try 
					{
						save.guardarLog(solver.log());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					int seleccion= fc.showOpenDialog(selector);
					 if (seleccion==JFileChooser.APPROVE_OPTION) 
					  {
						 File destino=fc.getSelectedFile();
						 File fichero=new File("informe.log");
						 
						 
						 Path origenPath = FileSystems.getDefault().getPath(fichero.getAbsolutePath());
						 
						
					       Path destinoPath = FileSystems.getDefault().getPath(destino.getAbsolutePath()+"\\informe.log");
					       
					      System.out.println(origenPath.toString());
						 System.out.println(destinoPath.toString());
					        
						 boolean continuar=true;
						 
						    if (fichero.exists()) 
						   {
						        try
						        {
						        	
						        		
						        		Files.move(origenPath,destinoPath, StandardCopyOption.REPLACE_EXISTING);
						        	
									
									
								} catch (IOException e1) 
						        {
									continuar=false;
									JOptionPane.showMessageDialog(selector, "Se requieren permisos de administrador");
								}finally
								{
									if (continuar)
									{
										JOptionPane.showMessageDialog(selector, "Se copio informe.log en: "+destinoPath.toString());
									}
									
									
								}
						        
						        
						    } 
						    
					  }
				}
				frame.setVisible(true);
				dialog.setVisible(false);
			}
		
			public void windowClosed(WindowEvent e)
			{
				frame.setVisible(true);
				dialog.setVisible(false);
				
				try 
				{
					save.guardarLog(solver.toJSon());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int seleccion= fc.showOpenDialog(selector);
				 if (seleccion==JFileChooser.APPROVE_OPTION) 
				  {
					 File destino=fc.getSelectedFile();
					 File fichero=new File("output.json");
					 
					 
					 Path origenPath = FileSystems.getDefault().getPath(fichero.getAbsolutePath());
					 
					
				       Path destinoPath = FileSystems.getDefault().getPath(destino.getAbsolutePath()+"\\output.json");
				       
				      System.out.println(origenPath.toString());
					 System.out.println(destinoPath.toString());
				        
					 boolean continuar=true;
					 
					    if (fichero.exists()) 
					   {
					        try
					        {
					        	
					        		
					        		Files.move(origenPath,destinoPath, StandardCopyOption.REPLACE_EXISTING);
					        	
								
								
							} catch (IOException e1) 
					        {
								continuar=false;
								JOptionPane.showMessageDialog(selector, "Se requieren permisos de administrador");
							}finally
							{
								if (continuar)
								{
									JOptionPane.showMessageDialog(selector, "Se copio output.json en: "+destinoPath.toString());
								}
								
								
							}
					        
					        
					    } 
					    
				  }
			}
		});
			
	   text.addKeyListener(new KeyAdapter() 
	   {
 			public void keyReleased(KeyEvent e)
 			{
// 					Pattern pat= Pattern.compile(text.getText());
//					
//					Matcher mat=pat.matcher(paises[0]);
//					
//					//popup.setVisible(true);
//					popup=null;
//					popup=new JPopupMenu();
//					popup.setLocation(1000,100);
//					
//					for (int i=0;i<paises.length;i++)
//					{
//						mat=pat.matcher(paises[i]);
//						
//						if (mat.find())
//						{
//							System.out.println (paises[i]);
//							
//							
//							popup.add((paises[i]));
//							
//						}
//					}
//					popup.updateUI();
//					popup.setVisible(true);
 				
					
 				if (e.getKeyCode()==KeyEvent.VK_ENTER) 
 				{
 					String value = text.getText();
 					
 					
 					
 						
 					try 
 						{
 					if (esValorDeterminado(formatear(value),atletas))
 						
 						value=formatear(value);	
 						}catch(Exception s)
 						{
 							
 						}
 						
 					
 					if (!text.getText().equals("")) 
 					{
 					
 						value=capitalizar(value);
 						
 						
 					if	(esValorDeterminado(value,atletas)) 
 					{
 							text.setText(value);
 						filtro(value,table);
 						
 						render.setInput(value);
 						table.updateUI();
 					JOptionPane.showMessageDialog(frame, "Se encontraron: "+solver.Cuantos( text.getText())+" resultados","Busqueda para: "+text.getText(), JOptionPane.INFORMATION_MESSAGE);
 					addConsoleLine("La busqueda para "+value+ " a arrojado "+solver.Cuantos( value)+ " resultados");
 					
 					
 					frame.requestFocus();
 					}
 					else 
 					{
 						render.setInput("");
 						JOptionPane.showMessageDialog(frame, "No se encontraron resultados para: '"+value+"'");
 						addConsoleLine(">>Search content Exception<<");
 						addConsoleLine( "No se encontraron resultados para: "+value+"\n");
 					    text.setText("");
 					    
 	 					
 					}
 				
 				}
 					
 	
 					
 				}
 				
 				if (text.getText().equals("")) 
 				{
 					render.setInput("");
 					filtro("",table);
 					combo.setSelectedItem("Seleccionar...");
 					
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
					
					
					JOptionPane.showMessageDialog(frame, "Se encontraron: "+solver.Cuantos( text.getText())+" resultados","Busqueda para: "+text.getText(), JOptionPane.INFORMATION_MESSAGE);
					addConsoleLine("La busqueda para '"+value+ "' a arrojado "+solver.Cuantos( value)+ " resultados");
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
					addConsoleLine("Redigigiendo a >> "+uri);
				} catch (IOException e1)
				{
			
					e1.printStackTrace();
				}
			}
			
			public void mouseEntered(MouseEvent e)
			{
			
				
				dir2=1;
				
			}
			
			public void  mouseExited(MouseEvent e)
			{
				
				
				dir2=0;
				
			}

		});
	   
	   
	   
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
				fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
				int seleccion= fc.showOpenDialog(selector);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				 boolean continuar=true;
				 
			
				  if (seleccion==JFileChooser.APPROVE_OPTION) 
				  {
					  File fichero=fc.getSelectedFile();
					  
					 
					 
					  jsonFile = fichero.getAbsolutePath();
					  String json="";

					  String extension=jsonFile.substring(jsonFile.lastIndexOf(".") +1);
					 
					  System.out.println(tipoArchivo);
					  System.out.println(extension);
					  if (!extension.equals(tipoArchivo))
					  {
						  continuar=false;
						  JOptionPane.showMessageDialog(selector, "No se admiten archivos con formato : "+ extension.toUpperCase(), "Archive's extension not Supported", JOptionPane.ERROR_MESSAGE);
						  addConsoleLine(">>Archive's extension not Supported Exception<<<");
						  addConsoleLine("No se admiten archivos con formato '"+extension.toUpperCase()+"'\n");
						  fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
					  }
					  
					  else 
					  {
						  
						  
						  if (tipoArchivo.equals("json"))
						json=save.cargar(jsonFile);   
						  if (tipoArchivo.equals("xlsx")) 
						  {
							  
						
							 
							   
							   Path origenPath = FileSystems.getDefault().getPath(fichero.getAbsolutePath());
						       Path destinoPath = FileSystems.getDefault().getPath("C:\\Ficheros-Excel\\"+fichero.getAbsoluteFile().getName());
						        
							    if (fichero.exists()) {
							        try {
							        	File folder = new File("C:\\Ficheros-Excel\\");
							        	folder.mkdirs();
										Files.copy(origenPath,destinoPath, StandardCopyOption.REPLACE_EXISTING);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(selector, "Se requieren permisos de administrador");
									}
							    } else {
							        addConsoleLine("El fichero "+fichero+" no existe en el directorio ");
							    }
							    
							     json=save.jsonFromExcel(fichero.getAbsoluteFile().getName());
							    System.out.println(json);
						  }
							  
							 
					  }
					  
					
					  
					Gson gson=new Gson();
						
					try 
					{
						 atletas= gson.fromJson(json, Atleta[].class );
						 
					}
					catch (Exception r) 
					{
						continuar=false;
						JOptionPane.showMessageDialog(selector, "Gson Error: "+r.getCause().getMessage()+" \n No se puede transformar el json en una lista de atletas", "Illegal State Exception:", JOptionPane.ERROR_MESSAGE);
						addConsoleLine(">>"+r.getCause().getMessage()+"<<");
						addConsoleLine("Error al transformar el archivo json");
						fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
					}
					
							
							try 
						{
							objetos=new Object[atletas.length][5];
							
						}
							catch (Exception r) 
						{
						continuar=false;
						  JOptionPane.showMessageDialog(selector, "No se obtuvo informacion alguna del archivo .json o se produjo un error de lectura  ", "Empty or corrupted File Exception", JOptionPane.ERROR_MESSAGE);
							addConsoleLine(">>Empty or corrupted File Exception<<");
							addConsoleLine("Error al transformar el archivo json");
						  fc.setCurrentDirectory(new File(new File(".").getAbsolutePath()));
						}
				
						if (continuar)
						{
						int i=0;
					
						solver=new Solver(atletas);
						addConsoleLine(solver.estadisticasIniciales());
						asignar.setEnabled(true);
						asignar.setVisible(true);
					
						for (Atleta a: atletas)
						{
						
						int ub=i+1;
						String ubi=ub+"";
						objetos [i][0]=ubi ;objetos[i][1] =a.getName();objetos[i][2] =a.getGenre();objetos[i][3] =a.getSport();objetos[i][4] =a.getNacionality();
						i++;
						}
					
					
					table.setBounds(10, 31, 538, 929);
					
					table.setModel(new DefaultTableModel(
						objetos,
						new String[] {
							"Nº" ,"Nombre", "Genero", "Deporte", "Nacionalidad"
						}
					));
				
					   JScrollPane pane=new JScrollPane (table);
					   table.getColumnModel().getColumn(0).setMinWidth(40);
					   table.getColumnModel().getColumn(0).setMaxWidth(40);
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
					   pane.setBounds(10, 30, 600, 955); 
					   table.setDefaultRenderer(Object.class, render);
					   frame.add(pane);
					   selector.setVisible(false);
					   combo.setVisible(true);
					   text.setVisible(true);
					   limpiar.setVisible(true);
					   timer.cancel();
				  }
						}
					
			}
			
		});
		
		
		
		
		jcheckBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if (jcheckBox.isSelected())
				{
					activo=false;
					
					addConsoleLine("Se desactivaron las animaciones");
					ImageIcon icono=new ImageIcon("f00.png");
					selector.setIcon(icono);
					
					Image im=new ImageIcon("Yog.png").getImage();
					imagen.setIcon(new ImageIcon( im.getScaledInstance(140, 220, Image.SCALE_SMOOTH)));
					contador=0;
					contador2=0;
					dir=0;
					dir2=0;
					
					
				}
				else
				{
					activo=true;
					addConsoleLine("Se reaunudaron las animaciones");
				}
				
			}
			
		});
		modelo.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int seleccion= fc.showOpenDialog(selector);
				 if (seleccion==JFileChooser.APPROVE_OPTION) 
				  {
					 File destino=fc.getSelectedFile();
					 File fichero=new File("Modelo Atletas.xlsx");
					 
					 
					 Path origenPath = FileSystems.getDefault().getPath(fichero.getAbsolutePath());
					 
					
				       Path destinoPath = FileSystems.getDefault().getPath(destino.getAbsolutePath()+"\\Modelo Atletas.xlsx");
				       
				      System.out.println(origenPath.toString());
					 System.out.println(destinoPath.toString());
				        
					 boolean continuar=true;
					 
					    if (fichero.exists()) 
					   {
					        try
					        {
					        	int eleccion=JOptionPane.showConfirmDialog(selector, "Desea Guardar el archivo Modelo Atletas.xlsx en el directorio:" +
										destinoPath.toString());
					        	if (eleccion==0)
					        	{
					        		
					        		Files.copy(origenPath,destinoPath, StandardCopyOption.REPLACE_EXISTING);
					        	}
					        	else
					        	{
					        		continuar=false;
					        		JOptionPane.showMessageDialog(selector,"Se cancelo el copiado por el usuario");	
					        	}
								
								
							} catch (IOException e1) 
					        {
								continuar=false;
								JOptionPane.showMessageDialog(selector, "Se requieren permisos de administrador");
							}finally
							{
								if (continuar)
								{
									JOptionPane.showMessageDialog(selector, "Se copio el modelo de excel en "+destinoPath.toString());
								}
								
								
							}
					        
					        
					    } 
					    
				  }
					 
					 
				  }
					  
				
			
			
		});
		asignar.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				asignar.setEnabled(false);
				solver.resolvedor();
				
				addConsoleLine(solver.estadisticasFinales());
				
				
				int i=1;
				for (Departamento de: solver.listaDepartamentos) 
				{
					System.out.println("Departamento Nº"+i+"\n"+de);
					i++;
				}
				
				System.out.println("LA LISTA DE DEPARTAMENTOS ES: " + solver.listaDepartamentos.size());
				
			for (Atleta a : solver.listaAtletas) 
			{
				System.out.println(a);
					
			}
			System.out.println("LA LISTA DE ATLETAS ES :"+solver.listaAtletas.size());
			
			info.setText(solver.log());
			
			
			frame.setVisible(false);
			
			
			dialog.setVisible(true);
			
			
			}
			
			
			
		});
		
				
	    
	}
}
