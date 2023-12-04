package logica;


import java.util.Date;

public class MedioDePago {
 private int numero;
 private String tipoDeTarjeta;
 private Date fechaVencimiento;
 
 	public MedioDePago(int numero ,String tipoDeTarjeta ,Date fechaVencimiento) {
 	this.numero = numero;
 	this.tipoDeTarjeta = tipoDeTarjeta;
 	this.fechaVencimiento = fechaVencimiento;
     }
 	
 	
 	public int getNumeroTarjeta() {
 		return numero;
 	}
 	
 }
