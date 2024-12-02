package server.mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import server.interfaz.Panel1;

public class SocketServer implements Runnable {

    private Sentencias sentencias;
    private static String ipUser, ipServer;

    /* Constructor */
    public SocketServer() throws SQLException {
        Thread treadListener = new Thread(this);
        treadListener.start();
        sentencias = new Sentencias();
        ipUser = null;
        ipServer = null;
        leerTxt();
    }

    /* Server:Data >> Socket >> Client */
    public static void socket(Mensaje miObjeto) {
        try {
            Socket server = new Socket(ipUser, 5050); // portSend 5050
            ObjectOutputStream outBuffer = new ObjectOutputStream(server.getOutputStream());
            outBuffer.writeObject(miObjeto);
            server.close();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Server : socket() : UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Server : socket() : IOException: " + e.getMessage());
        }
    }

    @Override
    /* Server: Listen */
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream inObjectBuffer;
        try {
            serverSocket = new ServerSocket(5000); // portListen 5000
            while (true) {
                socket = serverSocket.accept();
                inObjectBuffer = new ObjectInputStream(socket.getInputStream());
                try {
                    Mensaje miObjeto = (Mensaje) inObjectBuffer.readObject();
                    switch (miObjeto.getType()) {
                        case 0:
                            ipUser = miObjeto.getIp();
                            socket(new Mensaje(0, miObjeto.getNickE(), null, null, ipUser, sentencias.devolverContactos()));
                            break;
                        case 1:
                            ipUser = miObjeto.getIp();
                            socket(new Mensaje(1, miObjeto.getNickE(), null, null, ipUser, sentencias.devolverContactos()));
                            break;
                        case 2:
                            ipUser = miObjeto.getIp();
                            if (sentencias.usuarioExistente(miObjeto.getNickE())) {
                                sentencias.updateEstado(1, miObjeto.getNickE());
                                sentencias.updateIp(ipUser, miObjeto.getNickE());
                            } else {
                                sentencias.insertUsuario(miObjeto.getNickE(), ipUser);
                                sentencias.insertContacto(miObjeto.getNickE());
                            }
                            break;
                        case 3:
                            Panel1.gettxt().append(miObjeto.getNickE() + " >> " + miObjeto.getMensaje() + " >> " + miObjeto.getNickR() + "\n");
                            if (sentencias.estado(miObjeto.getNickR())) {
                                ipUser = sentencias.getIpR(miObjeto.getNickR());
                                miObjeto.setIp(ipUser);
                                socket(miObjeto);
                            }
                            sentencias.insertMensaje(miObjeto.getMensaje(), miObjeto.getNickE(), miObjeto.getNickR());
                            break;
                        case 4:
                            ipUser = miObjeto.getIp();
                            sentencias.updateEstado(0, miObjeto.getNickE());
                            break;
                        case 5:
                            ipUser = miObjeto.getIp();
                            socket(new Mensaje(5, miObjeto.getNickE(), miObjeto.getNickR(), sentencias.obtenerConversacion(miObjeto.getNickE(),miObjeto.getNickR()), ipUser, null));
                            break;
                    }
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Server = " + e.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                socket.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Server : run (): IOException: " + e.getMessage());
        }
    }
    public void leerTxt() {
        String nombreArchivo = "src\\server\\configserver\\PruebaServer.txt";
        ArrayList<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.length() > 0) {
                    lista.add(linea);
                }
            }
            ipUser = lista.get(0);
            ipServer = lista.get(1);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}