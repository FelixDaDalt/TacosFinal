/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Tablas.TablaRelleno;
import Model.Tablas.TablaTortilla;
import Model.Tablas.TablaSalsa;
import Repositorio.RepositorioRelleno;
import Repositorio.RepositorioSalsa;
import Repositorio.RepositorioTortilla;
import View.Internal.Internal_Ingredientes;

/**
 *
 * @author trabajo
 */
public class Controller_Ingredientes {
    
    private Internal_Ingredientes ventanalistaIngredientes = null;
    
    private TablaSalsa salsas = null;
    private TablaTortilla tortillas = null;
    private TablaRelleno rellenos = null;
     
    RepositorioRelleno repositorioRelleno = new RepositorioRelleno();
    RepositorioSalsa repositorioSalsa = new RepositorioSalsa();
    RepositorioTortilla repositorioTortilla = new RepositorioTortilla();
    
    public Controller_Ingredientes(Internal_Ingredientes _ventanalistaIngredientes){
        this.ventanalistaIngredientes = _ventanalistaIngredientes;
        this.salsas = new TablaSalsa();
        this.tortillas = new TablaTortilla();
        this.rellenos = new TablaRelleno();

    }
    
    public void run(){
      this.salsas.setSalsas(this.repositorioSalsa.getAll());
      this.rellenos.setRellenos(this.repositorioRelleno.getAll());
      this.tortillas.setTortillas(this.repositorioTortilla.getAll());
      
      this.ventanalistaIngredientes.jListaSalsas.setModel(salsas);
      this.ventanalistaIngredientes.jListaRellenos.setModel(rellenos);
      this.ventanalistaIngredientes.jListaTortillas.setModel(tortillas);
        
    }
    
}
