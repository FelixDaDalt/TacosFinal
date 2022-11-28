/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Model.Salsa;
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
public class ServicioSalsa {
    
    private EntityManagerFactory emf = null; // Instancio la Persistencia
    private EntityManager em = null;
    
    public ServicioSalsa(){
        if(this.emf == null){
         emf = Persistence.createEntityManagerFactory("TacosFinalPU");
        }
        if(this.em == null || !this.em.isOpen()){
            em = emf.createEntityManager();
        }
    }
    
    public void Update(Salsa salsa)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Salsa buscar = Search(salsa);
        buscar.setNombre(salsa.getNombre());
        buscar.setDescripcion(salsa.getDescripcion());
        buscar.setDisponible(salsa.getDisponible());
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Insert(Salsa salsa)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
 
        em.persist(salsa);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Delete(Salsa salsa)
    {
        try{
            if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Salsa buscar = Search(salsa);
        buscar.setisDelete(1);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public <Salsa>List getAll(){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Query consulta = em.createNamedQuery("Salsa.findAll");
        return consulta.getResultList();
    }
    
    
    public Salsa Search(Salsa salsa){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }

        return em.find(Salsa.class, salsa.getId());
        
    }
}
