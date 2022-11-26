/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Controller.Pedidos.Controller_CargarPedido;
import Controller.Pedidos.Controller_CocinaPedido;
import View.Internal.Internal_Tortilla;
import View.Internal.Internal_Ingredientes;
import View.Internal.Internal_Relleno;
import View.Internal.Internal_Salsa;
import View.Internal.Pedidos.Internal_Cargar;
import View.Internal.Pedidos.Internal_Cocina;
import View.Encargado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author trabajo
 */
public class Controller_Encargado implements ActionListener{
    
    private Encargado ventanaPrincipal = null;
    private Internal_Ingredientes listaIngredientes = null;
    private Internal_Tortilla cargaTortilla = null;
    private Internal_Relleno cargaRelleno = null;
    private Internal_Salsa cargaSalsa = null;
    private Internal_Cocina cocina = null;
    private Controller_Tortilla controladoraTortilla = null;
    private Controller_Relleno controladoraRelleno = null;
    private Controller_Salsa controladoraSalsa = null;
    
    private Internal_Cargar cargarPedido = null;
    private Controller_CargarPedido controladoraCargarPedido = null;
    
    public Controller_Encargado(Encargado _ventanaPrincipal){
        // en el constructor Recibo la vista y se la asigno
        this.ventanaPrincipal = _ventanaPrincipal;
        this.ventanaPrincipal.jVerIngredientes.addActionListener(this); // le aplico ActionListener al subMenu VerIngredientes de la ventanaPrincipal,para capturar el evento del clic
        this.ventanaPrincipal.jTortilla.addActionListener(this);
        this.ventanaPrincipal.jTortillasIcon.addActionListener(this);
        this.ventanaPrincipal.jRelleno.addActionListener(this);
        this.ventanaPrincipal.jSalsa.addActionListener(this);
        this.ventanaPrincipal.jCargarPedido.addActionListener(this);
        this.ventanaPrincipal.jCocina.addActionListener(this);
        this.ventanaPrincipal.jRellenosIcon.addActionListener(this);
        this.ventanaPrincipal.jSalsasIcon.addActionListener(this);
        this.ventanaPrincipal.jListadoIcon.addActionListener(this);
        
        
    }
    
    public void runView(){

        
        // metodo para inicializar la vista
        this.ventanaPrincipal.setTitle("Gestion de Tacos V 1.0 - MODO ENCARGADO"); // le seto un titulo
        this.ventanaPrincipal.setExtendedState(Encargado.MAXIMIZED_BOTH);
        this.ventanaPrincipal.setVisible(true); // la muestro

            
}

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.ventanaPrincipal.jVerIngredientes == evento.getSource() || this.ventanaPrincipal.jListadoIcon == evento.getSource()) // hago clic en ver Ingredientes,muestro la ventana
        {
            if(this.listaIngredientes == null){ // si lista de ingredientes (vista) no esta Inicializada, la inicializo
                 this.listaIngredientes = new Internal_Ingredientes();
                 Controller_Ingredientes controladora = new Controller_Ingredientes(this.listaIngredientes); //inicializo la controladora
                 controladora.run();
            }
            
            if(!this.listaIngredientes.isVisible()){ // si lista de ingredientes no esta visibible
                this.ventanaPrincipal.jPanelInterno.add(this.listaIngredientes); // le paso la vista al panel Interno de la vista Encargado
                this.listaIngredientes.setVisible(true); //la inicializo
            } 
        }
        
        if(this.ventanaPrincipal.jTortilla == evento.getSource() || this.ventanaPrincipal.jTortillasIcon== evento.getSource())
        {
            if(this.cargaTortilla == null){
                this.cargaTortilla = new Internal_Tortilla();
                this.controladoraTortilla = new Controller_Tortilla(this.cargaTortilla); 
            }
            
            if(!this.cargaTortilla.isVisible()){
                this.controladoraTortilla.run();
                this.ventanaPrincipal.jPanelInterno.add(this.cargaTortilla); 
                this.cargaTortilla.setVisible(true); 
            }   
        }
        
        if(this.ventanaPrincipal.jRelleno == evento.getSource() || this.ventanaPrincipal.jRellenosIcon== evento.getSource())
        {
            if(this.cargaRelleno == null){
                this.cargaRelleno = new Internal_Relleno();
                controladoraRelleno = new Controller_Relleno(this.cargaRelleno);
            }
            
            if(!this.cargaRelleno.isVisible()){
                controladoraRelleno.run();
                this.ventanaPrincipal.jPanelInterno.add(this.cargaRelleno); 
                this.cargaRelleno.setVisible(true); 
            }   
        }
        
        if(this.ventanaPrincipal.jSalsa == evento.getSource() || this.ventanaPrincipal.jSalsasIcon== evento.getSource())
        {
            if(this.cargaSalsa == null){
                this.cargaSalsa = new Internal_Salsa();
                this.controladoraSalsa = new Controller_Salsa(this.cargaSalsa); 
            }
            
            if(!this.cargaSalsa.isVisible()){
                this.controladoraSalsa.run();
                this.ventanaPrincipal.jPanelInterno.add(this.cargaSalsa); 
                this.cargaSalsa.setVisible(true); 
            }   
        }
        
        if(this.ventanaPrincipal.jCargarPedido == evento.getSource())
        {
            if(this.cargarPedido == null){
                this.cargarPedido = new Internal_Cargar();
                this.controladoraCargarPedido = new Controller_CargarPedido(this.cargarPedido); 
            }
            
            if(!this.cargarPedido.isVisible()){
                this.controladoraCargarPedido.run();
                this.ventanaPrincipal.jPanelInterno.add(this.cargarPedido); 
                this.cargarPedido.setVisible(true); 
            }   
        }
        
        
        if(this.ventanaPrincipal.jCocina == evento.getSource()) // hago clic en ver Ingredientes,muestro la ventana
        {
            if(this.cocina== null){ // si lista de ingredientes (vista) no esta Inicializada, la inicializo
                 this.cocina = new Internal_Cocina();
                 Controller_CocinaPedido controladora = new Controller_CocinaPedido(this.cocina); //inicializo la controladora
                 controladora.run();
            }
            
            if(!this.cocina.isVisible()){ // si lista de ingredientes no esta visibible
                this.ventanaPrincipal.jPanelInterno.add(this.cocina); // le paso la vista al panel Interno de la vista Encargado
                this.cocina.setVisible(true); //la inicializo
            } 
        }
    }
}

