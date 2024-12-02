/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.interfaz;

import client.controlador.Controlador;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import server.mundo.Mensaje;

/**
 *
 * @author Usuario
 */
public class VentanaConversacion extends JFrame implements WindowListener{
    //Atributos
    private PanelContactos pnlCont;
    private PanelEnviarMensaje pnlEnv;
    private PanelConversacion pnlConv;
    private Controlador ctrl;
    private String nickname;
    
    //Constructor
    public VentanaConversacion(String usuario, Controlador ctrl) {
        nickname = usuario;
        this.ctrl = ctrl;
        setTitle(usuario);
        getContentPane().setLayout(null);
        
        setSize(350, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        pnlCont = new PanelContactos(ctrl,nickname);
        pnlCont.setBounds(10, 10, 315, 60);
        getContentPane().add(pnlCont);
        
        pnlConv = new PanelConversacion();
        pnlConv.setBounds(10, 80, 315, 300);
        getContentPane().add(pnlConv);
        
        pnlEnv = new PanelEnviarMensaje(pnlConv, this.ctrl, nickname);
        pnlEnv.setBounds(10, 390, 315, 60);
        getContentPane().add(pnlEnv);
        
        addWindowListener(this);
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ctrl.socket(new Mensaje(4, nickname, null, null,ctrl.leerTxt("user"), null));
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
