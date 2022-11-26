/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tacosfinal;

import Controller.Controller_Login;
import View.Login;

/**
 *
 * @author trabajo
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login vistaLogin = new Login();
        Controller_Login controladora = new Controller_Login(vistaLogin);
        controladora.runView();
        
    }
    
}
