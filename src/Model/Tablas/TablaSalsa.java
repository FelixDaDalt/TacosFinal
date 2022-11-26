/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Tablas;

import Model.Salsa;
import Model.Tortilla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author trabajo
 */
public class TablaSalsa extends AbstractTableModel {
    
    private Class[] tipoColumna;
    private String[] tituloColumna;
    private List<Salsa> salsas;

    public TablaSalsa(){
        this.salsas = new ArrayList();
        this.tituloColumna = new String[]{"Id","Nombre","Desripcion","Picor","Disponibilidad"};
        this.tipoColumna = new Class[]{Integer.class,String.class,String.class,Integer.class,Boolean.class};
    }
    
    public List<Salsa> getSalsas(){
        return this.salsas;
    }
    
    public void setSalsas(List<Salsa> salsas){
        this.salsas = salsas;
    }
    
    public void addSalsa(Salsa salsa){
        this.salsas.add(salsa);
    }
    
    public void updateSalsas(List<Salsa> salsas){
        this.salsas.clear();
        setSalsas(salsas);
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
        return this.salsas.size();
    }

    @Override
    public int getColumnCount() {
        return tituloColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.salsas.get(rowIndex).getId();
            case 1:
                return this.salsas.get(rowIndex).getNombre();
            case 2:
                return this.salsas.get(rowIndex).getDescripcion();
            case 3:
                return this.salsas.get(rowIndex).getPicor();
            case 4:
                return this.salsas.get(rowIndex).getDisponible();
            default:
                return null;
        }
    }
    
}
