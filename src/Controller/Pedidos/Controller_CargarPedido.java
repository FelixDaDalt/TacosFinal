/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Pedidos;

import Model.Estados;
import Model.Listas.ListaRellenos;
import Model.Listas.ListaSalsas;
import Model.Listas.ListaTortillas;
import Model.Tablas.TablaTacos;
import Model.Tacos;
import Repositorio.RepositorioEstados;
import Repositorio.RepositorioRelleno;
import Repositorio.RepositorioSalsa;
import Repositorio.RepositorioTacos;
import Repositorio.RepositorioTortilla;
import View.Internal.Pedidos.Internal_Cargar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author trabajo
 */
public class Controller_CargarPedido implements ActionListener, ListSelectionListener{
    
    
    private Internal_Cargar ventanaCargarPedido = null;
    
    private ListaTortillas listaTortillas = null;
    private ListaRellenos listaRellenos = null;
    private ListaSalsas listaSalsas = null;
    
    private RepositorioTortilla repoTortillas = null;
    private RepositorioSalsa repoSalsa = null;
    private RepositorioRelleno repoRelleno = null;
    
    private RepositorioTacos repoTacos = null;
    
    private RepositorioEstados reposEstados= null;
    private Estados estado = null;
   
    private TablaTacos tablaTacos = null;
    private  Tacos taco;
    
    private Timer timer;
    private int cronometro = 0;
    
    public Controller_CargarPedido(Internal_Cargar _ventanaCargarPedido){
        
        
        this.timer = new Timer(1000,this);
        timer.start();
        
        this.ventanaCargarPedido = _ventanaCargarPedido;  
        this.repoTacos = new RepositorioTacos();
        
        this.ventanaCargarPedido.jUpdateList.addActionListener(this);
        this.ventanaCargarPedido.jcargarPedido.addActionListener(this);
        this.ventanaCargarPedido.jPedidos.getSelectionModel().addListSelectionListener(this);
        this.ventanaCargarPedido.jEliminarPedido.addActionListener(this);
        this.ventanaCargarPedido.jDetalle.addActionListener(this);
        this.ventanaCargarPedido.jUpdate.addActionListener(this);
    }

   
    public void run(){
         
        
        this.repoTortillas = new RepositorioTortilla();
        this.repoSalsa = new RepositorioSalsa();
        this.repoRelleno = new RepositorioRelleno();
        this.reposEstados = new RepositorioEstados();
        
        this.tablaTacos = new TablaTacos(false);
        this.listaTortillas = new ListaTortillas();
        this.listaRellenos = new ListaRellenos();
        this.listaSalsas = new ListaSalsas();
        
        this.tablaTacos.setTacos(this.repoTacos.getAll(),null);
        this.listaTortillas.setTortillasDisponibles(this.repoTortillas.getAll());
        this.listaRellenos.setRellenosDisponibles(this.repoRelleno.getAll());
        this.listaSalsas.setSalsasDisponibles(this.repoSalsa.getAll());
       
        this.ventanaCargarPedido.jPedidos.setModel(this.tablaTacos);
        this.ventanaCargarPedido.jListaTortillas.setModel(this.listaTortillas);
        this.ventanaCargarPedido.jListaRellenos.setModel(this.listaRellenos);
        this.ventanaCargarPedido.jListaSalsas.setModel(this.listaSalsas);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
    
    if (evento.getSource() == this.timer)
    { 
        if(this.cronometro==30){
            UpdateTable();
            this.cronometro = 0;
        }
        this.ventanaCargarPedido.jRecarga.setValue(cronometro);
        this.ventanaCargarPedido.jRecarga.setString(Integer.toString(30-cronometro)+" seg");
        this.cronometro++;

    }
        
    if (evento.getSource() == this.ventanaCargarPedido.jcargarPedido)
    { 
        nuevoPedido();
    }
    
    if (evento.getSource() == this.ventanaCargarPedido.jEliminarPedido)
    {
        eliminarPedido();
    
    }
    
    if (evento.getSource() == this.ventanaCargarPedido.jUpdate)
    {
        guardarPedido();
    
    }
    
    if (evento.getSource() == this.ventanaCargarPedido.jDetalle)
    {
        detallePedido();
    
    }
    
    if (evento.getSource() == this.ventanaCargarPedido.jUpdateList)
        {
            this.timer.restart();
            this.cronometro=30;
            UpdateTable();

        }
    }   

    public void guardarPedido(){
        if(this.taco!= null)
        {
            int rellenosSeleccionados = this.ventanaCargarPedido.jListaRellenos.getSelectedIndices().length;

            int[] rellenosIndex = ventanaCargarPedido.jListaRellenos.getSelectedIndices(); //Almaceno los indices de la lista de rellenos
                // seteo los rellenos
            switch(rellenosSeleccionados){
                        case 1:
                            this.taco.setIdRelleno1(this.listaRellenos.getRelleno(rellenosIndex[0]));
                            this.taco.setIdRelleno2(null);
                            this.taco.setIdRelleno3(null);
                            break;
                        case 2:
                            this.taco.setIdRelleno1(this.listaRellenos.getRelleno(rellenosIndex[0]));
                            this.taco.setIdRelleno2(this.listaRellenos.getRelleno(rellenosIndex[1]));
                            this.taco.setIdRelleno3(null);
                            break;
                        case 3:
                            this.taco.setIdRelleno1(this.listaRellenos.getRelleno(rellenosIndex[0]));
                            this.taco.setIdRelleno2(this.listaRellenos.getRelleno(rellenosIndex[1]));
                            this.taco.setIdRelleno3(this.listaRellenos.getRelleno(rellenosIndex[2]));
                            break;
                        default:
                            break;
                }

                // seteo la fecha
                long millis=System.currentTimeMillis(); 
                java.sql.Date date = new java.sql.Date(millis);
                this.taco.setFecha(date);

                // seteo salsa y tortilla
                this.taco.setIdSalsa(this.listaSalsas.getSalsa(this.ventanaCargarPedido.jListaSalsas.getSelectedIndex()));
                this.taco.setIdTortilla(this.listaTortillas.getTortilla(this.ventanaCargarPedido.jListaTortillas.getSelectedIndex()));

                // seteo el estado
                this.estado = this.reposEstados.getById(1);
                this.taco.setIdEstado(this.estado);
                // Guardo el taco
                if(this.repoTacos.Guardar(this.taco)){
                    this.tablaTacos.setTacos(this.repoTacos.getAll(),null);
                    this.ventanaCargarPedido.jPedidos.updateUI();

                }
        }
    }
    
    public void eliminarPedido(){
         if(repoTacos.Eliminar(this.taco))
         {
           
            this.tablaTacos.setTacos(repoTacos.getAll(),null);
            this.ventanaCargarPedido.jPedidos.updateUI();
            this.ventanaCargarPedido.jPedidos.clearSelection();
            this.taco = new Tacos();
         }
        
    
    }
    
    public void detallePedido(){
        if(!this.ventanaCargarPedido.jPedidos.getSelectionModel().isSelectionEmpty())
        {
            int index = this.ventanaCargarPedido.jPedidos.getSelectedRow();
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
    
    public void nuevoPedido(){
        int rellenosSeleccionados = this.ventanaCargarPedido.jListaRellenos.getSelectedIndices().length;
        int tortillasSeleccionadas = this.ventanaCargarPedido.jListaTortillas.getSelectedIndices().length;
        int salsasSeleccionadas = this.ventanaCargarPedido.jListaSalsas.getSelectedIndices().length;

        if(tortillasSeleccionadas==0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una Tortilla");
            return;
        }
        
        if(salsasSeleccionadas ==0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una Salsa");
            return;
        }
        
        if(rellenosSeleccionados > 3 || rellenosSeleccionados==0)
        {
            JOptionPane.showMessageDialog(null,"Seleccione los rellenos correctamente");
            return;
        }
        this.taco = new Tacos();
        guardarPedido();
    
    }
    
    public void UpdateTable(){
            this.repoTacos = new RepositorioTacos();
            this.tablaTacos.setTacos(this.repoTacos.getAll(),null);
            this.ventanaCargarPedido.jPedidos.updateUI();
            
    }
    
                
                
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!this.ventanaCargarPedido.jPedidos.getSelectionModel().isSelectionEmpty()){
            this.ventanaCargarPedido.jEliminarPedido.setEnabled(true);
            this.ventanaCargarPedido.jUpdate.setEnabled(true);
            this.ventanaCargarPedido.jDetalle.setEnabled(true);
            int index = this.ventanaCargarPedido.jPedidos.getSelectedRow();
            taco = this.tablaTacos.getTacos().get(index);
            if(taco.getIdEstado().getId()>1)
            {
                this.ventanaCargarPedido.jUpdate.setEnabled(false);
            }
            
            this.ventanaCargarPedido.jListaTortillas.setSelectedValue(taco.getIdTortilla().getNombre(), true);
            this.ventanaCargarPedido.jListaSalsas.setSelectedValue(taco.getIdSalsa().getNombre(), true);
            
            this.ventanaCargarPedido.jListaRellenos.setSelectedValue(taco.getIdRelleno1().getNombre(), true);

            if(taco.getIdRelleno2()!=null){
            
            this.ventanaCargarPedido.jListaRellenos.setSelectedValue(taco.getIdRelleno2().getNombre(), true);
                if(taco.getIdRelleno3()!=null){
                this.ventanaCargarPedido.jListaRellenos.setSelectedValue(taco.getIdRelleno3().getNombre(), true);
                }
            }
        }else{
            this.ventanaCargarPedido.jEliminarPedido.setEnabled(false);
            this.ventanaCargarPedido.jUpdate.setEnabled(false);
            this.ventanaCargarPedido.jDetalle.setEnabled(false);
        }

        
    }
}
