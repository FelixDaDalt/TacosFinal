/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Model.Tortilla;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author trabajo
 */
public class ServicioTortilla {
    
    private EntityManagerFactory emf = null; // Instancio la Persistencia
    private EntityManager em = null;
    
    public ServicioTortilla(){
        if(this.emf == null){
         emf = Persistence.createEntityManagerFactory("TacosFinalPU");
        }
        if(this.em == null || !this.em.isOpen()){
            em = emf.createEntityManager();
        }
    }
    
    public void Update(Tortilla tortilla)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Tortilla buscar = Search(tortilla);
        buscar.setNombre(tortilla.getNombre());
        buscar.setDescripcion(tortilla.getDescripcion());
        buscar.setDisponible(tortilla.getDisponible());
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Insert(Tortilla tortilla)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
 
        em.persist(tortilla);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Delete(Tortilla tortilla)
    {
        try{
            if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Tortilla buscar = Search(tortilla);
        em.remove(buscar);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public <Tortilla>List getAll(){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Query consulta = em.createNamedQuery("Tortilla.findAll");
        return consulta.getResultList();
    }
    
    
    public Tortilla Search(Tortilla tortilla){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }

        return em.find(Tortilla.class, tortilla.getId());
        
    }
}
