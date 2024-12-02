package client.interfaz;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import client.controlador.Controlador;
import server.mundo.Mensaje;
public class InterfazAppClient {

    /* Relaciones */
    private Controlador ctrl;

    public InterfazAppClient(Controlador ctrl) {
        this.ctrl = ctrl;
        LogIn login = new LogIn(ctrl);
        login.setVisible(true);
        System.out.println("Cliente.....");	
    }

    public static void main(String[] args) {
        new InterfazAppClient(new Controlador());

    }
}
