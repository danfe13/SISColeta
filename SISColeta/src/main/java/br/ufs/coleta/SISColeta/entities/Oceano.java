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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbOceano generated by hbm2java
 */
@Entity
@Table(name = "tb_oceano", schema = "public")
public class Oceano implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Set<Mar> mars = new HashSet<Mar>(0);

	public Oceano() {
	}

	public Oceano(int idtbOceano) {
		this.id = idtbOceano;
	}

	public Oceano(int idtbOceano, String descricao, Set<Mar> mars) {
		this.id = idtbOceano;
		this.descricao = descricao;
		this.mars = mars;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_oceano", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "descricao", length = 75)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbOceano")
	public Set<Mar> getTbMars() {
		return this.mars;
	}

	public void setTbMars(Set<Mar> mars) {
		this.mars = mars;
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
        if (!(object instanceof Oceano)) {
            return false;
        }
        Oceano other = (Oceano) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
