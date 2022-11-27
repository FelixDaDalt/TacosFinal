/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Listas;

import Model.Salsa;
import Repositorio.RepositorioSalsa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author trabajo
 */
public class ListaSalsas extends AbstractListModel{

    private List<Salsa> salsas; 

    public ListaSalsas(){

    }
    
    
    public Salsa getSalsa(int index){
        return this.salsas.get(index);
    }
    
    public void setSalsasDisponibles(List<Salsa> salsas){ 
       this.salsas = new ArrayList();
       this.salsas.stream().filter((p)->p.getDisponible()==true);
    }
    
    @Override
    public int getSize() {
        return salsas.size();
    }

    @Override
    public Object getElementAt(int index) {
        Salsa salsa = salsas.get(index);
        return salsa.getNombre();
    }
    

    
}
