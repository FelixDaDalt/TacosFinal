/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Model.Salsa;
import Servicio.ServicioSalsa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class RepositorioSalsa {
    private List<Salsa> salsas; 
    private ServicioSalsa servicioSalsa=null;
    
     public RepositorioSalsa(){
        this.salsas = new ArrayList();
        if(this.servicioSalsa == null)
        {
            this.servicioSalsa = new ServicioSalsa();
        }
    }
      
    public <Salsa>List getAll(){
         this.salsas = servicioSalsa.getAll(); // se lo asigno a la lista
         return this.salsas; // retorno la lista
    }
    
    public boolean Guardar(Salsa salsa){
            try{
                if(salsa.getId()!=null){ // si tiene ID es una modificacion
                        servicioSalsa.Update(salsa); // llamo al servicio y actualizo
                        return true;
                    }
                servicioSalsa.Insert(salsa); // si no tiene ID es nuevo, lo inserto
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
    
    public boolean Eliminar(Salsa salsa){
        try{
              servicioSalsa.Delete(salsa);
              return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
}
