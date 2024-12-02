/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.mundo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Sentencias {

    private MySql sen;

    public Sentencias() throws SQLException {
        this.sen = new MySql();
        sen.connect();
    }

    public void insertUsuario(String nickname, String IP) throws SQLException {
        sen.update("INSERT INTO usuarios ( Nickname, IP, Estado ) VALUES ( '" + nickname + "', '" + IP + "', '" + 1 + "')");
    }

    public void updateIp(String IP, String nickname) throws SQLException {
        sen.update("UPDATE usuarios SET IP = '" + IP + "' WHERE Nickname = '" + nickname + "'");
    }

    public void updateEstado(int estado, String nickname) throws SQLException {
        sen.update("UPDATE usuarios SET Estado = '" + estado + "' WHERE Nickname = '" + nickname + "'");
    }

    public void insertMensaje(String contenido, String usuarioEmisor, String usuarioReceptor) throws SQLException {
        sen.update("INSERT INTO mensaje ( ID_Mensaje, Contenido, NicknameEmisor, NicknameReceptor ) VALUES ( " + null + ", '" + contenido + "', '" + usuarioEmisor + "', '" + usuarioReceptor + "')");
    }

    public void insertContacto(String NuevoUsuario) throws SQLException {
        ArrayList<String> con = devolverContactos();
        for (int i = 0; i < con.size(); i++) {
            sen.update("INSERT INTO contactos ( NicknameEmisor, NicknameReceptor  ) VALUES ( '" + con.get(i) + "', '" + NuevoUsuario + "')");
            if (!con.get(i).equalsIgnoreCase(NuevoUsuario)) {
                sen.update("INSERT INTO contactos ( NicknameEmisor, NicknameReceptor ) VALUES ( '" + NuevoUsuario + "', '" + con.get(i) + "')");
            }
        }
    }

    public String obtenerConversacion(String nicknameE, String nicknameR) throws SQLException {
        ResultSet rst = sen.select("SELECT NicknameEmisor, Contenido FROM mensaje WHERE ( NicknameEmisor = '" + nicknameE + "' AND NicknameReceptor = '" + nicknameR + "' ) OR ( NicknameEmisor = '" + nicknameR + "' AND NicknameReceptor = '" + nicknameE + "' )");
        String salida = "";
        while (rst.next()) {
            String emisor = rst.getString("NicknameEmisor");
            String contenido = rst.getString("Contenido");
            salida += emisor + " : \n" + contenido + "\n";
        }
        return salida;
    }

    public ArrayList<String> devolverContactos() throws SQLException {
        ArrayList<String> lista = new ArrayList();
        ResultSet rst = sen.select("SELECT Nickname FROM usuarios");
        while (rst.next()) {
            String user = rst.getString(1);
            lista.add(user);
        }
        return lista;
    }

    public boolean usuarioExistente(String nickname) throws SQLException {
        ResultSet rst = sen.select("SELECT COUNT(*) FROM usuarios WHERE nickname = '" + nickname + "'");
        if (rst.next()) {
            int rowCount = rst.getInt(1);
            return rowCount > 0;
        }
        return false;
    }

    public boolean estado(String nickname) throws SQLException {
        ResultSet rst = sen.select("SELECT Estado FROM usuarios WHERE Nickname = '" + nickname + "'");
        if (rst.next()) {
            int est = rst.getInt("Estado");
            return est == 1;
        }
        return false;
    }

    public String getIpR(String nickname) throws SQLException {
        ResultSet rst = sen.select("SELECT IP FROM usuarios WHERE Nickname = '" + nickname + "'");
        if (rst.next()) {
            String ip = rst.getString("IP");
            return ip;
        }
        return null;
    }

    //    public void select(String sql) throws SQLException {
//        ResultSet rst = sen.select(sql);
//        while (rst.next()) {
//            System.out.println(rst.getString("ID_CodTipo") + " :: " + rst.getString("nombreTipo"));
//        }
//    }
}
