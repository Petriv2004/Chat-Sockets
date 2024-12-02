/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.interfaz;

import client.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import server.mundo.Mensaje;

/**
 *
 * @author Usuario
 */
public class PanelContactos extends JPanel implements ActionListener {

    //Atributos
    private static JComboBox jcbCont;
    private JButton btnCont;
    private Controlador ctrl;
    private String nickname;

    //Constructor
    public PanelContactos(Controlador ctrl, String nickname) {
        this.nickname = nickname;
        this.ctrl = ctrl;
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Contacts")));
        setLayout(null);

        jcbCont = new JComboBox();
        jcbCont.setBounds(10, 25, 275, 20);
        add(jcbCont);

        btnCont = new JButton("Actualizar Contactos");
        btnCont.setBounds(288, 20, 20, 30);
        add(btnCont);

        jcbCont.addActionListener(this);
        btnCont.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Actualizar Contactos")) {
            ctrl.socket(new Mensaje(1, nickname, null, null, ctrl.leerTxt("user"), null));

        } else if (e.getSource() == jcbCont) {
            PanelConversacion.getJtxaConv().setText("");
            ctrl.socket(new Mensaje(5, nickname, jcbCont.getSelectedItem().toString(), null, ctrl.leerTxt("user"), null));       
        }
    }

    public static JComboBox getJcbCont() {
        return jcbCont;
    }
}
