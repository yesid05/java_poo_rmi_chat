/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Usuario implements IObservador{
    
    private IServidor oIservidor;
    
    private String login;
    
    private String Ip;
    
    private String registro;

    public Usuario( String login, String Ip, String registro) throws RemoteException {        
        this.login = login;
        this.Ip = Ip;
        this.registro = registro;
        oIservidor = Sala.getInstance();
        try {
            Registry registry = LocateRegistry.getRegistry(5555/*host*/);
            oIservidor = (IServidor) registry.lookup("Sala");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null, "Nose pudo inicializar el STUB", "Error RMI", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace();
        }
    }
    
    

    @Override
    public List<String> actualizarListaMensaje() {
        try {
            return oIservidor.obtenerListaMensajes();
        } catch (RemoteException ex) {
            return null;
        }
    }

    @Override
    public void escribirMensaje(String mensaje) {
        try {
            oIservidor.adicionarMensaje(mensaje);
        } catch (RemoteException ex) {
            System.out.println("error" + ex.getLocalizedMessage());
        }
    }

    @Override
    public void adicionarUsuario(IObservador oObservador) {
        try {
            oIservidor.adicionarObservador(oObservador);
        } catch (RemoteException ex) {
            System.out.println("Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void eliminarUsuario(IObservador oObservador) {
        try {
            oIservidor.eliminarObservador(oObservador);
        } catch (RemoteException ex) {
            System.out.println("Error: "+ex.getLocalizedMessage());
        }
    }

    @Override
    public String getLogin() {
        return login+" "+Ip+" "+registro;
    }

}
