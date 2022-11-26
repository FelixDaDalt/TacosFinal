/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Model.Tacos;
import Servicio.ServicioTacos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class RepositorioTacos {
    
    private List<Tacos> Listatacos = null; 
    private ServicioTacos servicioTacos=null;

    
    public RepositorioTacos(){
    
        this.Listatacos = new ArrayList();
        this.servicioTacos = new ServicioTacos();
    }
      
    public <Tacos>List getAll(){
        
         this.Listatacos = servicioTacos.getAll(); // se lo asigno a la lista
         return this.Listatacos; // retorno la lista
    }
    
    public boolean Guardar(Tacos taco){
            try{
                if(taco.getId()!=null){ // si tiene ID es una modificacion
                        servicioTacos.Update(taco); // llamo al servicio y actualizo
                        return true;
                    }
                servicioTacos.Insert(taco); // si no tiene ID es nuevo, lo inserto
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
    
    public boolean Eliminar(Tacos taco){
        try{
              servicioTacos.Delete(taco);
              return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
    
    
    
}
