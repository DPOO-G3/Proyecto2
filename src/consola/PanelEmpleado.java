package consola;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.AdministradorLocal;
import logica.Empleado;


public class PanelEmpleado extends JPanel implements ActionListener{
	
	private InterfazPrincipal interfazPrincipal;
	private JButton botonRegistrarEmpleado;
	private JButton BtonRegistrarCliente;
	private JButton btonEliminar;
	private Image imagen;	
	private JComboBox<Integer> usuariosBox; 
	
	public PanelEmpleado(InterfazPrincipal interfazPrincipal,Empleado empleado ) {
		
		this.interfazPrincipal = interfazPrincipal;
		cargarImagen();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Image
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		imagen = imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel lJLabel = new JLabel(new ImageIcon(imagen)) ;
		lJLabel.setSize(50,50);
		add(lJLabel, c);
		c = new GridBagConstraints();

		// JLabel "Administrador Local"
		c.gridx = 0;
		c.gridy = 3; 
		JLabel labelAdminLocal = new JLabel("Empleado");
		labelAdminLocal.setFont(new Font(labelAdminLocal.getFont().getName(),Font.ITALIC,25));
		add(labelAdminLocal, c);
		
		JPanel PanelInfo = new JPanel();
		PanelInfo.setLayout(new GridLayout(3,2));
		JLabel labelNombre = new JLabel("Nombre");
		Font fontnombre = labelNombre.getFont();
		labelNombre.setFont(new Font(fontnombre.getName(), Font.PLAIN, 25));
		PanelInfo.add(labelNombre);

		// JLabel con el nombre del administrador
		JLabel labelNombreValor = new JLabel(empleado.getNombre());
		Font fontNombre = labelNombreValor.getFont();
		labelNombreValor.setFont(new Font(fontNombre.getName(), Font.ITALIC, 25));
		PanelInfo.add(labelNombreValor);

		// JLabel "Usuario"
		JLabel labelUsuario = new JLabel("Usuario:");
		Font fontu = labelUsuario.getFont();
		labelUsuario.setFont(new Font(fontu.getName(),Font.PLAIN,25));
		PanelInfo.add(labelUsuario);

		// JLabel con el nombre de usuario del administrador
		JLabel labelUsuarioValor = new JLabel(empleado.getUsuario());
		Font fontUsuario = labelUsuarioValor.getFont();
		labelUsuarioValor.setFont(new Font(fontUsuario.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelUsuarioValor);

		// JLabel "Sede"
		JLabel labelSede = new JLabel("Sede:");
		labelSede.setFont(new Font(labelSede.getFont().getName(),Font.PLAIN,25));
		PanelInfo.add(labelSede);

		// JLabel con el nombre de la sede del administrador
		JLabel labelSedeValor = new JLabel(empleado.getSede());
		Font fontSede = labelSedeValor.getFont();
		labelSedeValor.setFont(new Font(fontSede.getName(), Font.ITALIC, 25)); 
		PanelInfo.add(labelSedeValor);
		// JLabel "Nombre"
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(1, 10, 1, 1);
		add(PanelInfo,c);
		
		//Panel buscar por id vehiculo
		
		JPanel panelBuscarCarro = new JPanel();
		panelBuscarCarro.setLayout(new GridLayout(1,2,1,0));
		JLabel labelIngresar = new JLabel("Ingrese ID Vehiculo: ");
		JTextField cajaText= new JTextField();
		panelBuscarCarro.add(labelIngresar);
		panelBuscarCarro.add(cajaText);
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=5;
		c.insets= new Insets(5, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		add(panelBuscarCarro,c);
		
		//Panel para eliminar Usuarios
		JPanel panelEliminarU = new JPanel();
		panelEliminarU.setLayout(new GridLayout(1,2,10,0));
		ArrayList<Integer> listaCarros = interfazPrincipal.listaCarros();
		Integer[] arrayCarros = listaCarros.toArray(new Integer[0]);

		usuariosBox = new JComboBox<Integer>(arrayCarros);
		
		btonEliminar = new JButton("Eliminar");
		btonEliminar.setBackground(Color.red);
		panelEliminarU.add(usuariosBox);
		panelEliminarU.add(btonEliminar);
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=6;
		c.insets= new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		add(panelEliminarU,c);
		
		//Botones Inferiores
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(1,2,50,0));
		botonRegistrarEmpleado = new JButton("Registrar Empleado");
		BtonRegistrarCliente = new JButton("Registrar Cliente");
		panelInferior.add(botonRegistrarEmpleado);
		panelInferior.add(BtonRegistrarCliente);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy =7;
		c.insets = new Insets(20,1, 1, 1);
		c.gridwidth = 3;
		add(panelInferior,c);
		
	}
	
	private void cargarImagen() {
		try {
			imagen  = ImageIO.read(new File("./data/Imagenes/usuario.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}


