/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Relleno;
import Model.Tablas.TablaRelleno;
import Repositorio.RepositorioRelleno;
import View.Internal.Internal_Relleno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author trabajo
 */
public class Controller_Relleno implements ActionListener,ListSelectionListener {
    
    private Internal_Relleno ventanaCargaRelleno = null;
    private TablaRelleno lista = null; // Instancio un modelo personalizado para la Tabla Tortillas
    private RepositorioRelleno repo = null; // Instancio el Repositorio Tortillas
    private Relleno relleno = null;
    
    public Controller_Relleno(Internal_Relleno _ventanaCargaRelleno)
    {
        this.ventanaCargaRelleno = _ventanaCargaRelleno;
        this.lista = new TablaRelleno();
        this.repo = new RepositorioRelleno();
        this.relleno = new Relleno();
        
        // aplico los ActionListener a los botones y al modelo
        this.ventanaCargaRelleno.jNew.addActionListener(this); 
        this.ventanaCargaRelleno.jUpdate.addActionListener(this);
        this.ventanaCargaRelleno.jDelete.addActionListener(this);
        this.ventanaCargaRelleno.jListaRellenos.getSelectionModel().addListSelectionListener(this);
        
               
    }
    
    public void run(){
        this.ventanaCargaRelleno.jListaRellenos.setModel(this.lista); //le seteo a la tabla el modelo
        this.lista.setRellenos(repo.getAll()); // seteo al modelo de Tabla las tortillas que me devuelve el repo
        if(this.lista.getRowCount()>0){
            this.ventanaCargaRelleno.jListaRellenos.setRowSelectionInterval(0, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       
       if(evento.getSource() == ventanaCargaRelleno.jUpdate)
       {
           guardarRelleno();
       }
       
       if(evento.getSource() == ventanaCargaRelleno.jDelete)
       {
           eliminarRelleno();
       }
       
       if(evento.getSource() == ventanaCargaRelleno.jNew)
       {
           nuevoRelleno();
       }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!this.ventanaCargaRelleno.jListaRellenos.getSelectionModel().isSelectionEmpty()){
        this.ventanaCargaRelleno.jDelete.setEnabled(true);
        this.ventanaCargaRelleno.jUpdate.setEnabled(true);
        
        int index = ventanaCargaRelleno.jListaRellenos.getSelectedRow();
        this.relleno = lista.getRellenos().get(index);
        
        this.ventanaCargaRelleno.jNombre.setText(this.relleno.getNombre());
        this.ventanaCargaRelleno.jDescripcion.setText(this.relleno.getDescripcion());
        this.ventanaCargaRelleno.jDisponibilidad.setValue(this.relleno.getDisponible()? 1 : 0);
        
        return;
        }
        this.ventanaCargaRelleno.jDelete.setEnabled(false);
        this.ventanaCargaRelleno.jUpdate.setEnabled(false);
    }
    
    private void guardarRelleno(){
        
        if(this.relleno != null)
        {
            this.relleno.setNombre(ventanaCargaRelleno.jNombre.getText());
            this.relleno.setDescripcion(ventanaCargaRelleno.jDescripcion.getText());
            this.relleno.setDisponible(ventanaCargaRelleno.jDisponibilidad.getValue()!=0);
            if(repo.Guardar(this.relleno)){
                this.lista.setRellenos(repo.getAll());
                this.ventanaCargaRelleno.jListaRellenos.updateUI();
            }         
        }
    }
    
    private void eliminarRelleno(){
        if(repo.Eliminar(this.relleno)){
            this.lista.setRellenos(repo.getAll());
            this.ventanaCargaRelleno.jListaRellenos.updateUI(); 
            this.relleno = new Relleno();
            this.ventanaCargaRelleno.jListaRellenos.clearSelection(); 
            this.ventanaCargaRelleno.jDelete.setEnabled(false);
            this.ventanaCargaRelleno.jUpdate.setEnabled(false);
        }            
    }

    private void nuevoRelleno(){
        this.relleno = new Relleno();
        if("".equals(this.ventanaCargaRelleno.jNombre.getText()))
            {
                JOptionPane.showMessageDialog(null,"Debe Ingresar un nombre");
                return;
            }
        guardarRelleno();
    }
}
