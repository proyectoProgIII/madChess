package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objetos.*;


public class VentanaJuegoTesteos extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JPanel panelControles;
	
	protected JPanel panelChat;
	protected JTextArea areaChat;
	protected JTextField textfieldChat;
	protected JTextField textfieldUsuario;
	protected JLabel labelChat;
	protected JLabel labelEscribe;
	protected JButton botonEnviar;
	protected JPanel panelControlChat;
	protected JPanel panelDatosChat;
	protected JPanel panelUsuario;
	protected JPanel panelMensaje;
	protected JLabel labelUsuario;
	
	
	protected JPanel panelMovimentos;
	
	protected TableroTests tablero = new TableroTests();
	
	public VentanaJuegoTesteos() {
	    int[] ventanaSize = {1000, 800};
	    this.setSize(ventanaSize[0], ventanaSize[1]);
	    this.setLocationRelativeTo(null);

	    // Panel principal con GridBagLayout
	    JPanel fondo = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    // Paneles
	    JPanel panelJuego = new JPanel();
	    JPanel panelControles = new JPanel(new GridLayout(2,1));

	    // Colores
	    fondo.setBackground(Color.red);
	    panelJuego.setBackground(Color.blue);
	    panelControles.setBackground(Color.green);

	    // PanelJuego
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 1.0; //En panel del juego va a ocupar todo lo que pueda
	    gbc.weighty = 1.0; // Para que ocupen todo lo que puedan en vertical
	    gbc.fill = GridBagConstraints.BOTH;
	    fondo.add(panelJuego, gbc);

	    // PanelControles
	    gbc.gridx = 1;
	    gbc.weightx = 0.5; // El espacio que va a ocupar el panelControles
	    fondo.add(panelControles, gbc);

	    
	    
	    // Configura el panelJuego con BoxLayout en la dirección vertical
	    panelJuego.setLayout(new BoxLayout(panelJuego, BoxLayout.Y_AXIS));

	    // Agrega algunos elementos a panelJuego (por ejemplo, botones o etiquetas)


	    
	    panelJuego.add(new JLabel("Nombre1"));
	    panelJuego.add(tablero);
	    panelJuego.add(new JLabel("Nombre2"));
	
	    this.add(fondo);
	    
	    System.out.println(panelJuego.getSize());
	    
	    // panel controles, movimiento y chat
	    
	    //panel chat
	    panelMovimentos = new JPanel();
	    
	    panelChat = new JPanel( new BorderLayout());
	    panelControlChat = new JPanel(new GridLayout(2,1));
	    
	    labelChat = new JLabel("CHAT");
	    areaChat = new JTextArea(5,2);
	    textfieldChat = new JTextField(0);
	    JScrollPane scrollChat = new JScrollPane(areaChat);
	    labelUsuario = new JLabel("Usuario:");
	    labelEscribe = new JLabel("Mensaje:");
	    panelDatosChat = new JPanel( new GridLayout(1,2));
	    textfieldUsuario = new JTextField(0);
	    botonEnviar = new JButton("Enviar");
	    botonEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = textfieldChat.getText();
				String usuario = textfieldUsuario.getText();
                areaChat.append(usuario + ": " + texto + "\n"); // Agrega el texto al JTextArea
                textfieldChat.setText(""); // Limpia el JTextField después de agregar el texto
                textfieldUsuario.setText("");
				
			}
		});
	    
	    panelChat.add(labelChat, BorderLayout.NORTH);
	    panelChat.add(scrollChat, BorderLayout.CENTER);
	    
	    panelUsuario = new JPanel(new BorderLayout());
	    panelMensaje = new JPanel(new BorderLayout());
	    
	    panelUsuario.add(labelUsuario, BorderLayout.WEST);
	    panelUsuario.add(textfieldUsuario, BorderLayout.CENTER);
	    panelMensaje.add(labelEscribe, BorderLayout.WEST);
	    panelMensaje.add(textfieldChat , BorderLayout.CENTER);
	    
	    panelDatosChat.add(panelUsuario);
	    panelDatosChat.add(panelMensaje);
	    
	    
	    
	    panelControlChat.add(panelDatosChat);
	    
	    panelControlChat.add(botonEnviar);
	    
	    panelChat.add(panelControlChat, BorderLayout.SOUTH);
	    
	    
	    // panel movimiento de las piezas
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    panelControles.add(panelMovimentos);
	    panelControles.add(panelChat);
        
        


       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

        tablero.recalcularTamanyo();
      
        //PARA VER EL TABLEOR DE ANTES CAMBIAR EL ARCHIVO MAIN.JAVA	
        
    
        
        
        
    }
	

}



