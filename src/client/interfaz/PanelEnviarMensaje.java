/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.interfaz;

import client.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import server.mundo.Mensaje;

/**
 *
 * @author Usuario
 */
public class PanelEnviarMensaje extends JPanel implements ActionListener {

    //Atributos
    private JTextField jtxtMsg;
    private JButton btnEnv;
    private PanelConversacion con;
    private Controlador ctrl;
    private String nickname;

    //Constructor
    public PanelEnviarMensaje(PanelConversacion con, Controlador ctrl, String nickname) {
        this.ctrl = ctrl;
        this.nickname = nickname;
        this.con = con;
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Send msg")));
        setLayout(null);

        jtxtMsg = new JTextField();
        jtxtMsg.setBounds(10, 25, 275, 20);
        add(jtxtMsg);

        jtxtMsg.addActionListener(this);

        btnEnv = new JButton("S");
        btnEnv.setBounds(288, 20, 20, 30);
        add(btnEnv);
        btnEnv.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = jtxtMsg.getText();
        if (e.getActionCommand().equals("S")) {
            con.getJtxaConv().append(nickname + " : \n" + text + "\n");
            jtxtMsg.setText("");
            ctrl.socket(new Mensaje(3, nickname, PanelContactos.getJcbCont().getSelectedItem().toString(), text, ctrl.leerTxt("user"), null));
        } else if (e.getSource() == jtxtMsg) {
            con.getJtxaConv().append(nickname + " : \n" + text + "\n");
            jtxtMsg.setText("");
            ctrl.socket(new Mensaje(3, nickname, PanelContactos.getJcbCont().getSelectedItem().toString(), text, ctrl.leerTxt("user"), null));
        }
    }
}
