/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Model.Estados;
import Servicio.ServicioEstados;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trabajo
 */
public class RepositorioEstados {
    
    private List<Estados> estados; 
    private ServicioEstados servicioEstados=null;
    
    public RepositorioEstados(){
    
        this.estados = new ArrayList();
        if(this.servicioEstados == null)
        {
            this.servicioEstados = new ServicioEstados();
        }
    }
      
    public <Estados>List getAll(){
         this.estados = servicioEstados.getAll(); // se lo asigno a la lista
         return this.estados; // retorno la lista
    }
    
    public Estados getById(int id){
        return servicioEstados.getById(id);
    }
    
}
