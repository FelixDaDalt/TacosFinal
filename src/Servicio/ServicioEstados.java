/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Model.Estados;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author trabajo
 */
public class ServicioEstados {
    
    private EntityManagerFactory emf = null; // Instancio la Persistencia
    private EntityManager em = null;
    
    public ServicioEstados(){
        if(this.emf == null){
         emf = Persistence.createEntityManagerFactory("TacosFinalPU");
        }
        if(this.em == null || !this.em.isOpen()){
            em = emf.createEntityManager();
        }
    }
    
    public <Estados>List getAll(){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Query consulta = em.createNamedQuery("Estados.findAll");
        return consulta.getResultList();
    }
    
    
    public Estados getById(int id){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }

        return em.find(Estados.class, id);
        
    }
}
