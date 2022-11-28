/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Model.Relleno;
import Servicio.ServicioRelleno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class RepositorioRelleno {
    private List<Relleno> rellenos; 
    private ServicioRelleno servicioRelleno=null;
    
    public RepositorioRelleno(){
    
        this.rellenos = new ArrayList();
        if(this.servicioRelleno == null)
        {
            this.servicioRelleno = new ServicioRelleno();
        }
    }
      
    public <Relleno>List getAll(){
         this.rellenos = servicioRelleno.getAll(); // se lo asigno a la lista
         return this.rellenos.stream().filter((p)->p.getisDelete()==0).toList(); // retorno la lista
    }
    
    public boolean Guardar(Relleno relleno){
            try{
                if(relleno.getId()!=null){ // si tiene ID es una modificacion
                        servicioRelleno.Update(relleno); // llamo al servicio y actualizo
                        return true;
                    }
                servicioRelleno.Insert(relleno); // si no tiene ID es nuevo, lo inserto
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
    
    public boolean Eliminar(Relleno relleno){
        try{
              servicioRelleno.Delete(relleno);
              return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
                return false;
            }
    }
}
