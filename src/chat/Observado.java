/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Observado extends UnicastRemoteObject {

    protected List<IObservador> listObservadores;

    protected Observado() throws RemoteException {
        listObservadores = new ArrayList<IObservador>();
    }

    public void adicionarObservador(IObservador oObservador) {
        listObservadores.add(oObservador);
    }

    public void eliminarObservador(IObservador oObservador) {
        listObservadores.remove(oObservador);
    }

    public void notificar() {
        for (int i = 0; i < listObservadores.size(); i++) {
            System.out.println("Usuarios: "+listObservadores.get(i).getLogin());
        }
    }
}
