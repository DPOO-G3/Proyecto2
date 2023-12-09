package logica;

import java.util.ArrayList;
import java.util.Date;

import consola.EmpresaAlquilerVehiculos;

public  class Cliente extends UsuarioGenerico {
 
 public String nombre;
 public String nacionalidad;
 public String telefono;
 public String fechaNac;
 protected LicienciaConducion licienciaConducion;
 private  MedioDePago medioDePago;
 int numeroTarjeta =0;
 protected  ArrayList<Reserva>  reservas = new ArrayList<Reserva>(); ;
 
 public Cliente(String nombre, String nacionalidad, String telefono, String fechaNacimiento,String usuario, String contraseña, String tipoUsuario,ArrayList<Reserva> reservas,LicienciaConducion licencia)
	{
	  super(usuario, contraseña, tipoUsuario);
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.fechaNac = fechaNacimiento;
		this.reservas = reservas;
		this.licienciaConducion = licencia;
		
		
	}
 	public Cliente() {
	super("", "", "Cliente");
 	}
 
    public String getNombre() 
    {
    	return nombre;
    }

   
    public MedioDePago getMedioDePago() 
    {
	   return medioDePago;
    }

   
    public Reserva crearReserva(Vehiculo vehiculo,Date fechaInicio,Date FechaFinal,double precio30 ,double precioRestante,String sedeDevolver,String sedeRecoger) 
    {
	  Reserva reserva = new Reserva(EmpresaAlquilerVehiculos.getNumeroReservaInteger(),vehiculo.getCategoria().getNombreCategoria(),
			  fechaInicio,FechaFinal,precio30,precioRestante,
			  precio30+precioRestante,numeroTarjeta,
			  sedeRecoger,sedeDevolver,null,false,vehiculo,nombre);	
	  
	  reservas.add(reserva);
	  return reserva;
    }

  
    public  ArrayList<Reserva> getReservas() 
    {
	  return reservas;
    }
    
    
    public  int getNumeroTarjeta () 
    {
	  return numeroTarjeta;
    }

    
    public String getFechaNac()
    {
	  return fechaNac;
    }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public void setLicienciaConducion(LicienciaConducion licienciaConducion) {
		this.licienciaConducion = licienciaConducion;
	}
	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
    
}
