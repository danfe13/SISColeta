package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TbBacia generated by hbm2java
 */
@Entity
@Table(name = "tb_bacia", schema = "public")
@NamedQueries({
	@NamedQuery(name="Bacia.findById", query="SELECT b FROM Bacia b INNER JOIN b.tbRios r WHERE r.id = :id "),
})
public class Bacia implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Set<Rio> rios = new HashSet<Rio>(0);

	public Bacia() {
	}

	public Bacia(int idtbBacia) {
		this.id = idtbBacia;
	}

	public Bacia(int idtbBacia, String descricao, Set<Rio> rios) {
		this.id = idtbBacia;
		this.descricao = descricao;
		this.rios = rios;
	}
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_bacia", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "descricao", length = 75, nullable = false)
	@NotNull(message = "Campo não pode ser vázio!")
	@NotEmpty(message = "Não pode ser vázio!")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbBacia")
	public Set<Rio> getTbRios() {
		return this.rios;
	}

	public void setTbRios(Set<Rio> rios) {
		this.rios = rios;
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
        if (!(object instanceof Bacia)) {
            return false;
        }
        Bacia other = (Bacia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
