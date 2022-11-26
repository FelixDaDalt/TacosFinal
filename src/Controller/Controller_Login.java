/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Cocina;
import View.Empleado;
import View.Encargado;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class Controller_Login implements ActionListener{
   
    private Login ventana = null;
    
    public Controller_Login(Login _ventana){
        this.ventana = _ventana;
        this.ventana.jEncargado.addActionListener(this);
        this.ventana.jCocina.addActionListener(this);
        this.ventana.jEmpleado.addActionListener(this);
    
    }
    

    public void runView(){
        this.ventana.setVisible(true);
        
}

    @Override
    public void actionPerformed(ActionEvent evento) {
       if(this.ventana.jEncargado == evento.getSource())
       {
           /*LOGICA DE COMPROBACION DE AUTENTIFICACION*/
           String password = "12345";
           String usuario = "admin";
           
           if((this.ventana.jUsuario.getText() == null ? usuario == null : this.ventana.jUsuario.getText().equals(usuario)))
           {
               if((this.ventana.jPassword.getText() == null ? password == null : this.ventana.jPassword.getText().equals(password)))
               {
                    Encargado vistaEncargado = new Encargado(); // Instancion una ventana Prncipal
                    Controller_Encargado controladoraEncargado = new Controller_Encargado(vistaEncargado); // Instancio una controladora Encargado y le paso la Vista
                    controladoraEncargado.runView(); //Llamo al metodo de la cotroladora para inicializar la vista
                    this.ventana.setVisible(false);
                    return;
               }
               JOptionPane.showMessageDialog(null,"Clave Incorrecta");
               return;
           }
           JOptionPane.showMessageDialog(null,"Usuario Incorrecto");
           return;
       
       }
      
       if(this.ventana.jCocina == evento.getSource())
       {
         Cocina vistaCocina = new Cocina(); // Instancion una ventana Prncipal
         Controller_Cocina controladoraCocina = new Controller_Cocina(vistaCocina); // Instancio una controladora Encargado y le paso la Vista
         controladoraCocina.runView(); //Llamo al metodo de la cotroladora para inicializar la vista
         this.ventana.setVisible(false);
       }
       
       if(this.ventana.jEmpleado == evento.getSource())
       {
         Empleado vistaEmpleado = new Empleado(); // Instancion una ventana Prncipal
         Controller_Empleado controladoraEmpleado = new Controller_Empleado(vistaEmpleado); // Instancio una controladora Encargado y le paso la Vista
         controladoraEmpleado.runView(); //Llamo al metodo de la cotroladora para inicializar la vista  
         this.ventana.setVisible(false);
       }  
    }
}

