package consola;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class InterfazRegistrarReservaEmpleado extends JFrame implements ActionListener{
	
	private ArrayList<JTextField> listaJTextFields ;
	private ArrayList<JLabel> listaLabels = new ArrayList<JLabel>();
	private JButton botonRegistrar ;
	private PanelEmpleado interfazEmpleado;
	
	public InterfazRegistrarReservaEmpleado(PanelEmpleado panelEmpleado) {
		this.interfazEmpleado = panelEmpleado;
		setUndecorated(true); 
		JPanel panelInterfaz =new JPanel();
		panelInterfaz.setLayout(new GridBagLayout());
		interfazEmpleado.agregarLabel("Categoría", listaLabels);
		interfazEmpleado.agregarLabel("Sede a Recoger", listaLabels);
		interfazEmpleado.agregarLabel("Fecha Inicial", listaLabels);
		interfazEmpleado.agregarLabel("Fecha Final", listaLabels);
		interfazEmpleado.agregarLabel("Sede a devolver", listaLabels);
		interfazEmpleado.agregarLabel("Nombre Cliente", listaLabels);
		GridBagConstraints c  = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.insets = new Insets(10, 10, 10, 10);
		panelInterfaz.add(new JLabel("Ingrese la informacion del vehiculo a registrar"),c);
		listaJTextFields = panelEmpleado.crearJtextFieldsParaLabels(listaLabels, panelInterfaz,3,1);
		
		panelInterfaz.setBackground(new Color(79,193,223));
		//Boton Registrar
		botonRegistrar  = new JButton("Reservar");
		botonRegistrar.setBackground(new Color(	188,192,193));
		botonRegistrar.setSize(getPreferredSize());
		botonRegistrar.addActionListener(this);
		c.gridx =1;
		c.gridy =7;
		c.gridwidth= 3;
		c.weightx = 2;
		
		panelInterfaz.add(botonRegistrar,c);
		
		add(panelInterfaz);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		setSize(760,230);
		setResizable(false);
		
		 
		
		
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
}

