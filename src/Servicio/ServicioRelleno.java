/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Model.Relleno;
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
public class ServicioRelleno {
    
    private EntityManagerFactory emf = null; // Instancio la Persistencia
    private EntityManager em = null;
    
    public ServicioRelleno(){
        if(this.emf == null){
         emf = Persistence.createEntityManagerFactory("TacosFinalPU");
        }
        if(this.em == null || !this.em.isOpen()){
            em = emf.createEntityManager();
        }
    }
    
    public void Update(Relleno relleno)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Relleno buscar = Search(relleno);
        buscar.setNombre(relleno.getNombre());
        buscar.setDescripcion(relleno.getDescripcion());
        buscar.setDisponible(relleno.getDisponible());
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Insert(Relleno relleno)
    {
        try{
            if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
 
        em.persist(relleno);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Delete(Relleno relleno)
    {
        try{
            if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Relleno buscar = Search(relleno);
        buscar.setisDelete(1);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public <Relleno>List getAll(){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Query consulta = em.createNamedQuery("Relleno.findAll");
        return consulta.getResultList();
    }
    
    
    public Relleno Search(Relleno relleno){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }

        return em.find(Relleno.class, relleno.getId());
        
    }
}
