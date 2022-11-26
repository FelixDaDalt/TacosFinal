/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;


import Model.Tacos;
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
public class ServicioTacos {
    
    private EntityManagerFactory emf = null; 
    private EntityManager em = null;
    
    public ServicioTacos(){

         emf = Persistence.createEntityManagerFactory("TacosFinalPU");
         em = emf.createEntityManager();

    }
    
    public void Update(Tacos taco)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Tacos buscar = Search(taco);
        
        buscar.setIdTortilla(taco.getIdTortilla());
        buscar.setIdSalsa(taco.getIdSalsa());
        buscar.setIdRelleno1(taco.getIdRelleno1());
        buscar.setIdRelleno2(taco.getIdRelleno2());
        buscar.setIdRelleno3(taco.getIdRelleno3());
        buscar.setIdEstado(taco.getIdEstado());
        
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Insert(Tacos taco)
    {
        try{
           if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
 
        em.persist(taco);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void Delete(Tacos taco)
    {
        try{
            if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Tacos buscar = Search(taco);
        em.remove(buscar);
        em.getTransaction().commit();
        }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public <Tacos>List getAll(){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }
        
        Query consulta = em.createNamedQuery("Tacos.findAll");
        return consulta.getResultList();
    }
    
    
    public Tacos Search(Tacos taco){
        if(!em.getTransaction().isActive())
           {
               em.getTransaction().begin();
           }

        return em.find(Tacos.class, taco.getId());
        
    }
}
