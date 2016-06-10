/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GUI_Cliente_Mensaje extends JFrame implements ActionListener {

    private GUI_Cliente winCliente;

    private IObservador oUsuario;
    
    private JLabel lblImagen;

    private JTextArea txtMensajes;

    private JTextArea txtEscribir;

    private JButton btnEnviarMensaje;

    private JButton btnActualizar;

    private JButton btnSalir;

    public GUI_Cliente_Mensaje(GUI_Cliente unCliente, String unNombre, String unaIp) throws RemoteException {
        setTitle(unNombre);
        inicializarComponentesGraficos(unCliente);
        oUsuario = new Usuario(unNombre, unaIp, "Registro 1");
        oUsuario.adicionarUsuario(oUsuario);
    }

    public IObservador getoUsuario() {
        return oUsuario;
    }

    public void setoUsuario(IObservador oUsuario) {
        this.oUsuario = oUsuario;
    }

    public void inicializarComponentesGraficos(GUI_Cliente unCiente) {

        winCliente = unCiente;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        Container contenido = getContentPane();
        contenido.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new GridBagLayout());

//        txtMensajes = new JList();
//        txtMensajes.setVisibleRowCount(10);
//        txtMensajes.setFixedCellWidth(this.getSize().width);
//        txtMensajes.setFixedCellHeight(30);
        txtMensajes = new JTextArea(15, 30);
        txtMensajes.setWrapStyleWord(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSuperior.add(new JScrollPane(txtMensajes), gbc);
        
        lblImagen = new JLabel(new ImageIcon(getClass().getResource("descarga.png")));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSuperior.add(lblImagen,gbc);
        
        txtEscribir = new JTextArea(2, 15);
        txtEscribir.setWrapStyleWord(true);
        txtEscribir.setEditable(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSuperior.add(new JScrollPane(txtEscribir), gbc);

        btnEnviarMensaje = new JButton("Enviar mensaje");
        btnEnviarMensaje.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 5, 5);
        panelInferior.add(btnEnviarMensaje, gbc);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 5, 5);
        panelInferior.add(btnActualizar, gbc);

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(this);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 5, 5);
        panelInferior.add(btnSalir, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        contenido.add(panelSuperior, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        contenido.add(panelInferior, gbc);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnviarMensaje) {
            oUsuario.escribirMensaje(txtEscribir.getText());
            actualizarMensajes();
            txtEscribir.setText("");
        }
        if (e.getSource() == btnActualizar) {
            actualizarMensajes();
        }
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public void actualizarMensajes() {
        List<String> lista = oUsuario.actualizarListaMensaje();
        String unMensaje = "";
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            unMensaje = unMensaje + lista.get(i) + "\n";
        }
        txtMensajes.setText(unMensaje);
    }

}
