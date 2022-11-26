/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author trabajo
 */
@Entity
@Table(name = "tacos")
@NamedQueries({
    @NamedQuery(name = "Tacos.findAll", query = "SELECT t FROM Tacos t"),
    @NamedQuery(name = "Tacos.findById", query = "SELECT t FROM Tacos t WHERE t.id = :id"),
    @NamedQuery(name = "Tacos.findByFecha", query = "SELECT t FROM Tacos t WHERE t.fecha = :fecha")})
public class Tacos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Estados idEstado;
    @JoinColumn(name = "id_relleno1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Relleno idRelleno1;
    @JoinColumn(name = "id_relleno2", referencedColumnName = "id")
    @ManyToOne
    private Relleno idRelleno2;
    @JoinColumn(name = "id_relleno3", referencedColumnName = "id")
    @ManyToOne
    private Relleno idRelleno3;
    @JoinColumn(name = "id_salsa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salsa idSalsa;
    @JoinColumn(name = "id_tortilla", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tortilla idTortilla;

    public Tacos() {
    }

    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public Relleno getIdRelleno1() {
        return idRelleno1;
    }

    public void setIdRelleno1(Relleno idRelleno1) {
        this.idRelleno1 = idRelleno1;
    }

    public Relleno getIdRelleno2() {
        return idRelleno2;
    }

    public void setIdRelleno2(Relleno idRelleno2) {
        this.idRelleno2 = idRelleno2;
    }

    public Relleno getIdRelleno3() {
        return idRelleno3;
    }

    public void setIdRelleno3(Relleno idRelleno3) {
        this.idRelleno3 = idRelleno3;
    }

    public Salsa getIdSalsa() {
        return idSalsa;
    }

    public void setIdSalsa(Salsa idSalsa) {
        this.idSalsa = idSalsa;
    }

    public Tortilla getIdTortilla() {
        return idTortilla;
    }

    public void setIdTortilla(Tortilla idTortilla) {
        this.idTortilla = idTortilla;
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
        if (!(object instanceof Tacos)) {
            return false;
        }
        Tacos other = (Tacos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Tacos[ id=" + id + " ]";
    }
    
}
