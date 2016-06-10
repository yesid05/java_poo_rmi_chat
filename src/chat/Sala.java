/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Sala extends Observado implements IServidor {

    private List<String> listMensaje;

    private static Sala INSTANCE = null;

    private Sala() throws RemoteException {
        listMensaje = new ArrayList<String>();
    }

    private static void createInstance() throws RemoteException {
        if (INSTANCE == null) {
            INSTANCE = new Sala();
        }
    }

    public static Sala getInstance() throws RemoteException {
        createInstance();
        return INSTANCE;
    }

    @Override
    public List<String> obtenerListaMensajes() {
        
        return listMensaje;
    }

    @Override
    public void adicionarMensaje(String mensaje) {
        notificar();
        System.out.println("Mensaje: "+mensaje);
        listMensaje.add(mensaje);
    }

    @Override
    public void adicionarObservador(IObservador oObservador) {
        System.out.println("Usuario: "+oObservador.getLogin());
        super.adicionarObservador(oObservador);
    }

    @Override
    public void eliminarObservador(IObservador oObservador) {
        super.eliminarObservador(oObservador);
    }
    
    public static void main (String args[]){
        try {
            Sala unaSala = Sala.getInstance();
            Registry registry = LocateRegistry.createRegistry(5555/*Constantes.PORT_RMI*/);
            registry.bind("Sala", unaSala);            
            System.out.println("Servidor funcionando... ");
            unaSala.notificar();
        } catch (Exception e) {
            System.out.println("error"+e.getLocalizedMessage());
        }
    }

}
