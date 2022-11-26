/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Controller.Pedidos.Controller_CargarPedido;
import Controller.Pedidos.Controller_CocinaPedido;
import View.Cocina;
import View.Empleado;
import View.Internal.Internal_Tortilla;
import View.Internal.Internal_Ingredientes;
import View.Internal.Internal_Relleno;
import View.Internal.Internal_Salsa;
import View.Internal.Pedidos.Internal_Cargar;
import View.Internal.Pedidos.Internal_Cocina;
import View.Encargado;
import View.Login;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


/**
 *
 * @author trabajo
 */
public class Controller_Empleado implements ActionListener{
    
    private Empleado ventanaPrincipal = null;

    
    private Internal_Cargar cargarPedido = null;
    private Controller_CargarPedido controladoraCargarPedido = null;
    
    public Controller_Empleado(Empleado _ventanaPrincipal){
        // en el constructor Recibo la vista y se la asigno
        this.ventanaPrincipal = _ventanaPrincipal;
        this.ventanaPrincipal.jCargarPedido.addActionListener(this);
        this.ventanaPrincipal.jCocina.addActionListener(this);
        this.ventanaPrincipal.jEncargado.addActionListener(this);
        
    }
    
    public void runView(){

        
        // metodo para inicializar la vista
        this.ventanaPrincipal.setTitle("Gestion de Tacos V 1.0 - Modo Empleado"); // le seto un titulo
        this.ventanaPrincipal.setExtendedState(this.ventanaPrincipal.MAXIMIZED_BOTH);
        this.ventanaPrincipal.setVisible(true); // la muestro
        
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

    @Override
    public void actionPerformed(ActionEvent evento) {
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
        
        if(this.ventanaPrincipal.jCocina == evento.getSource())
        {
            Cocina vistaCocina = new Cocina(); // Instancion una ventana Prncipal
            Controller_Cocina controladoraCocina = new Controller_Cocina(vistaCocina); // Instancio una controladora Encargado y le paso la Vista
            controladoraCocina.runView(); //Llamo al metodo de la cotroladora para inicializar la vista
            this.ventanaPrincipal.setVisible(false);
        }
        
        if(this.ventanaPrincipal.jEncargado == evento.getSource())
        {
            Login vistaLogin = new Login();
            Controller_Login controladora = new Controller_Login(vistaLogin);
            controladora.runView();
            this.ventanaPrincipal.setVisible(false);
        }
    }
}

