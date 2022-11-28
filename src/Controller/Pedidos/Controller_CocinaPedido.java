/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Pedidos;

import Model.Estados;

import Model.Tablas.TablaTacos;
import Model.Tacos;
import Repositorio.RepositorioEstados;
import Repositorio.RepositorioTacos;
import View.Internal.Pedidos.Internal_Cocina;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author trabajo
 */
public class Controller_CocinaPedido implements ActionListener, ListSelectionListener, ItemListener, PropertyChangeListener{
    
    private Internal_Cocina ventanaCocina = null;
    
    private RepositorioTacos repo = null;
    
    private RepositorioEstados reposEstados= null;
    private Estados estado = null;
   
    private TablaTacos tablaTacos = null;
    private Tacos taco;
    
    private Timer timer;
    private int cronometro = 0;
    private Date fecha;
    
    
    public Controller_CocinaPedido(Internal_Cocina _ventanaCocina){
        
        this.timer = new Timer(1000,this);
        timer.start();
        
        this.ventanaCocina = _ventanaCocina;   
        this.repo = new RepositorioTacos();
        this.reposEstados = new RepositorioEstados();
        
        this.ventanaCocina.jUpdateCocina.addActionListener(this);
        this.ventanaCocina.jComboEstados.addActionListener(this);
        this.ventanaCocina.jRadioDefecto.addItemListener(this);
        this.ventanaCocina.jRadioTodos.addItemListener(this);
        this.ventanaCocina.jRadioFiltrar.addItemListener(this);
        this.ventanaCocina.jRadioFecha.addItemListener(this);
       
        this.ventanaCocina.jPedidosCocina.getSelectionModel().addListSelectionListener(this);
       
        this.ventanaCocina.jCalendario.addPropertyChangeListener(this);

    }
    
    public void run(){
        this.tablaTacos = new TablaTacos(true);
        this.ventanaCocina.jPedidosCocina.setModel(tablaTacos);
        
        
        for (Object p:this.reposEstados.getAll()){
            this.ventanaCocina.jComboEstados.addItem((Estados)p);
        }
        
        this.ventanaCocina.jCalendario.setDate(new Date());
        this.ventanaCocina.jRadioDefecto.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

    if (evento.getSource() == this.timer)
    { 
        if(this.cronometro==30){
            updateVistaPedidos();
            this.cronometro = 0;
        }
        this.ventanaCocina.jRecarga.setValue(cronometro);
        this.ventanaCocina.jRecarga.setString(Integer.toString(30-cronometro)+" seg");
        this.cronometro++;

    }
    
    if (evento.getSource() == this.ventanaCocina.jUpdateCocina)
    {
         int result = JOptionPane.showConfirmDialog(null, "¿Modificar el estado?",
                "Confirmacion de estado", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION)
        {
            guardarPedido();
        }
    
    }
    
    
    if(evento.getSource() == this.ventanaCocina.jComboEstados)
            updateVistaPedidosbyCombo();
    }   

    public void guardarPedido(){
        
            if(this.taco!= null)
            {
                    // seteo la fecha
                    long millis=System.currentTimeMillis(); 
                    java.sql.Date date = new java.sql.Date(millis);
                    this.taco.setFecha(date);

                    // seteo el estado
                    this.estado = this.reposEstados.getById(this.ventanaCocina.jEstado.getValue());
                    this.taco.setIdEstado(this.estado);
                    // Guardo el taco
                    if(this.repo.Guardar(this.taco)){
                        updateVistaPedidos();

                    }
            }
        
    }
        
    public void detallePedido(){
        if(!this.ventanaCocina.jPedidosCocina.getSelectionModel().isSelectionEmpty())
        {
            int index = this.ventanaCocina.jPedidosCocina.getSelectedRow();
            taco = this.tablaTacos.getTacos().get(index);
           
            if(taco.getIdRelleno2()==null)
            {
                JOptionPane.showMessageDialog(null,"Tortilla: "+taco.getIdTortilla().getNombre()+"\nSalsa: "+taco.getIdSalsa().getNombre()+"\nRelleno 1: "+taco.getIdRelleno1().getNombre());
                return;
            }
            
            if(taco.getIdRelleno3()==null)
            {
                    JOptionPane.showMessageDialog(null,"Tortilla: "+taco.getIdTortilla().getNombre()+"\nSalsa: "+taco.getIdSalsa().getNombre()+"\nRelleno 1: "+taco.getIdRelleno1().getNombre()+"\nRelleno 2: "+taco.getIdRelleno2().getNombre());
                    return;
            }
            
            JOptionPane.showMessageDialog(null,"Tortilla: "+taco.getIdTortilla().getNombre()+"\nSalsa: "+taco.getIdSalsa().getNombre()+"\nRelleno 1: "+taco.getIdRelleno1().getNombre()+"\nRelleno 2: "+taco.getIdRelleno2().getNombre()+"\nRelleno 3: "+taco.getIdRelleno3().getNombre());
       }
    }
    
    public void updateVistaPedidos(){
        
        if(this.ventanaCocina.jRadioDefecto.isSelected())
        {
            
            this.ventanaCocina.jPedidosCocina.clearSelection();
            this.ventanaCocina.jPedidosCocina.updateUI();
            return;
        }
        
        if(this.ventanaCocina.jRadioFiltrar.isSelected()){
            updateVistaPedidosbyCombo();
            return;
        }
            this.ventanaCocina.jComboEstados.setEnabled(false);
            this.tablaTacos.setTacos(this.repo.getAll(),this.fecha);
            this.ventanaCocina.jPedidosCocina.clearSelection();
            this.ventanaCocina.jPedidosCocina.updateUI();
    }
    
    public void updateVistaPedidosbyCombo(){
        int index = this.ventanaCocina.jComboEstados.getSelectedIndex();
        this.tablaTacos.setTacosByEstado(this.repo.getAll(),this.ventanaCocina.jComboEstados.getItemAt(index).getId(),this.fecha);
        this.ventanaCocina.jPedidosCocina.clearSelection();
        this.ventanaCocina.jPedidosCocina.updateUI();
    }
   
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!this.ventanaCocina.jPedidosCocina.getSelectionModel().isSelectionEmpty()){
            this.ventanaCocina.jUpdateCocina.setEnabled(true);
            
            
            int index = this.ventanaCocina.jPedidosCocina.getSelectedRow();
            taco = this.tablaTacos.getTacos().get(index);
            this.ventanaCocina.jEstado.setValue(taco.getIdEstado().getId());
        }else{
            this.ventanaCocina.jUpdateCocina.setEnabled(false);
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(this.ventanaCocina.jRadioDefecto.isSelected())
        {
            this.timer.restart();
            this.ventanaCocina.jRadioFecha.setSelected(false);
            this.ventanaCocina.jRadioFecha.setEnabled(false);
            
            this.ventanaCocina.jRadioFiltrar.setSelected(false);
            this.ventanaCocina.jRadioFiltrar.setEnabled(false);

            List<Integer> estados= Arrays.asList(1, 2);
            this.tablaTacos.setTacosByEstado(this.repo.getAll(),estados);
            this.ventanaCocina.jPedidosCocina.clearSelection();
            this.ventanaCocina.jPedidosCocina.updateUI();
        }
        
        if(this.ventanaCocina.jRadioTodos.isSelected())
        {
            this.timer.stop();
            this.ventanaCocina.jRadioFiltrar.setEnabled(true);
            this.ventanaCocina.jRadioFecha.setEnabled(true);
            updateVistaPedidos();
        }
        
        if(this.ventanaCocina.jRadioFecha.isSelected())
        {
            this.ventanaCocina.jCalendario.setEnabled(true);
            updateVistaPedidos();
        }else{
            this.fecha = null;
            this.ventanaCocina.jCalendario.setEnabled(false);
            updateVistaPedidos();
        }
        
        if(this.ventanaCocina.jRadioFiltrar.isSelected())
        {
            this.ventanaCocina.jComboEstados.setEnabled(true);
            updateVistaPedidosbyCombo();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evento) {
        if(this.ventanaCocina.jCalendario.isEnabled())
        {
            this.fecha = this.ventanaCocina.jCalendario.getDate();
            updateVistaPedidos();
            return;
        }
        this.fecha=null;
    }
}
