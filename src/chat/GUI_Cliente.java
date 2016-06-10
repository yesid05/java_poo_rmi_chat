/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GUI_Cliente extends JFrame implements ActionListener {

    private GUI_Cliente_Mensaje winMensajeria;
    private static String host = "";
    private JTextField txtNombre;
    private JButton btnIngresar;

    public GUI_Cliente() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        Container container = getContentPane();
        GridBagConstraints gbc = new GridBagConstraints();
        
        container.setSize(250, 350);

        txtNombre = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        container.add(txtNombre,gbc);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        container.add(btnIngresar,gbc);
        pack();
    }

    public static void main(String[] args) {
        host = (args.length < 1) ? null : args[0];
        GUI_Cliente ventana = new GUI_Cliente();
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            if (!txtNombre.getText().isEmpty()) {
                try {
                    winMensajeria = new GUI_Cliente_Mensaje(this,txtNombre.getText(),host);
                    winMensajeria.setVisible(true);
                    this.dispose();
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getLocalizedMessage());
                }
            }
        }
    }

}
