/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author trabajo
 */
@Entity
@Table(name = "salsa")
@NamedQueries({
    @NamedQuery(name = "Salsa.findAll", query = "SELECT s FROM Salsa s"),
    @NamedQuery(name = "Salsa.findById", query = "SELECT s FROM Salsa s WHERE s.id = :id"),
    @NamedQuery(name = "Salsa.findByNombre", query = "SELECT s FROM Salsa s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Salsa.findByDescripcion", query = "SELECT s FROM Salsa s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Salsa.findByPicor", query = "SELECT s FROM Salsa s WHERE s.picor = :picor"),
    @NamedQuery(name = "Salsa.findByDisponible", query = "SELECT s FROM Salsa s WHERE s.disponible = :disponible")})
public class Salsa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "picor")
    private int picor;
    @Basic(optional = false)
    @Column(name = "disponible")
    private boolean disponible;
    @Basic(optional = false)
    @Column(name = "isDelete")
    private int isDelete;
    
    public Salsa() {
    }


    public Salsa(Integer id, String nombre, int picor, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.picor = picor;
        this.disponible = disponible;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPicor() {
        return picor;
    }

    public void setPicor(int picor) {
        this.picor = picor;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

     public void setisDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    
    public int getisDelete() {
        return this.isDelete;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salsa)) {
            return false;
        }
        Salsa other = (Salsa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
