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
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author trabajo
 */
public class Controller_Cocina implements ActionListener{
    
    private Cocina ventanaPrincipal = null;
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
    
    public Controller_Cocina(Cocina _ventanaPrincipal){
        // en el constructor Recibo la vista y se la asigno
        this.ventanaPrincipal = _ventanaPrincipal;
        this.ventanaPrincipal.jCocina.addActionListener(this);
        this.ventanaPrincipal.jEmpleado.addActionListener(this);
        this.ventanaPrincipal.jEncargado.addActionListener(this);
        
    }
    
    public void runView(){

        
        // metodo para inicializar la vista
        this.ventanaPrincipal.setTitle("Gestion de Tacos V 1.0 - MODO COCINA"); // le seto un titulo
        this.ventanaPrincipal.setExtendedState(this.ventanaPrincipal.MAXIMIZED_BOTH);
        this.ventanaPrincipal.setVisible(true); // la muestro

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

    @Override
    public void actionPerformed(ActionEvent evento) {
      
        
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
        
        if(this.ventanaPrincipal.jEncargado == evento.getSource())
        {
            Login vistaLogin = new Login();
            Controller_Login controladora = new Controller_Login(vistaLogin);
            controladora.runView();
            this.ventanaPrincipal.setVisible(false);
        }
        
        if(this.ventanaPrincipal.jEmpleado == evento.getSource())
        {
            Empleado vistaEmpleado = new Empleado(); // Instancion una ventana Prncipal
            Controller_Empleado controladoraEmpleado = new Controller_Empleado(vistaEmpleado); // Instancio una controladora Encargado y le paso la Vista
            controladoraEmpleado.runView(); //Llamo al metodo de la cotroladora para inicializar la vista  
            this.ventanaPrincipal.setVisible(false);
        }
    }
}

