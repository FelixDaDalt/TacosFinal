/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Model.Tortilla;
import Servicio.ServicioTortilla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class RepositorioTortilla {
    
    private List<Tortilla> tortillas; 
    private ServicioTortilla servicioTortilla=null;
    
    public RepositorioTortilla(){
        this.tortillas = new ArrayList();
        if(this.servicioTortilla == null)
        {
            this.servicioTortilla = new ServicioTortilla();
        }
        
         System.out.println(this.tortillas);
    }
      
    public <Tortilla>List getAll(){
         this.tortillas = servicioTortilla.getAll(); // se lo asigno a la lista
         return this.tortillas.stream().filter((p)->p.getisDelete()==0).toList(); // retorno la lista
    }
    
    public boolean Guardar(Tortilla tortilla){
            try{
                if(tortilla.getId()!=null){ // si tiene ID es una modificacion
                        servicioTortilla.Update(tortilla); // llamo al servicio y actualizo
                        return true;
                    }
                servicioTortilla.Insert(tortilla); // si no tiene ID es nuevo, lo inserto
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
    
    public boolean Eliminar(Tortilla tortilla){
        try{
              servicioTortilla.Delete(tortilla);
              return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
}
