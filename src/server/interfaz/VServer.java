/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.interfaz;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class VServer extends JFrame{
    private Panel1 pnlPanel1;
    private Panel2 pnlPanel2;

    public VServer() {
        setTitle("Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500);
        getContentPane().setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        pnlPanel1 = new Panel1("Conversations");
        pnlPanel1.setBounds(10,10,315,250);
        getContentPane().add(pnlPanel1);
        
        pnlPanel2 = new Panel2("Exceptions");
        pnlPanel2.setBounds(10,260,315,200);
        getContentPane().add(pnlPanel2);
        
    }
}
