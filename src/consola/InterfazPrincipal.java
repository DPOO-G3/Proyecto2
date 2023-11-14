
package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logica.AdministradorLocal;
import logica.Cliente;
import logica.Empleado;
import logica.Vehiculo;


public class InterfazPrincipal extends JFrame {
	private PanelLogin panelLogin;
	private EmpresaAlquilerVehiculos empresaAlquilerVehiculos;
	private PanelAdminGeneral panelAdminGeneral;
	private InterfazRegistrarVehiculo interfazRegistrarVehiculo;
	private PanelAdminLocal panelAdminLocal;
	private PanelCliente panelCliente;
	private PanelEmpleado panelEmpleado;

	

	public InterfazPrincipal(EmpresaAlquilerVehiculos empresaAlquilerVehiculos) {
		this.empresaAlquilerVehiculos = empresaAlquilerVehiculos;
		panelLogin = new PanelLogin(empresaAlquilerVehiculos,this);
		
		
		
		add(panelLogin);
		setSize(new Dimension(650,550));
		setResizable(false);
		
			}
	
	public void interfazAdminGeneral() {
		panelAdminGeneral = new PanelAdminGeneral(this);
		
		JFrame  JframeAdminGeneral = new JFrame();
		JframeAdminGeneral.setLayout( new GridLayout(1,1) );
		JframeAdminGeneral.add(panelAdminGeneral,BorderLayout.CENTER);
		JframeAdminGeneral.setVisible(true);
		JframeAdminGeneral.add(panelAdminGeneral);
		JframeAdminGeneral.pack();
		JframeAdminGeneral.setLocationRelativeTo(this);
		
		
	}
	public void eliminarVehiculoInventario(int ID)
	{
		empresaAlquilerVehiculos.darDeBajaVehiculoAdmin(ID);
	}	
	
	public void buscarAutoporId(int ID) {
		Vehiculo vehiculo = empresaAlquilerVehiculos.buscarAutoPorId(ID);
		panelAdminGeneral.actualizarAuto(vehiculo);
	}
	public void interfazRegistrarVehiculoAdmin() {
		interfazRegistrarVehiculo = new InterfazRegistrarVehiculo(this);
		interfazRegistrarVehiculo.setVisible(true);
	}
	public void agregarLabel(String nombre,ArrayList<JLabel> listaLabels) {
		JLabel label = new JLabel(nombre);
        listaLabels.add(label);
	}
	public ArrayList<JTextField> crearJtextFieldsParaLabels(ArrayList<JLabel> listaLabels,JPanel panel, int primeraColumna,int inicioDesde0) {
		ArrayList<JTextField> listaJTextFields = new ArrayList<JTextField>();
		for ( int i=0;i<listaLabels.size();i++) {
			GridBagConstraints c;
			Border borde = BorderFactory.createLineBorder(Color.black, 1);

	       
	        
			if(i<primeraColumna) {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
					textField.setBorder(borde);
				 listaJTextFields.add(textField);
				 textField.setPreferredSize(new Dimension(250, 30));
				 textField.setEditable(true);
//				
				 c.gridy=i+inicioDesde0;
				 c.gridx=0;
				 panel.add(listaLabels.get(i),c);
				 c.gridx=1;
				 panel.add(textField,c);
				
				
				
			}	else {
				c = new GridBagConstraints();
				JTextField textField = new JTextField();
				textField.setBorder(borde);
				listaJTextFields.add(textField);
				textField.setPreferredSize(new Dimension(250, 30));
				textField.setEditable(true);
				c.gridx=3;
				c.gridy=inicioDesde0+i-primeraColumna;
				panel.add(textField,c);
				c.gridx=2;
				panel.add(listaLabels.get(i),c);
			}
			
		}
		return listaJTextFields;
	}
	public void RegistrarVehiculoSistema(String nombreSedeString
			 ,String modelo ,int capacidad,
			 String placa, String color,String tipoTransmision,String categoriaVehiculo ,String rutaarchivo,int idVehiculo) {
		empresaAlquilerVehiculos.modificarVehiculoAdministradorGeneral(nombreSedeString, modelo, capacidad, placa, color, tipoTransmision, categoriaVehiculo, rutaarchivo, idVehiculo);;
	}
	
	public void interfazAdminLocal(String usuario , String contransenia) {
		AdministradorLocal administradorLocal = empresaAlquilerVehiculos.buscarAdministradorLocalPorLogin(usuario, contransenia);
		panelAdminLocal = new PanelAdminLocal(this,administradorLocal);
		JFrame  JframeAdminLocal = new JFrame();
		JframeAdminLocal.setLocationRelativeTo(null);
		
		JframeAdminLocal.setVisible(true);
		JframeAdminLocal.add(panelAdminLocal);
		JframeAdminLocal.setResizable(false);
		JframeAdminLocal.setSize(600,350);
		
		
	}
	
	public void interfazEmpleado(String usuario , String contransenia) {
		Empleado empleado = empresaAlquilerVehiculos.buscarEmpleadoPorLogin(usuario, contransenia);
		panelEmpleado = new PanelEmpleado(this,empleado);
		JFrame  JframeAdminLocal = new JFrame();
		JframeAdminLocal.setLocationRelativeTo(null);
		
		JframeAdminLocal.setVisible(true);
		JframeAdminLocal.add(panelEmpleado);
		JframeAdminLocal.setResizable(false);
		JframeAdminLocal.setSize(600,350);
		
		
	}
	
	public void interfazCliente(String usuario , String contransenia) {
		Cliente cliente = empresaAlquilerVehiculos.buscarClientePorLogin(usuario, contransenia);
		panelCliente = new PanelCliente(this,cliente.getNombre(),cliente.getUsuario(),cliente.getFechaNac(),cliente.getContraseña());
		JFrame  JframeAdminLocal = new JFrame();
		JframeAdminLocal.setLocationRelativeTo(null);
		
		JframeAdminLocal.setVisible(true);
		JframeAdminLocal.add(panelCliente);
		JframeAdminLocal.setResizable(false);
		JframeAdminLocal.setSize(600,350);
		
		
	}  
	public ArrayList<String> listaUsuariosSistema(){
		return empresaAlquilerVehiculos.crearListaUsuario();
	}

	public void PantallaReserva() {
		interfazRegistrarVehiculo = new InterfazRegistrarVehiculo(this);
		interfazRegistrarVehiculo.setVisible(true);
		
	}

	public ArrayList<Integer> listaCarros() {
		return empresaAlquilerVehiculos.crearListaCarros();
	}
	
	
	public int buscarClienteYCrearReservaCliente(String categoria, String sedeRecoger, String fechaInicial, String fechaFinal, String sedeDevolver, String nombreCliente)//Llenar con parametros mateo);
	{
		
		Cliente cliente = empresaAlquilerVehiculos.buscarClienteSistema(nombreCliente);
		if(cliente == null) {
			return 0;
		}else {
		String quienRealiza =  "Empleado";
	    realizarReservaCliente(categoria,sedeRecoger, fechaInicial, fechaFinal, sedeDevolver, cliente.getUsuario(),cliente.getContraseña(),3 ,quienRealiza);
		return 1; }
	}
	
	public void realizarReservaCliente(String categoria, String sedeR, String fechaA, String fechaF, String sedeD, String usuario, String contrasenia,int reservaOAlquiler, String quienRealiza )
	{
		//0 si es reserva, 1 si es alquiler ( El tipo, ultimo parametro)
		
		
		try {
			empresaAlquilerVehiculos.CrearReserva(categoria, sedeR, fechaA, fechaF, sedeD, usuario, contrasenia,reservaOAlquiler, quienRealiza);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int DevolverCarro(Integer idVehiculoSeleccionado, Empleado empleado) {
		int hecha = empresaAlquilerVehiculos.ProgramaEmpleadoDevolucion(idVehiculoSeleccionado,empleado);
		return hecha;
	}

	public int RecibirCarroCliente(Empleado empleado, Integer idVehiculoSeleccionado) {
		int hecho = empresaAlquilerVehiculos.ProgramaEmpleadoRecogerCliente(empleado, idVehiculoSeleccionado);
		return hecho;
	}

	public void registrarClienteNeuvo(String nombre, String nacionalidad, String telefono, String fechaNac, String paisExp, String usuario, String contraseña, int nLicencia, String fechaVencLicen) throws ParseException {
		empresaAlquilerVehiculos.crearUsuario(nombre,nacionalidad,telefono,fechaNac,paisExp,usuario, contraseña, nLicencia,fechaVencLicen);
		
	}

	public int registraEmpleadoNuevo(String nombre, String sede, String usuario, String contraseña) {
		// TODO Auto-generated method stub
		int comple = empresaAlquilerVehiculos.agregarEmpleado(nombre, sede, usuario, contraseña);
		
		return comple;
	}

	public void SacarFechasDeSede(String idSede) {
	empresaAlquilerVehiculos.sacarFechaSede(idSede);
		
	}

	

	
}