/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Tablas;

import Model.Estados;
import Model.Relleno;
import Model.Salsa;
import Model.Tacos;
import Model.Tortilla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author trabajo
 */
public class TablaTacos extends AbstractTableModel {
    
    private Class[] tipoColumna;
    private String[] tituloColumna;
    private List<Tacos> tacos;

    public TablaTacos(boolean cocina){
        
        if(cocina){
                this.tituloColumna = new String[]{"Id","Fecha","Estado","Tortilla","Salsa","Relleno 1","Relleno 2","Relleno 3"};
                this.tipoColumna = new Class[]{Integer.class,DateTime.class,Estados.class,Tortilla.class,Salsa.class,Relleno.class,Relleno.class,Relleno.class};
                return;
        }
        tacos = new ArrayList();
        this.tituloColumna = new String[]{"Id","Fecha","Estado"};
        this.tipoColumna = new Class[]{Integer.class,DateTime.class,Estados.class};
    }
    
    public List<Tacos> getTacos(){
        return tacos;
    }
    
    public void setTacos(List<Tacos> tacos, Date fecha){
        
        if(fecha == null)
        {

            this.tacos = tacos;
            return;
        }
        this.tacos  = new ArrayList();
        for (Tacos p:tacos){
           if(p.getFecha().getDate()==fecha.getDate())
           {
               this.tacos.add(p);
           }
       }
    }
    
    public void addTaco(Tacos taco){
        this.tacos.add(taco);
    }
    
    
   public void setTacosByEstado(List<Tacos> tacos,int id, Date fecha){
       this.tacos = new ArrayList();
       if(fecha==null)
       {
        for (Tacos p:tacos){
            if(p.getIdEstado().getId()==id)
            {
                this.tacos.add(p);
            }
        }
       return;
       }
       
       for (Tacos p:tacos){
           if(p.getFecha().getDate()==fecha.getDate())
           {
                if(p.getIdEstado().getId()==id)
                {
                    this.tacos.add(p);
                }
           }
       }
    }
    
   public void setTacosByEstado(List<Tacos> tacos,List<Integer>id){
       this.tacos = new ArrayList();
       for (Tacos p:tacos){
           for(int x:id){
               if(p.getIdEstado().getId()==x)
                   this.tacos.add(p);
           }
       }
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
        return tacos.size();
    }

    @Override
    public int getColumnCount() {
        return tituloColumna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tacos.get(rowIndex).getId();
            case 1:
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                dateFormat.format(tacos.get(rowIndex).getFecha());
                return dateFormat.format(tacos.get(rowIndex).getFecha());
            case 2:
                return tacos.get(rowIndex).getIdEstado();
                case 3:
                return tacos.get(rowIndex).getIdTortilla();
                case 4:
                return tacos.get(rowIndex).getIdSalsa();
                case 5:
                return tacos.get(rowIndex).getIdRelleno1();
                case 6:
                return tacos.get(rowIndex).getIdRelleno2();
                case 7:
                return tacos.get(rowIndex).getIdRelleno3();
            default:
                return null;
        }
    }
    
}
