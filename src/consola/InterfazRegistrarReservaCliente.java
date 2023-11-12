package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//
import java.awt.Component;


import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarReservaCliente extends JFrame implements ActionListener{
	//private ArrayList<JTextField> listaJTextFields ;
	//private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelCliente panelCliente;
	private PanelAuxLista panelLista;
	private PanelLogin panelSur;
	
	public InterfazRegistrarReservaCliente(PanelCliente panelCliente) {
		//setSize( 750, 600 );
		setLayout(new BorderLayout());
		setBackground(new Color(79,193,223));
		panelLista = new PanelAuxLista(this);
		JPanel panelTitulo = new JPanel();
		panelTitulo.add(new JLabel("Ingrese la informacion de la reserva:"));
		panelTitulo.setBackground(new Color(79,193,223));
		panelTitulo.setBorder(new EmptyBorder(0, 0, 0, 0));
		//panelLista.setBorder(new EmptyBorder(0, 0, 0, 0));
		LineBorder bordeCenter = new LineBorder(new Color(188, 192, 193)); // Cambia a tu color deseado
        panelLista.setBorder(bordeCenter);
		add(panelTitulo, BorderLayout.NORTH);
		add(panelLista, BorderLayout.CENTER);
		//add(panelLista, BorderLayout.SOUTH);
		
	    
		//panelSur = new PanelOpciones(this);
	    //add(panelSur, BorderLayout.SOUTH);
	    
		//this.panelCliente = interfazCliente;
		//setUndecorated(true); 
		//JPanel panelInterfaz = new JPanel();
		//panelInterfaz.add(new JLabel("Ingrese la informacion de la reserva:"));
		
		
	    //Boton
        botonRegistrar = new JButton("Reservar");
        botonRegistrar.setBackground(new Color(188, 192, 193));
        Dimension botonDimension = new Dimension(100, 40);
        botonRegistrar.setPreferredSize(botonDimension);
        botonRegistrar.addActionListener(this);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(79, 193, 223));
        panelBoton.add(botonRegistrar);
     // Establecer márgenes e insets para reducir el espacio no deseado
        panelBoton.setBorder(new EmptyBorder(0, 0, 0, 0));
        add(panelBoton, BorderLayout.SOUTH);
				
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        setSize(710, 270);
        setResizable(false);
	}
		 
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}