/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Listas;

import Model.Tortilla;
import Repositorio.RepositorioTortilla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author trabajo
 */
public class ListaTortillas extends AbstractListModel{

    private List<Tortilla> tortillas; 
    
    public ListaTortillas(){
    }
    
    public void setTortillas(List<Tortilla> tortillas){
        this.tortillas = tortillas;
    }
    
    public Tortilla getTortilla(int index){
        return this.tortillas.get(index);
    }
    
    public void setTortillasDisponibles(List<Tortilla> tortillas){ 
       this.tortillas = new ArrayList();
       for (Tortilla p:tortillas){
           if(p.getDisponible()==true)
           {
               this.tortillas.add(p);
           }
        }
    }
    
    @Override
    public int getSize() {
        return tortillas.size();
    }

    @Override
    public Object getElementAt(int index) {
        Tortilla tortilla = tortillas.get(index);
        return tortilla.getNombre();
    }
    

    
}
