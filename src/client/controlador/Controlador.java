package client.controlador;

import client.mundo.SocketClient;
import server.mundo.Mensaje;

public class Controlador
{   
 // Atributos
	
	
 // Relaciones 	
	private SocketClient SocketClient;
	
 // Constructor	
	public Controlador()
	{ SocketClient = new SocketClient(); 
	}
	
 // Recibe las referencias de los objetos a controlar en la interfaz	
	public void conectar()
	{ 
	}

 // --------------------------------------------------------------	
 // Implementacion de los requerimientos de usuario.	
 // --------------------------------------------------------------
	public void socket(Mensaje miObjeto)
	{ SocketClient.socket(miObjeto);		
	}
	
        public String leerTxt(String opc){
            return SocketClient.leerTxt(opc);
        }
}