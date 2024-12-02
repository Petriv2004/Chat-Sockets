package client.mundo;

import client.interfaz.PanelContactos;
import client.interfaz.PanelConversacion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import server.mundo.Mensaje;

public class SocketClient implements Runnable {

    private ArrayList<String> antiguo, nuevo;
    private String ipUser, ipServer;

    /* Constructor */
    public SocketClient() {
        Thread treadListener = new Thread(this);
        treadListener.start();
        ipUser = null;
        ipServer = null;
        leerTxt("user");
        leerTxt("server");

    }

    /* Client:Data >> Socket >> Server */
    public void socket(Mensaje miObjeto) {
        try {
            Socket client = new Socket(ipServer, 5000); // portSend 5000
            ObjectOutputStream outBuffer = new ObjectOutputStream(client.getOutputStream());
            outBuffer.writeObject(miObjeto);
            client.close();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Client: socket(1) : UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Client: socket(2) : IOException: " + e.getMessage());
        }
    }

    @Override
    /* Client: Listen */
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream inObjectBuffer;

        try {
            serverSocket = new ServerSocket(5050); // portListen 5050

            while (true) {
                socket = serverSocket.accept();
                inObjectBuffer = new ObjectInputStream(socket.getInputStream());
                try {
                    Mensaje miObjeto = (Mensaje) inObjectBuffer.readObject();
                    switch (miObjeto.getType()) {
                        case 0:
                            antiguo = arrayContactos(miObjeto.getUsers());
                            llenarContactos(miObjeto.getUsers());
                            socket(new Mensaje(5, miObjeto.getNickE(), PanelContactos.getJcbCont().getSelectedItem().toString(), null, ipUser, null));
                            break;
                        case 1:
                            nuevo = arrayContactos(miObjeto.getUsers());
                            if (!antiguo.containsAll(nuevo)) {
                                llenarContactos(usuarioPuestos());
                            }
                            antiguo = nuevo;
                            break;
                        case 3:
                            if ((!(miObjeto.getNickE().equalsIgnoreCase(miObjeto.getNickR()))) && miObjeto.getNickE().equalsIgnoreCase(PanelContactos.getJcbCont().getSelectedItem().toString())) {
                                PanelConversacion.getJtxaConv().append(miObjeto.getNickE() + " : " + miObjeto.getMensaje());
                                String salida = PanelConversacion.getJtxaConv().getText() + "\n";
                                PanelConversacion.getJtxaConv().setText(salida);
                            }
                            break;
                        case 5:
                            PanelConversacion.getJtxaConv().setText(miObjeto.getMensaje());
                            break;
                    }
                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Server = " + e.getMessage());
                }
                socket.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Client run() : IOException: " + e.getMessage());
        }
    }

    public String leerTxt(String opc) {
        String nombreArchivo = "src\\client\\configclient\\PruebaClient.txt";
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
            if (opc.equals("user")) {
                return ipUser;
            } else {
                return ipServer;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<String> usuarioPuestos() {
        ArrayList<String> usuarioNuevo = new ArrayList<>();
        for (int i = 0; i < nuevo.size(); i++) {
            boolean esta = antiguo.contains(nuevo.get(i));
            if (!esta) {
                usuarioNuevo.add(nuevo.get(i));
            }
        }
        return usuarioNuevo;
    }

    private void llenarContactos(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            PanelContactos.getJcbCont().addItem(lista.get(i));
        }
    }

    private ArrayList<String> arrayContactos(ArrayList<String> lista) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            items.add(lista.get(i));
        }
        return items;
    }
}
