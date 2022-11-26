/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Tablas;

import Model.Tortilla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trabajo
 */
public class TablaTortilla extends AbstractTableModel {
    
    private Class[] tipoColumna;
    private String[] tituloColumna;
    private List<Tortilla> tortillas;

    public TablaTortilla(){
        tortillas = new ArrayList();
        this.tituloColumna = new String[]{"Id","Nombre","Desripcion","Disponibilidad"};
        this.tipoColumna = new Class[]{Integer.class,String.class,String.class,Boolean.class};
    }
    
    public List<Tortilla> getTortillas(){
        return tortillas;
    }
    
    public void setTortillas(List<Tortilla> tortillas){
        this.tortillas = tortillas;
    }
    
    public void addTortillas(Tortilla tortilla){
        this.tortillas.add(tortilla);
    }
    
    public void updateTortillas(List<Tortilla> tortillas){
        this.tortillas.clear();
        setTortillas(tortillas);
    }
    
 
    
    @Override
    public String getColumnName(int columna){
        return tituloColumna[columna];
    }
    
    @Override
    public Class<?> getColumnClass(int columnaIndex){
        return tipoColumna[columnaIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int cellIndex){
        return false;
    }
    
    
    @Override
    public int getRowCount() {
        return tortillas.size();
    }

    @Override
    public int getColumnCount() {
        return tituloColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tortillas.get(rowIndex).getId();
            case 1:
                return tortillas.get(rowIndex).getNombre();
            case 2:
                return tortillas.get(rowIndex).getDescripcion();
            case 3:
                return tortillas.get(rowIndex).getDisponible();
            default:
                return null;
        }
    }
    
}
