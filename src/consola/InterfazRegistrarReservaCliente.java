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
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//
import java.awt.Component;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarReservaCliente extends JFrame implements ActionListener{
	private JButton botonRegistrar ;
	private PanelAuxLista panelLista;
	public static LocalDate fechaActual;
	
	public InterfazRegistrarReservaCliente(int tipo) {
		//setSize( 750, 600 );
		fechaActual = LocalDate.now();
		setLayout(new BorderLayout());
		setBackground(new Color(79,193,223));
		
		//Titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.add(new JLabel("Ingrese la informacion de la reserva:"));
		panelTitulo.setBackground(new Color(79,193,223));
		panelTitulo.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(panelTitulo, BorderLayout.NORTH);
		
		//Panel Centro
		panelLista = new PanelAuxLista(tipo);
		LineBorder bordeCenter = new LineBorder(new Color(188, 192, 193));
        panelLista.setBorder(bordeCenter);
		add(panelLista, BorderLayout.CENTER);
		
	    //Boton
        botonRegistrar = new JButton("Reservar");
        botonRegistrar.setBackground(new Color(188, 192, 193));
        Dimension botonDimension = new Dimension(100, 40);
        botonRegistrar.setPreferredSize(botonDimension);
        botonRegistrar.addActionListener(this);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(79, 193, 223));
        panelBoton.add(botonRegistrar);
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