package consola;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import logica.AdministradorLocal;
import logica.Cliente;
import logica.Empleado;
import logica.Sede;
import logica.CategoriaVehiculo;
import logica.Cliente;
import logica.ControladorReserva;
import logica.AdministradorLocal;
import logica.Cliente;
import logica.Empleado;
import logica.Sede;
import logica.UsuarioGenerico;
import logica.Vehiculo;

public class EmpresaAlquilerVehiculos {
  private ControladorReserva controllerEmpresa = new ControladorReserva();
  private ArrayList<UsuarioGenerico> listaUsuarioGenericos = new ArrayList<UsuarioGenerico>();
  private ArrayList<Cliente> listaClientes;
  private ArrayList<Sede> listaSedes;
  private ArrayList<Empleado> listaEmpleados;
  private ArrayList<CategoriaVehiculo> categoriaVehiculo = new ArrayList<CategoriaVehiculo>();
  
  private ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();

  private void ejecutarPrograma() {
	 
;	 System.out.println("Bienvenido a la empresa");
	 String usuario=input("Usuario");
	 String contrasenia=input("Contraseña");
	 String tipoUsuario =login(usuario, contrasenia);
	 Cliente clienteLogin = null;
	 if(tipoUsuario.equals("")) {
		 System.out.println("Usuario o contraseña incorrecta");
		 while (tipoUsuario.equals("")) {
			 System.out.println("Ingrese nuevamente su usuario y contraseña");
			 usuario=input("Usuario");
			 contrasenia=input("Contraseña");
			 tipoUsuario =login(usuario, contrasenia);
		 }
		 
		
	 } else {
		System.out.println("Entrando al sistema.....");
		if(tipoUsuario.equals("Cliente"))
			
		{ 
			for (Cliente cliente : listaClientes) {
				if(cliente.getUsuario().equals(usuario)&& cliente.getContraseña().equals(contrasenia)) 
					clienteLogin = cliente;
					cliente.crearReserva();
					break;
				
			}
			programaCliente(clienteLogin);
		}
		
	}
	 
 }
 private String login(String usuario,String contrasenia) {
	 
	 for (UsuarioGenerico usuarioGenerico : listaUsuarioGenericos) {
		String userFor = usuarioGenerico.getUsuario();
		String passwordFor = usuarioGenerico.getContraseña();
		if(usuario.equals(userFor) && passwordFor.equals(contrasenia)) {
			String tipoUsario= usuarioGenerico.getTipoUsuario();
			return tipoUsario;
		}
		
	}return "";
 }
 
 private void programaCliente (Cliente clienteLogin) {
	 MenuCliente();
	 int option = Integer.parseInt(input("Ingrese la opcion que desea"));
	 
	 if(option == 1) {
		 String categoria=input("Ingrese el tipo de vehiculo que desea ");
		 String nombreSede = input("Ingrese la sede en la que desea recogerlo");
		 String fechaI= input("Ingrese la fecha de inicio formato: yyyy-MM-dd HH:mm");
		 String fechaF= input("Ingrese la fecha de inicio formato: yyyy-MM-dd HH:mm");
		 try {
		Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, nombreSede, fechaI, fechaF, listaSedes);
		String sedeDevolver = input("Ingrese la sede que desea devolverlo");
		String conductorAdicional = input("Desea agregar otro conductor Si(1) No(0) ");
		boolean aditional = false;
		if(conductorAdicional.equals("1"))
			aditional=true;
		controllerEmpresa.crearReservaCliente(clienteLogin,vehiculo,listaSedes,sedeDevolver,aditional);
		 } catch (ParseException e) {
			e.printStackTrace();
		}
		 
	 }
	 }
 private void MenuCliente() {
	 System.out.println("1.Reservar vehiculo");
	 System.out.println("2.Devolver vehiculo");
	 
 }
	 
 
 
 
 
 
 
 public static void main(String[] args) {
	 EmpresaAlquilerVehiculos programa = new EmpresaAlquilerVehiculos();
	 ControllerCarga control = new ControllerCarga();
	 programa.cargaDatos(control);
	 programa.ejecutarPrograma();
}
 
 
 
 
 
 
 
 private void cargaDatos(ControllerCarga control) {
	  
	 ArrayList<Cliente> LCliente = control.cargarClientes(null);
	 ArrayList<Empleado> LEmpleado = control.cargarEmpleados(null);
	 ArrayList<Vehiculo> LVehiculos = control.cargarVehiculos(null);
	 ArrayList<AdministradorLocal> LAdmiLocal = control.cargarAdministradorLocal(null);
	 ArrayList<Sede> LSedes = control.cargarSedes(LEmpleado, LVehiculos, LAdmiLocal, null);
 
 
     }
 
 
 
 
 
 
public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
