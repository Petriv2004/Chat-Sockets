/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.interfaz;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Usuario
 */
public class PanelConversacion extends JPanel {

    //Atributos
    private static JTextArea jtxaConv;
    private JScrollPane jsp;
    
    //Constructor
    public PanelConversacion() {
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Conversation")));
        setLayout(null);
        
        jtxaConv = new JTextArea();
        jtxaConv.setBounds(10, 25, 296, 200);
        jtxaConv.setEditable(false);
        
        jsp = new JScrollPane(jtxaConv);
        jsp.setBounds(10, 17, 296, 275);
        add(jsp);
    }

    public static JTextArea getJtxaConv() {
        return jtxaConv;
    }
}
