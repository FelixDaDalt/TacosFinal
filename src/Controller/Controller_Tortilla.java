/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Tablas.TablaTortilla;
import Model.Tortilla;
import Repositorio.RepositorioTortilla;
import View.Internal.Internal_Tortilla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author trabajo
 */
public class Controller_Tortilla implements ActionListener,ListSelectionListener {
    
    private Internal_Tortilla ventanaCargaTortilla = null;
    private TablaTortilla tabla = null; // Instancio un modelo personalizado para la Tabla Tortillas
    private RepositorioTortilla repo = null; // Instancio el Repositorio Tortillas
    private Tortilla tortilla = null;
    
    public Controller_Tortilla(Internal_Tortilla _ventanaCargaTortilla)
    {
        this.ventanaCargaTortilla = _ventanaCargaTortilla;
        this.tabla = new TablaTortilla();
        this.repo= new RepositorioTortilla();
        this.tortilla = new Tortilla();
        
        // aplico los ActionListener a los botones y al modelo
        this.ventanaCargaTortilla.jNew.addActionListener(this); 
        this.ventanaCargaTortilla.jUpdate.addActionListener(this);
        this.ventanaCargaTortilla.jDelete.addActionListener(this);
        this.ventanaCargaTortilla.jListaTortillas.getSelectionModel().addListSelectionListener(this);       
    }
    
    public void run(){
        this.ventanaCargaTortilla.jListaTortillas.setModel(this.tabla); //le seteo a la tabla el modelo
        this.tabla.setTortillas(repo.getAll()); // seteo al modelo de Tabla las tortillas que me devuelve el repo
        
        if(this.tabla.getRowCount()>0){ // si hay al menos un elemnto en la lista
            this.ventanaCargaTortilla.jListaTortillas.setRowSelectionInterval(0, 0); // posiciono la seleccion en el primero
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       
       if(evento.getSource() == ventanaCargaTortilla.jUpdate)
       {
           guardarTortilla();
       }
       
       if(evento.getSource() == ventanaCargaTortilla.jDelete)
       {
           eliminarTortilla();
       }
       
       if(evento.getSource() == this.ventanaCargaTortilla.jNew)
       {
           nuevaTortilla();
       }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!this.ventanaCargaTortilla.jListaTortillas.getSelectionModel().isSelectionEmpty()){
            
            this.ventanaCargaTortilla.jDelete.setEnabled(true);
            this.ventanaCargaTortilla.jUpdate.setEnabled(true);
            
            int index = ventanaCargaTortilla.jListaTortillas.getSelectedRow();
            this.tortilla = tabla.getTortillas().get(index);

            this.ventanaCargaTortilla.jNombre.setText(this.tortilla.getNombre());
            this.ventanaCargaTortilla.jDescripcion.setText(this.tortilla.getDescripcion());
            this.ventanaCargaTortilla.jDisponibilidad.setValue(this.tortilla.getDisponible()? 1 : 0);
            return;
            
        }
        this.ventanaCargaTortilla.jDelete.setEnabled(false);
        this.ventanaCargaTortilla.jUpdate.setEnabled(false);
    }
    
    private void guardarTortilla(){
        if(this.tortilla != null)
        {
            this.tortilla.setNombre(ventanaCargaTortilla.jNombre.getText());
            this.tortilla.setDescripcion(ventanaCargaTortilla.jDescripcion.getText());
            this.tortilla.setDisponible(ventanaCargaTortilla.jDisponibilidad.getValue()!=0);
            if(repo.Guardar(this.tortilla)){
                this.tabla.setTortillas(repo.getAll());
                this.ventanaCargaTortilla.jListaTortillas.updateUI();
            }         
        }
    }
    
    private void eliminarTortilla(){
         int result = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar "+this.tortilla.getNombre()+"?\n\n(Esta opcion no puede ser revertida)",
                "Eliminar Tortilla", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION)
        {
            if(repo.Eliminar(this.tortilla)){
                this.tabla.setTortillas(repo.getAll());
                this.ventanaCargaTortilla.jListaTortillas.updateUI(); 
                this.tortilla = new Tortilla();
                this.ventanaCargaTortilla.jListaTortillas.clearSelection();
                this.ventanaCargaTortilla.jUpdate.setEnabled(false);
                this.ventanaCargaTortilla.jDelete.setEnabled(false);

            }
        }            
    }

    private void nuevaTortilla(){
        this.tortilla = new Tortilla();
        if("".equals(ventanaCargaTortilla.jNombre.getText()))
            {
                JOptionPane.showMessageDialog(null,"Debe Ingresar un nombre");
                return;
            }
        guardarTortilla();
    }
}
