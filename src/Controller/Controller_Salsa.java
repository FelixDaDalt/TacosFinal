/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Salsa;
import Model.Tablas.TablaSalsa;
import Repositorio.RepositorioSalsa;
import View.Internal.Internal_Salsa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author trabajo
 */
public class Controller_Salsa implements ActionListener,ListSelectionListener {
    
    private Internal_Salsa ventanaCargaSalsa = null;
    private TablaSalsa lista = null; // Instancio un modelo personalizado para la Tabla Tortillas
    private RepositorioSalsa repo = null; // Instancio el Repositorio Tortillas
    private Salsa salsa = null;
    
    public Controller_Salsa(Internal_Salsa _ventanaCargaSalsa)
    {
        this.ventanaCargaSalsa = _ventanaCargaSalsa;
        this.lista = new TablaSalsa();
        this.repo= new RepositorioSalsa();
        this.salsa = new Salsa();
        
        // aplico los ActionListener a los botones y al modelo
        this.ventanaCargaSalsa.jNew.addActionListener(this); 
        this.ventanaCargaSalsa.jUpdate.addActionListener(this);
        this.ventanaCargaSalsa.jDelete.addActionListener(this);
        this.ventanaCargaSalsa.jListaSalsas.getSelectionModel().addListSelectionListener(this);       
    }
    
    public void run(){
       this.ventanaCargaSalsa.jListaSalsas.setModel(this.lista); //le seteo a la tabla el modelo
        this.lista.setSalsas(repo.getAll()); // seteo al modelo de Tabla las tortillas que me devuelve el repo
        
        if(this.lista.getRowCount()>0){ // si hay al menos un elemnto en la lista
            this.ventanaCargaSalsa.jListaSalsas.setRowSelectionInterval(0, 0); // posiciono la seleccion en el primero
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       
       if(evento.getSource() == this.ventanaCargaSalsa.jUpdate)
       {
           guardarSalsa();
       }
       
       if(evento.getSource() == this.ventanaCargaSalsa.jDelete)
       {
           eliminarSalsa();
       }
       
       if(evento.getSource() == this.ventanaCargaSalsa.jNew)
       {
           nuevaSalsa();
       }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!this.ventanaCargaSalsa.jListaSalsas.getSelectionModel().isSelectionEmpty()){
            
            this.ventanaCargaSalsa.jDelete.setEnabled(true);
            this.ventanaCargaSalsa.jUpdate.setEnabled(true);
      
            int index = this.ventanaCargaSalsa.jListaSalsas.getSelectedRow();
            this.salsa = lista.getSalsas().get(index);

            this.ventanaCargaSalsa.jNombre.setText(this.salsa.getNombre());
            this.ventanaCargaSalsa.jDescripcion.setText(this.salsa.getDescripcion());
            this.ventanaCargaSalsa.jPicor.setValue(this.salsa.getPicor());
            this.ventanaCargaSalsa.jDisponibilidad.setValue(this.salsa.getDisponible()? 1 : 0);

            return;
        }
        this.ventanaCargaSalsa.jDelete.setEnabled(false);
        this.ventanaCargaSalsa.jUpdate.setEnabled(false);
    }
    
    private void guardarSalsa(){
        if(this.salsa != null)
        {
            this.salsa.setNombre(this.ventanaCargaSalsa.jNombre.getText());
            this.salsa.setDescripcion(this.ventanaCargaSalsa.jDescripcion.getText());
            this.salsa.setPicor(this.ventanaCargaSalsa.jPicor.getValue());
            this.salsa.setDisponible(this.ventanaCargaSalsa.jDisponibilidad.getValue()!=0);
            if(repo.Guardar(this.salsa)){
                this.lista.updateSalsas(repo.getAll());
                this.ventanaCargaSalsa.jListaSalsas.updateUI();
            }         
        }
    }
    
    private void eliminarSalsa(){
        if(repo.Eliminar(this.salsa)){
            this.lista.updateSalsas(repo.getAll());
            this.ventanaCargaSalsa.jListaSalsas.updateUI(); 
            this.salsa = new Salsa();
            this.ventanaCargaSalsa.jListaSalsas.clearSelection();
            this.ventanaCargaSalsa.jUpdate.setEnabled(false);
            this.ventanaCargaSalsa.jDelete.setEnabled(false);

        }            
    }

    private void nuevaSalsa(){
        this.salsa = new Salsa();
        if("".equals(this.ventanaCargaSalsa.jNombre.getText()))
            {
                JOptionPane.showMessageDialog(null,"Debe Ingresar un nombre");
                return;
            }
        guardarSalsa();
    }
}
