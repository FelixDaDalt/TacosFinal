/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Tablas;

import Model.Relleno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trabajo
 */
public class TablaRelleno extends AbstractTableModel {
    
    private Class[] tipoColumna;
    private String[] tituloColumna;
    private List<Relleno> rellenos;

    public TablaRelleno(){
        rellenos = new ArrayList();
        this.tituloColumna = new String[]{"Id","Nombre","Desripcion","Disponibilidad"};
        this.tipoColumna = new Class[]{Integer.class,String.class,String.class,Boolean.class};
    }
    
    public List<Relleno> getRellenos(){
        return rellenos;
    }
    
    public void setRellenos(List<Relleno> rellenos){
        this.rellenos = rellenos;
    }
    
    public void addRelleno(Relleno relleno){
        this.rellenos.add(relleno);
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
        return rellenos.size();
    }

    @Override
    public int getColumnCount() {
        return tituloColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rellenos.get(rowIndex).getId();
            case 1:
                return rellenos.get(rowIndex).getNombre();
            case 2:
                return rellenos.get(rowIndex).getDescripcion();
            case 3:
                return rellenos.get(rowIndex).getDisponible();
            default:
                return null;
        }
    }
    
}
