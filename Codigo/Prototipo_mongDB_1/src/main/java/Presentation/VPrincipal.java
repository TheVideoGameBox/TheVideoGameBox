package Presentation;

import Logic.Person.SAPersonImp;
import Logic.Person.TPerson;
import Presentation.Person.VAddPerson;
import Presentation.Person.VBajaPrueba;
import Presentation.Person.VBuscarPrueba;
import Presentation.Person.VEditarPrueba;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class VPrincipal extends JFrame{
	
	private static int logged;
	
	public VPrincipal() {
		super();
		logged = -1;
		initGUI();
	}
	public VPrincipal(int log) {
		super();
		log = logged;
		initGUI();
	}
	
	public void initGUI() {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono_prueba.png")).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		
		JPanel topPanel = createTopPanel();
		JPanel midPanel = createMidPanel();
		
		mainpanel.add(topPanel, BorderLayout.NORTH);
		mainpanel.add(midPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(true);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1150, 100));
		topPanel.setMaximumSize(new Dimension(1150, 100));
		topPanel.setMinimumSize(new Dimension(1150, 100));
		//topPanel.setBackground(new Color(0, 113, 188));
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		/*
		JLabel usuario = new JLabel();
		usuario.setForeground(new Color(64, 147, 255));
		usuario.setFont(new Font("Arial", 1, 20));
		usuario.setVisible(false);
		
		JButton logIn = new JButton("Iniciar Sesi�n");
		JButton logOut = new JButton("Cerrar Sesi�n");
		logIn.setPreferredSize(new Dimension(150, 50));
		logIn.setMaximumSize(new Dimension(150, 50));
		logIn.setMinimumSize(new Dimension(150, 50));
		logIn.setBackground(new Color(64, 147, 255));
		logIn.setForeground(Color.white);
		logIn.setFont(new Font("Leelawadee", Font.BOLD, 15));
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setFocusPainted(false);
		logIn.setToolTipText("Iniciar sesi�n");
		logIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logged = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el n�mero de usuario:", "Acceso a Aplicaci�n mediante usuario", JOptionPane.YES_NO_CANCEL_OPTION));
					logIn.setVisible(false);
					logOut.setVisible(true);
					usuario.setText("Usuario: " + logged);
					usuario.setVisible(true);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Datos introducidos no v�lidos","Error al conectarse", JOptionPane.NO_OPTION);;
				}
			}
			
		});
		
		logOut.setVisible(false);
		logOut.setPreferredSize(new Dimension(150, 50));
		logOut.setMaximumSize(new Dimension(150, 50));
		logOut.setMinimumSize(new Dimension(150, 50));
		logOut.setBackground(new Color(64, 147, 255));
		logOut.setForeground(Color.white);
		logOut.setFont(new Font("Leelawadee", Font.BOLD, 15));
		logOut.setBorder(BorderFactory.createBevelBorder(0));
		logOut.setFocusPainted(false);
		logOut.setToolTipText("Cerrar sesi�n");
		logOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn.setVisible(true);
				logOut.setVisible(false);
				usuario.setVisible(false);
				logged = -1;
			}
			
		});
		
		JButton registro = new JButton("Registrarse");
		registro.setPreferredSize(new Dimension(150, 50));
		registro.setMaximumSize(new Dimension(150, 50));
		registro.setMinimumSize(new Dimension(150, 50));
		registro.setBackground(new Color(255, 170, 0));
		registro.setForeground(Color.white);
		registro.setFont(new Font("Leelawadee", Font.BOLD, 15));
		registro.setBorder(BorderFactory.createBevelBorder(0));
		registro.setFocusPainted(false);
		registro.setToolTipText("Registrarse");
		*/
		
		JLabel icono = new JLabel("Aqu� ir�a un icono");
		icono.setForeground(Color.white);
		icono.setFont(new Font("Arial Nova", 1, 30));
		
		/*topPanel.add(Box.createRigidArea(new Dimension(20,0)));
		topPanel.add(logIn);
		topPanel.add(logOut);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(registro);
		*/
		topPanel.add(Box.createRigidArea(new Dimension(460,0)));
		topPanel.add(icono);
		//topPanel.add(Box.createRigidArea(new Dimension(250,0)));
		//topPanel.add(usuario);
		
		return topPanel;
	}
	
	private JPanel createMidPanel() {
		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		midPanel.setPreferredSize(new Dimension(1150, 600));
		midPanel.setMaximumSize(new Dimension(1150, 600));
		midPanel.setMinimumSize(new Dimension(1150, 600));
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setOpaque(false);
		buttonContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonContainer.setMaximumSize(new Dimension(1000, 60));
		buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
		
		JButton aniadir = new JButton("A�adir prueba");
		aniadir.setPreferredSize(new Dimension(180, 80));
		aniadir.setMaximumSize(new Dimension(180, 80));
		aniadir.setMinimumSize(new Dimension(180, 80));
		aniadir.setBackground(new Color(64, 147, 255));
		aniadir.setForeground(Color.white);
		aniadir.setFont(new Font("Leelawadee", Font.BOLD, 20));
		aniadir.setBorder(BorderFactory.createBevelBorder(0));
		aniadir.setFocusPainted(false);
		aniadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAddPerson alta = new VAddPerson();
				dispose();
			}
			
		});
		
		buttonContainer.add(Box.createRigidArea(new Dimension(425,0)));
		buttonContainer.add(aniadir);
		buttonContainer.add(Box.createRigidArea(new Dimension(425,0)));
		
		midPanel.add(buttonContainer);
		midPanel.add(Box.createRigidArea(new Dimension(0,10)));

		List<TPerson> personas = new ArrayList<TPerson>();
		SAPersonImp functions = new SAPersonImp();
		personas = functions.readAll();
		midPanel.add(todasPruebaPanel(personas));
		midPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		return midPanel;
	}
	
	private JPanelRound todasPruebaPanel(List<TPerson> pruebas) {
		JPanelRound mainPanel = new JPanelRound();
		mainPanel.setBackground(new Color(64, 147, 255));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setMaximumSize(new Dimension(1000, 150));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBorder(new BorderTitle("Personas", Color.white));
		mainPanel.add(Box.createRigidArea(new Dimension(10,0)));

/*		for(TPerson p : pruebas) {
			if(p.isActivo()) {
				mainPanel.add(pruebaPanel(p));
				mainPanel.add(Box.createRigidArea(new Dimension(10,0)));
			}
		}
*/
		return mainPanel;
	}
	
	private JPanelRound2 pruebaPanel(TPerson p) {
		JPanelRound2 mainPanel = new JPanelRound2();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setMaximumSize(new Dimension(200, 130));
		mainPanel.setLayout(new BorderLayout());

		JLabel texto = new JLabel(p.getNombre());
		texto.setForeground(Color.white);
		texto.addMouseListener(new MouseListener(){

		@Override
			public void mouseClicked(MouseEvent e) {

				VBuscarPrueba buscar = new VBuscarPrueba(p);
				dispose();

		}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}

		});

		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);

		JButton editar = new JButton("Editar");
		editar.setBackground(new Color(62, 168, 18));
		editar.setForeground(Color.white);
		editar.setBorder(BorderFactory.createBevelBorder(0));
		editar.setPreferredSize(new Dimension(80,35));
		editar.setMaximumSize(new Dimension(80,35));
		editar.setMinimumSize(new Dimension(80,35));
		editar.setFocusPainted(false);
		editar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VEditarPrueba editar = new VEditarPrueba(p);
				dispose();
			}

		});

		JButton eliminar = new JButton("Eliminar");
		eliminar.setBackground(new Color(161, 35, 18));
		eliminar.setForeground(Color.white);
		eliminar.setBorder(BorderFactory.createBevelBorder(0));
		eliminar.setPreferredSize(new Dimension(80,35));
		eliminar.setMaximumSize(new Dimension(80,35));
		eliminar.setMinimumSize(new Dimension(80,35));
		eliminar.setFocusPainted(false);
		eliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VBajaPrueba b = new VBajaPrueba(p);
				dispose();
			}

		});

		buttonContainer.add(editar);
		buttonContainer.add(eliminar);

		mainPanel.add(texto, BorderLayout.NORTH);
		mainPanel.add(buttonContainer, BorderLayout.SOUTH);

		return mainPanel;
	}
	
}