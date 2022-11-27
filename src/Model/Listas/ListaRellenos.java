/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Listas;

import Model.Relleno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author trabajo
 */
public class ListaRellenos extends AbstractListModel{

    private List<Relleno> rellenos; 

    
    public ListaRellenos(){
    }
    
    
    public Relleno getRelleno(int index){
        return this.rellenos.get(index);
    }
    
    public void setRellenosDisponibles(List<Relleno> rellenos){ 
       this.rellenos = new ArrayList();
       this.rellenos.stream().filter((p)->p.getDisponible()==true);
    }
    
    public int findRellenoIndex(Relleno relleno){
       return this.rellenos.indexOf(relleno);
    }
    
    @Override
    public int getSize() {
        return rellenos.size();
    }

    @Override
    public Object getElementAt(int index) {
        Relleno relleno = rellenos.get(index);
        return relleno.getNombre();
    }
    

    
}
