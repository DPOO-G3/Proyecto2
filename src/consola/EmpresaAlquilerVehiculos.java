package consola;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import logica.LicienciaConducion;
import logica.AdministradorGeneral;
import logica.AdministradorLocal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import logica.Cliente;
import logica.Empleado;
import logica.Reserva;
import logica.Sede;
import logica.CategoriaVehiculo;
import logica.ControladorReserva;
import logica.Seguro;

import logica.UsuarioGenerico;
import logica.Vehiculo;

public class EmpresaAlquilerVehiculos {
  private ControladorReserva controllerEmpresa = new ControladorReserva();
  private ArrayList<UsuarioGenerico> listaUsuarioGenericos;
  private ArrayList<Cliente> listaClientes ;
  private ArrayList<Sede> listaSedes;
  private ArrayList<Empleado> listaEmpleados;
  private ArrayList<CategoriaVehiculo> categoriaVehiculo;
  private AdministradorGeneral administradorGeneral;
  private ArrayList<Vehiculo> listaVehiculo ;
  private ArrayList<AdministradorLocal> listaAdministradorLocal;
  private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
  private ArrayList<Seguro> seguros = new ArrayList<Seguro>();
  public static Integer numeroReservaInteger = 0 ;
  public static Map<Date, Integer> calendario;
  
  
  
  public String ejecutarPrograma(String usuario, String contrasenia)  {
	 String tipoUsuario =login(usuario, contrasenia);
	 if(tipoUsuario.equals("")) {
		 return "No encontrado";
	 } else {
		 return tipoUsuario;
//		if(tipoUsuario.equalsIgnoreCase("Cliente")){ 
//			Cliente clienteLogin = null;
//			for (Cliente cliente : listaClientes) {
//				if(cliente.getUsuario().equals(usuario)&& cliente.getContraseña().equals(contrasenia)) {
//					clienteLogin = cliente;
//					break; }
//			}
//			programaCliente(clienteLogin);
//			
//		}else if(tipoUsuario.equalsIgnoreCase("Empleado")) {
//			Empleado empleadoLogin = null;
//			for (Empleado empleado : listaEmpleados) {
//				if(empleado.getUsuario().equals(usuario)&& empleado.getContraseña().equals(contrasenia)) 
//					empleadoLogin = empleado;
//					break;
//			}programaEmpleado(empleadoLogin);
//			
//		} else if (tipoUsuario.equalsIgnoreCase("Administrador General")) {
//			programaAdministradorGeneral(administradorGeneral);
//		}
//		
//		else if(tipoUsuario.equalsIgnoreCase("Administrador Local")) {
//			AdministradorLocal administradorLocalLogin = null;
//			for (AdministradorLocal AdmiL : listaAdministradorLocal) {
//				if(AdmiL.getUsuario().equals(usuario)&& AdmiL.getContraseña().equals(contrasenia)) 
//					administradorLocalLogin = AdmiL;
//					break;
//			}programaAdministradorLocal(administradorLocalLogin);
//		}
		
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
 
 private void programaCliente (String categoria, String sedeR,String fechaI,String fechaF,
		 String sedeD, Cliente clienteAReservar,int reservaOAlquiler) throws ParseException {
	 //MenuCliente();
	 //int option = Integer.parseInt(input("Ingrese la opcion que desea"));
	 if(reservaOAlquiler == 0) {
		 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
		 //String categoria=input("Ingrese el tipo de vehiculo que desea ");
		 //String nombreSede = input("Ingrese la sede en la que desea recogerlo");
		 //String fechaI= input("Ingrese la fecha de inicio formato: yyyy-MM-dd HH:mm");
		 //String fechaF= input("Ingrese la fecha de finalizacion formato: yyyy-MM-dd HH:mm");
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date fechaInicio =format.parse(fechaI);
		 Date fechaFinal = format.parse(fechaF);
		 try {
		Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
		//String sedeDevolver = input("Ingrese la sede que desea devolverlo");
		//mostrarSeguros();
		//int seguro =Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"));
		int seguro = 0;
		boolean conSeguro = false;
		if(seguro!=0) {
			segurosPosiciones.add(seguro);
			String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			while(masSeguro.equals("1")) {
				seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
				segurosPosiciones.add(seguro);
				masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			}
			conSeguro = true;
		}
		
		
		
		
		double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
		reservas.add(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
		numeroReservaInteger+=1;
		System.out.println("Creando la reserva... ");
		Thread.sleep(100);
		System.out.println("Su valor a pagar es"+reservas.get(reservas.size()-1).getPrecio30());
		System.out.println("Se hizo el cobro a la tarjeta del 30% del valor de la reserva");
		System.out.println("Cuando recoga el vehiculo se hara el cobro restante");
		
		 } catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
	 } 
	 else if(reservaOAlquiler == 1) {
		 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
		 Empleado empleadoSede = null;
		 //String categoria=input("Ingrese el tipo de vehiculo que desea ");
		 //String sedeR = input("Ingrese la sede en la que se encuentra");
		 //String fechaI= input("Ingrese la fecha de inicio formato: yyyy-MM-dd HH:mm"); 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date fechaInicio = new Date();
		 Date fechaFinal = format.parse(fechaF);
		 Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
		 //String sedeDevolver = input("Ingrese la sede que desea devolverlo");
		 //mostrarSeguros();
			//int seguro =Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"));
		 int seguro = 0;
		 boolean conSeguro = false;
			if(seguro!=0) {
				segurosPosiciones.add(seguro);
				String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				while(masSeguro.equals("1")) {
					seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
					segurosPosiciones.add(seguro);
					masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
				}
				conSeguro = true;
			}
		 double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
		 Reserva alquiler;
		 alquiler =(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
		 numeroReservaInteger+=1;
		 reservas.add(alquiler);
		 
		 for (Sede sedess: listaSedes) {
			 if(sedess.getNombre().equalsIgnoreCase(sedeR)) {
				empleadoSede= sedess.getEmpleados().get(0);
				break;
				 }	 
			
			 } 
		 //String conductorAdicional = input("Desea agregar otro conductor Si(1) No(0)");
		 String conductorAdicional = "0";
		 boolean aditional = conductorAdicional.equals("1");
		 double valorConductorExtra=0;
		 if(aditional) {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 int numero  = Integer.parseInt(input("Ingrese el numero de su liciencia "));
			 String paisExpedicion = input("Ingrese el pais de expedicion de la licencia");
			 Date  fechaVencimiento =  dateFormat.parse(input("Ingrese la fecha de vencimiento de su licencia en formato: dd/MM/yyyy")); 
			Double valorTotalDouble = alquiler.getVehiculo().getCategoria().getTarifario().getValorExtra2Conduc();
			valorConductorExtra = empleadoSede.agregarConductor(valorTotalDouble,alquiler,numero,paisExpedicion,fechaVencimiento);
			 }
		 empleadoSede.administarRecogidaCliente(alquiler);
		 
		 System.out.println("Su valor a pagar es"+(reservas.get(reservas.size()-1).getPrecioTotal()+valorConductorExtra));
		 System.err.println("Sus datos fueron validados ");
		 System.err.println("Pago exitoso ");
		 System.out.println("Las llaves estan encima del vidrio");
	 }
	 
	guardarycerra();
	 }
 
 private void MenuCliente() {
	 System.out.println("1.Reservar vehiculo");
	 System.out.println("2 Alquilar vehiculo");
	 System.out.println("3 Cerrar la aplicacion");
	 
 }
 private void menuEmpleado() {
	 System.out.println("1.Recoger vehiculo cliente ");
	 System.out.println("2.Devolucion del vehiculo cliente");
	 System.out.println("3 Crear reserva cliente");
	 System.out.println("4 Cerrar la aplicacion");
	 
 }
 private void menuAdministradorGeneral (){ 

	 System.out.println("1 Registrar compra de un nuevo vehiculo");
	 System.out.println("2 Dar de baja un vehiculo");
	 System.out.println("3 Cerrar la aplicacion");
 }
 
 private void menuAdministradorLocal(){ 

	 System.out.println("1 Creae Usuario a Cliente");
	 System.out.println("2 Administrar informacion de empreados");
	 System.out.println("3 Cerrar la aplicacion");
 }
 
 private void mostrarSeguros() {
	 int i =0;
	 System.out.println("0 No agregar ningun seguro ");
	 for (Seguro seguro : seguros) {
		i+=1;
		System.out.println( i + seguro.getnombre()+":"+seguro.getPrecio());
	}
 }

 private void programaAdministradorGeneral (AdministradorGeneral administradorGeneral) {
	menuAdministradorGeneral();
	 int option=4;
	if(option==1) {
		System.out.println("");
	}else if(option==2) {
		System.out.println();
	}else if(option==3) {
		guardarycerra();
	}
	
 }
 
 private void guardarycerra() {
	 guardarReservas();
 }
public void darDeBajaVehiculoAdmin(int idVehiculo){
	 
	 
	 Vehiculo vehiculoDeBajaVehiculo=null;
	 
	 for (Vehiculo vehiculo : listaVehiculo) {
		if(idVehiculo==(vehiculo.getIdVehiculo())) {
			vehiculoDeBajaVehiculo = vehiculo;
		}
	}
	administradorGeneral.darDeBajaVehiculo(listaVehiculo, vehiculoDeBajaVehiculo, vehiculoDeBajaVehiculo.getSedeActual());
	
 }
 
 
 public void modificarVehiculoAdministradorGeneral(String nombreSedeString
		 ,String modelo ,int capacidad,
		 String placa, String color,String tipoTransmision,String categoriaVehiculo ,String rutaarchivo,int idVehiculo) {
	
	 
		Sede sede = controllerEmpresa.buscarSedePorNombre(nombreSedeString, listaSedes);
		CategoriaVehiculo categoria =null;
		
		for (CategoriaVehiculo categoriaVehiculo2 : this.categoriaVehiculo) {
			if(categoriaVehiculo.equals(categoriaVehiculo2.getNombreCategoria())) {
				categoria = categoriaVehiculo2;
			}
		}
		Vehiculo vehiculo = new Vehiculo(idVehiculo, false, sede,nombreSedeString, capacidad, placa,
				                         modelo, color, tipoTransmision, categoria, null,
				                         null, false, false,rutaarchivo);
		
		administradorGeneral.registrarCompraVehiculo(vehiculo, sede,listaVehiculo);
		
 }

 public int ProgramaEmpleadoDevolucion(Integer idVehiculoSeleccionado, Empleado empleado) {
	 ArrayList<Reserva> reservasHechas = reservas;
	 Reserva reservaEncontrada = null;
	 for (Reserva reserva : reservasHechas) {
			if((idVehiculoSeleccionado.equals(reserva.getVehiculo().getIdVehiculo()) && (reserva.getVehiculo().getAlquilado() == true)) )
				reservaEncontrada = reserva; 
	
      }
	 if (reservaEncontrada == null) {
		 return 0;
	 }
	 empleado.devolucionCocheCliente(reservaEncontrada);
	 return 1;
 }
 
public int ProgramaEmpleadoRecogerCliente(Empleado empleado, Integer idVehiculoSeleccionado) {
	Reserva reservaClienteInterno = null;
	int esta67 = recogerVehiculoCliente(reservaClienteInterno, empleado, idVehiculoSeleccionado);
	return esta67;
}
 
 public void programaEmpleado(String categoria, String sedeR,String fechaI,String fechaF,
		 String sedeD, Cliente clienteAReservar,int option) throws ParseException {
	 //menuEmpleado();
	 Reserva reservaClienteInterno=null;
	 //int option = Integer.parseInt(input("Ingrese la opcion que desea"));
	
	 if(option == 1) {
		 ArrayList<Reserva> reservaClienteLogin=clienteAReservar.getReservas();
		 
	//Devolucion de Vehiculo
	 }else if(option==2) {
		 Reserva reservaClienteLogin=null;
		 Integer numeroReserva = Integer.parseInt(input("Ingrese el identificador de reserva del cliente"));
		 for (Reserva reservasCliente : reservas) {
				if(numeroReserva.equals(reservasCliente.getIdentificador())) 
					reservaClienteLogin = reservasCliente; 
	 }   
	
    // empleadoLogin.devolucionCocheCliente(reservaClienteLogin);
	// String estadoCocheString = input("El carro esta en buen estado Si(1) No(0)");
	 boolean funcional = false;
	 //if(estadoCocheString.equals("1")) {
		// funcional = true;
		// empleadoLogin.revisarEstadoVehiculo(reservaClienteLogin.getVehiculo(), funcional);
		 //System.out.println("El carro fue devuelto con exito"); 
		 //System.out.println("El carro se lavara en unos minutos");
		 
	// }else {
	//	 System.out.println("Generando cobro a la tarjeta del cliente");
	 //}
	 
	  
	 } else if (option==3) {
		 ArrayList<Integer> segurosPosiciones = new ArrayList<Integer>();
		 //String categoria=input("Ingrese el tipo de vehiculo que desea ");
		 //String nombreSede = input("Ingrese la sede en la que desea recogerlo");
		 //String fechaI= input("Ingrese la fecha de inicio formato: yyyy-MM-dd HH:mm");
		 //String fechaF= input("Ingrese la fecha de finalizacion formato: yyyy-MM-dd HH:mm");
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date fechaInicio =format.parse(fechaI);
		 Date fechaFinal = format.parse(fechaF);
		 try {
		Vehiculo vehiculo = controllerEmpresa.ReservaVehiculo(categoria, categoriaVehiculo, sedeR, fechaInicio, fechaFinal, listaSedes);
		//String sedeDevolver = input("Ingrese la sede que desea devolverlo");
		//mostrarSeguros();
		//int seguro =Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"));
		int seguro = 0;
		boolean conSeguro = false;
		if(seguro!=0) {
			segurosPosiciones.add(seguro);
			String masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			while(masSeguro.equals("1")) {
				seguro = Integer.parseInt(input("Ingrese el numero del seguro que desea agregar"))  ;
				segurosPosiciones.add(seguro);
				masSeguro = input("Desea agregar otro seguro Si(1) , No(0)");
			}
			conSeguro = true;
		}
		
		
		
		
		double valorSinSeguro= controllerEmpresa.ValorReservaSinSeguro(vehiculo,listaSedes,sedeD);
		reservas.add(controllerEmpresa.CrearReservaCliente(clienteAReservar,valorSinSeguro,administradorGeneral,conSeguro, vehiculo,sedeR,sedeD,seguros,segurosPosiciones));
		numeroReservaInteger+=1;
		System.out.println("Creando la reserva... ");
		Thread.sleep(100);
		System.out.println("Su valor a pagar es"+reservas.get(reservas.size()-1).getPrecio30());
		System.out.println("Se hizo el cobro a la tarjeta del 30% del valor de la reserva");
		System.out.println("Cuando recoga el vehiculo se hara el cobro restante");
		
		 } catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
	}
		guardarycerra();
	
 } 

 
 private int recogerVehiculoCliente(Reserva reservaClienteInterno,Empleado empleadoLogin, Integer idVehiculoSeleccionado) {
	
	 
	 for (Reserva reserva : reservas) {
			if((idVehiculoSeleccionado.equals(reserva.getVehiculo().getIdVehiculo()) && (reserva.getVehiculo().getAlquilado() == true)) )
				reservaClienteInterno = reserva; 
			
	
     }
	 if(reservaClienteInterno == null) {
		 return 0;
	 }
		 double valorAdicional = 0;
		
		 String adicional = "1";
		 if(adicional.equals("1")) {
			int numero = 123456;
			String pais = "Colombia";
			valorAdicional = reservaClienteInterno.getVehiculo().getCategoria().getTarifario().getValorExtra2Conduc();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaVencimiento;
			try {
				fechaVencimiento = formatoFecha.parse("2023-12-03");
				valorAdicional= empleadoLogin.agregarConductor(valorAdicional, reservaClienteInterno, numero, pais, fechaVencimiento);
				double tarifaTotal = reservaClienteInterno.getPrecioRestante() + valorAdicional;
				 empleadoLogin.administarRecogidaCliente(reservaClienteInterno);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 }
		 return 1;
		 }	  
 
 
 
 private void programaAdministradorLocal(AdministradorLocal administradorLocalLogin) throws ParseException {
		menuAdministradorLocal();
		int option = Integer.parseInt(input("Ingrese la opcion que desea"));
		if(option==1) {
			//crearUsuario();
		}else if(option==2) {
			int opcion2  = Integer.parseInt(input("¿Que desea hacer? 1. Agregar Empleado o 2.Eliminar Empleado\n"));
		    if (opcion2 == 1) {
		    	//agregarEmpleado();
		    }else if (opcion2 == 2) {
		    	eliminarEmpleado();
		    }
		}else if (option==3) {
			guardarycerra();
		}
	}
 public ArrayList<String> crearListaUsuario() {
	 ArrayList<String> listaUsuarios = new ArrayList<String>();
	 for (UsuarioGenerico usuarioGenerico : listaUsuarioGenericos) {
		
		listaUsuarios.add(usuarioGenerico.getUsuario());
		
	}
	 return listaUsuarios;
 }
 
 
 private void eliminarEmpleado() {
	   System.out.println("\n*******ELIMINAR EMPLEADO****************\n");
	   System.out.println("Por favor llene el formulario: \n");
	  
	   String nombre = input("Nombre: ");
	   String sede = input("Sede: ");
	   
	   for ( Sede sed : listaSedes) {
           if (sed.getNombre().equals(sede)){
        	   for ( Empleado Emple :  sed.getEmpleados()) {
                   if (Emple.getNombre().equals(nombre)){
                	   sed.getEmpleados().remove(Emple);
                   }
               }
        	   
  
           }
       }
	   
	   System.out.println("Empleado Eliminado exitosamente \n");
	   
}

public int agregarEmpleado(String nombre2, String sede2, String usuario2, String contraseña2) {
       
	   System.out.println("\n*******CREAR EMPLEADO***************\n");
	   System.out.println("Por favor llene el formulario: \n");
	   String nombre = nombre2;
	   String sede = sede2;
	   String usuario = usuario2;
	   String contraseña = contraseña2;
	   
	   Empleado worker = new Empleado(nombre, sede, usuario, contraseña, "Empleado");
	   
	   for ( Sede sed : listaSedes) {
           if (sed.getNombre().equals(sede)){
        	   sed.getEmpleados().add(worker);
           }
       }
	   
	   System.out.println("Empleado Agregado exitosamente \n");
	   
	   return 1;
	   
}

public void crearUsuario(String nombre2, String nacionalidad2, String telefono2, String fechaNac2, String paisExp, String usuario2, String contraseña2, int nLicencia, String fechaVencLicen) throws ParseException {
	 
	   
	   String nombre = nombre2;
	   String nacionalidad = nacionalidad2;
	   String telefono = telefono2;
	   String fechaNac = fechaNac2;
	   String usuario = usuario2;
	   String contraseña = contraseña2;
	   
	   int numeroLicencia = nLicencia;
	   String paisExpe = paisExp;
	   String Fecvenci = fechaVencLicen;
	   
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   Date fechau =format.parse(Fecvenci);
	   
	   LicienciaConducion Lice = new LicienciaConducion(numeroLicencia, paisExpe, fechau );
	   Cliente cliente = new Cliente(nombre, nacionalidad, telefono, fechaNac,usuario, contraseña, "Cliente", null, Lice);
	   listaClientes.add(cliente);
}

public static void main(String[] args) throws ParseException {
	 
	 EmpresaAlquilerVehiculos programa = new EmpresaAlquilerVehiculos();
	//CRear Mapa de calendarios
	 InterfazPrincipal ventana = new InterfazPrincipal(programa);
	 ventana.setLocationRelativeTo( null );
     ventana.setVisible( true );
     Map<Date, Integer> mapaFechas = generarFechasDeDosAnios(2023, 2024);
	 EmpresaAlquilerVehiculos.calendario = mapaFechas;
	 ControllerCarga control = new ControllerCarga();
	 programa.cargaDatos(control);
	 Persistencia persistencia = new Persistencia();
	 programa.cargaPersistencia(persistencia, programa);	
	 programa.cargarReservasEnCalendario(programa);
	 
	 //Imprimir el HashMap para ambos años
     System.out.println("Fechas para 2023 y 2024:");
     for (Map.Entry<Date, Integer> entry : EmpresaAlquilerVehiculos.calendario.entrySet()) {
         System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());}
	 

	 
	 
}
public Map<Date, Integer> sacarFechaSede(String idSede) {
	//Set<Date> fechas = calendario.keySet();
    
    
    //for (Date fecha : fechas) {
        // Obtiene el valor asociado a la clave :V
      
	
     //Integer valor = calendario.get(fecha);
        
    //}
    return calendario;
}
 
public void cargarReservasEnCalendario(EmpresaAlquilerVehiculos programa)
{
	for(Vehiculo i:programa.listaVehiculo) {
		//System.out.println(i.getIdVehiculo());
		//System.out.println(i.getPlaca());
		for(Map.Entry<Date, Integer> entry : EmpresaAlquilerVehiculos.calendario.entrySet())
			{
			 Date fecha = entry.getKey();
			 //System.out.println(i.getFechaInicio());
			if (i.getFechaInicio()!=null &&(fecha.compareTo(i.getFechaInicio()) >= 0  && fecha.compareTo(i.getFechaFinal()) <= 0)){
				EmpresaAlquilerVehiculos.calendario.put(fecha, entry.getValue()+1);
				//System.out.println(entry.getKey());
				}
			}
	}
}
	

public static Map<Date, Integer> generarFechasDeDosAnios(int yearInicio, int yearFin) {
    Map<Date, Integer> mapaFechas = new HashMap<>();
    //DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Generar fechas para ambos años
    for (int year = yearInicio; year <= yearFin; year++) {
        for (int i = 1; i <= LocalDate.ofYearDay(year, 1).lengthOfYear(); i++) {
            LocalDate fecha = LocalDate.ofYearDay(year, i);
            Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
            //String fechaFormateada = fecha.format(formato);
            // Usamos el día del año como valor entero
            mapaFechas.put(date, 0);
        }
    }

    return mapaFechas;
}

 public Cliente buscarClienteSistema(String nombreCliente) {
	 Cliente clienteEncontrado;
	 for (Cliente cliente2 : listaClientes) {
		if(cliente2.getNombre().equalsIgnoreCase(nombreCliente)) {
			clienteEncontrado = cliente2;
			return clienteEncontrado;
		}
	} return null;
 }
 public AdministradorLocal buscarAdministradorLocalPorLogin(String usuario,String contrasenia) {
	 AdministradorLocal administradorLocalEncontrado;
	 for (AdministradorLocal administradorLocal2 : listaAdministradorLocal) {
		if(administradorLocal2.getUsuario().equals(usuario)&& administradorLocal2.getContrasenia().equals(contrasenia)) {
			administradorLocalEncontrado = administradorLocal2;
			return administradorLocalEncontrado;
		}
	} return null;
 }
 
 public Cliente buscarClientePorLogin(String usuario,String contrasenia) {
	 Cliente clienteEncontrado;
	 for (Cliente cliente2 : listaClientes) {
		if(cliente2.getUsuario().equals(usuario)&& cliente2.getContraseña().equals(contrasenia)) {
			clienteEncontrado = cliente2;
			return clienteEncontrado;
		}
	} return null;
 }
 
 public Empleado buscarEmpleadoPorLogin(String usuario,String contrasenia) {
	 Empleado EmpleadoEncontrado;
	 for (Empleado EmpleadoLocal : listaEmpleados) {
		if(EmpleadoLocal.getUsuario().equals(usuario)&& EmpleadoLocal.getContraseña().equals(contrasenia)) {
			EmpleadoEncontrado = EmpleadoLocal;
			return EmpleadoEncontrado;
		}
		} return null;
	 }
 
 private void cargaPersistencia(Persistencia persistencia, EmpresaAlquilerVehiculos self) throws ParseException {
	//ArrayList<Cliente> listaClientesAux;
	//listaClientesAux = persistencia.cargarClientesAgregadosNuevos("./data/persistencia/clientes.txt\\");
	//listaClientes.addAll(listaClientesAux);
	
	ArrayList<Reserva> reservasAux;
	reservasAux = persistencia.cargarReservas("./data/persistencia/reservas.txt\\", self);
	reservas.addAll(reservasAux);
	numeroReservaInteger+=reservas.size();
	//System.out.println(numeroReservaInteger);
	//for( Reserva i:self.reservas)
	//{
		//System.out.println(i.getFechaInicio());
		//System.out.println(i.getFechaFinal());
		//Vehiculo e = 
		//self.controllerEmpresa.ReservaVehiculo(i.getCategoriaVehiculo(), self.categoriaVehiculo, i.getSedeNombreRecoger(), i.getFechaInicio(), i.getFechaFinal(), self.listaSedes);
		//self.listaVehiculo.add(e);
	//}
	
	
	//for(i: Cliente)
}
 
 private void cargaDatos(ControllerCarga control) throws ParseException {
	  
	 listaClientes = control.cargarClientes("./data/clientes.txt\\");
	 listaEmpleados = control.cargarEmpleados("./data/empleados.txt\\");
	 categoriaVehiculo = control.cargarCategoria("./data/tarifas.txt\\\\");
	 listaVehiculo = control.cargarVehiculos(categoriaVehiculo,"./data/vehiculos.txt\\");
	 listaAdministradorLocal = control.cargarAdministradorLocal("./data/administradorLocal.txt\\");
	 administradorGeneral = control.cargarAdministradorGeneral("./data/administradorGeneral.txt\\");
	 listaSedes = control.cargarSedes(listaEmpleados, listaVehiculo, listaAdministradorLocal, "./data/sedes.txt\\");
	 listaUsuarioGenericos = control.cargaUsuarios(listaEmpleados, listaClientes, listaAdministradorLocal, administradorGeneral);
     }
 
 public String crearTextoReservas()
 {
	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 	String linea = "";
 	for(Reserva reserva: reservas)
 	{
 		linea += reserva.getIdentificador()+";";
 		linea += reserva.getCategoriaVehiculo()+";";
 		linea += formato.format(reserva.getFechaInicio()) +";";
 		linea += formato.format(reserva.getFechaFinal())+";"; 
 		linea += reserva.getPrecio30()+";";
 		linea += reserva.getPrecioRestante()+";";
 		linea += reserva.getPrecioTotal()+";";
 		linea += reserva.getNumeroTarjeta()+";";
 		linea += reserva.getSedeNombreRecoger()+";";
 		linea += reserva.getSedeNombreDevolver()+";";
 		if (reserva.getConductorAdicional()!=null) {
 			
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getNumeroLicencia()+";";
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getPaisExpedicion()+";";
 		linea += reserva.getConductorAdicional().getLicenciaConducion().getFechaVencimiento()+";";
 		
 		}else {
 			linea+= ";;;";
 		}
 		linea += reserva.getVehiculoRecogido()+";";
 		linea += reserva.getVehiculo().getIdVehiculo()+";";
 		linea += reserva.getNombrePersona();
 		linea += "\n";
 	}
 	return linea;
 }
 public void guardarReservas() {
     try {
     	String nombreArchivo = "./data/persistencia/" + "reservas.txt";    
         BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
         writer.write(crearTextoReservas());
         writer.close();
         System.out.println("Se ha guardado el archivo de reservas en: " + nombreArchivo);
     } catch (IOException e) {
         System.err.println("Error al guardar el archivo.");
         e.printStackTrace();
     }
 } 
 public ArrayList<Vehiculo> darListaVehiculo(){
	 return listaVehiculo;
 }
 
 
 
 public static Integer getNumeroReservaInteger() {
	return numeroReservaInteger;
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
public Vehiculo buscarAutoPorId(int ID)
{
	for (Vehiculo vehiculo : listaVehiculo){
		if(vehiculo.getIdVehiculo()==ID) {
			return vehiculo;
		}
		
	}
	return null;
	}

public ArrayList<Integer> crearListaCarros() {
	
	ArrayList<Integer> listaCar = new ArrayList<Integer>();
	
	for( Vehiculo carLocal: listaVehiculo) {
		listaCar.add(carLocal.getIdVehiculo());
	}
	
	return listaCar;
	
}

  void CrearReserva(String categoria, String sedeR, String fechaI, String fechaF, String sedeD, String usuario,
		String contrasenia, int reservaOAlquiler, String quienRealiza) throws ParseException {
	// TODO Auto-generated method stub

	   Cliente clienteAReservar = buscarClientePorLogin(usuario, contrasenia);

	  if(quienRealiza.equals("Cliente")) {
	  programaCliente(categoria,sedeR,fechaI,fechaF,sedeD,clienteAReservar,reservaOAlquiler);
	  guardarycerra();
	  }else if(quienRealiza.equals("Empleado")) {
		 programaEmpleado(categoria,sedeR,fechaI,fechaF,sedeD,clienteAReservar,reservaOAlquiler);
		 guardarycerra();
	  }

}




	public void reservaV(EmpresaAlquilerVehiculos self, int id, Date fechaInicio, Date fechaFinal) throws ParseException{
		Vehiculo coche = null;
		//System.out.println(id);
		for(Vehiculo e: self.listaVehiculo)
		{if( e.getIdVehiculo()== id ){coche = e;}
		}
		
		if( coche.getFechaInicio() == null) {
			coche.setFechaInicio(fechaInicio);
						coche.setFechaFinal(fechaFinal);
					}					
		else if( fechaInicio.after(coche.getFechaFinal())||fechaFinal.before(coche.getFechaInicio())){
			
				coche.setFechaInicio(fechaInicio);
				coche.setFechaFinal(fechaFinal);}					
	}
	
}
