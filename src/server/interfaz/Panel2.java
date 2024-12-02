/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.interfaz;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Julián
 */
public class Panel2 extends JPanel {
    private JTextArea txt;
    private JScrollPane jsp;
    
    public Panel2(String title){
        setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new TitledBorder(title)));
        setLayout(null);
        
        txt = new JTextArea();
        txt.setBounds(10,20,296, 170);
        txt.setEditable(false);
        
        jsp = new JScrollPane(txt);
        jsp.setBounds(10,20,296, 170);
        add(jsp);
    }
    
}
