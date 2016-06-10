/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface IObservador extends Serializable {
   
    public  List<String> actualizarListaMensaje();
    
    public  void escribirMensaje(String mensaje);
    
    public  void adicionarUsuario(IObservador oObservador);
    
    public  void eliminarUsuario(IObservador oObservador);
    
    public  String getLogin();

}
