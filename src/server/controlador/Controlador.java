package server.controlador;

import java.sql.SQLException;
import server.mundo.Mensaje;
import server.mundo.SocketServer;

public class Controlador
{   
 // Atributos
	
	
 // Relaciones 	
	private SocketServer SocketServer;
	
 // Constructor	
	public Controlador() throws SQLException
	{ SocketServer = new SocketServer(); 
	}
	
 // Recibe las referencias de los objetos a controlar en la interfaz	
	public void conectar()
	{ 
	}

 // --------------------------------------------------------------	
 // Implementacion de los requerimientos de usuario.	
 // --------------------------------------------------------------
	public void socket(Mensaje miObjeto)
	{ SocketServer.socket(miObjeto);		
	}
	
}