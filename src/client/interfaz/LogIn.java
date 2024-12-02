package client.interfaz;

import client.controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import server.mundo.Mensaje;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author nicor
 */
public class LogIn extends JFrame implements ActionListener {

    JLabel lblusuario, lblTitulo;
    JTextField txtusuario;
    JButton btnenviar;
    Controlador ctrl;

    public LogIn(Controlador ctrl) {
        this.ctrl = ctrl;
        setTitle("Log In");
        getContentPane().setLayout(null);
        setSize(350, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        //Inicializacion de Elementos
        lblTitulo = new JLabel("Log In");
        lblusuario = new JLabel("Nickname: ");
        txtusuario = new JTextField();
        btnenviar = new JButton("Log In");

        //Posicionar elementos
        lblTitulo.setBounds(125, 100, 70, 40);
        lblusuario.setBounds(125, 140, 70, 20);
        txtusuario.setBounds(125, 170, 100, 20);
        btnenviar.setBounds(125, 280, 100, 20);

        // caracteristicas extra
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnenviar.setBorderPainted(false);
        txtusuario.setBorder(null);
        txtusuario.setBackground(new Color(133, 197, 93));
        btnenviar.setBackground(new Color(119, 197, 70));
        btnenviar.addActionListener(this);

        //Añadir elementos
        add(lblTitulo);
        add(lblusuario);
        add(txtusuario);
        add(btnenviar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log In")) {
            VentanaConversacion con = new VentanaConversacion(txtusuario.getText(), ctrl);
            setVisible(false);
            con.setVisible(true);
            ctrl.socket(new Mensaje(2, txtusuario.getText(), null, null, ctrl.leerTxt("user"), null));
            ctrl.socket(new Mensaje(0, txtusuario.getText(), null, null, ctrl.leerTxt("user"), null));
        }
    }
}
