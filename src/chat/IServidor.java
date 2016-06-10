/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IServidor extends Remote{
    
    public List<String> obtenerListaMensajes() throws RemoteException;
    
    public void adicionarMensaje(String mensaje) throws RemoteException;
    
    public void adicionarObservador(IObservador oObservador) throws RemoteException;
    
    public void eliminarObservador(IObservador oObservador) throws RemoteException;
    
}
