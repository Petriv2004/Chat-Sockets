/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.mundo;

import java.io.Serializable;
import java.util.ArrayList;

public class Mensaje implements Serializable{
    //Atributos
    private int type;
    private String nickE, nickR, Mensaje, ip;
    private ArrayList <String> users;
    
    //Constructor
    public Mensaje() {
    }

    public Mensaje(int type, String nickE, String nickR, String Mensaje, String ip, ArrayList<String> users) {
        this.type = type;
        this.nickE = nickE;
        this.nickR = nickR;
        this.Mensaje = Mensaje;
        this.ip = ip;
        this.users = users;
    }

    public int getType() {
        return type;
    }

    public String getNickE() {
        return nickE;
    }

    public String getNickR() {
        return nickR;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public String getIp() {
        return ip;
    }

    public ArrayList getUsers() {
        return users;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    
    @Override
    public String toString() {
        return "Mensaje{" + "type=" + type + ", nickE=" + nickE + ", nickR=" + nickR + ", Mensaje=" + Mensaje + ", ip=" + ip + ", users=" + users + '}';
    }
    
}
