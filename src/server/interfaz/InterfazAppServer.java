package server.interfaz;

import client.interfaz.InterfazAppClient;
import java.sql.SQLException;
import server.controlador.Controlador;

public class InterfazAppServer {

    /* Relaciones */
    private Controlador ctrl;

    /* Constructor */
    public InterfazAppServer(Controlador ctrl) {
        this.ctrl = ctrl;
        VServer vs = new VServer();
        vs.setVisible(true);
        System.out.println("Server.....");
    }

    public static void main(String[] args) throws SQLException {
        new InterfazAppServer(new Controlador());
    }

}
