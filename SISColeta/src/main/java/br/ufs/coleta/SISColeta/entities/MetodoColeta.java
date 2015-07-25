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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * TbMetodoColeta generated by hbm2java
 */
@Entity
@Table(name = "tb_metodo_coleta", schema = "public")
public class MetodoColeta implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Set<Coleta> coletas = new HashSet<Coleta>(0);

	public MetodoColeta() {
	}

	public MetodoColeta(int idtbMetodoColeta) {
		this.id = idtbMetodoColeta;
	}

	public MetodoColeta(int idtbMetodoColeta, String descricao,
			Set<Coleta> coletas) {
		this.id = idtbMetodoColeta;
		this.descricao = descricao;
		this.coletas = coletas;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_metodo_coleta", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_metodo_coletas", schema = "public", joinColumns = { @JoinColumn(name = "tb_metodo_coleta_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tb_coleta_id", nullable = false, updatable = false) })
	public Set<Coleta> getColetas() {
		return this.coletas;
	}

	public void setColetas(Set<Coleta> coletas) {
		this.coletas = coletas;
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
        if (!(object instanceof MetodoColeta)) {
            return false;
        }
        MetodoColeta other = (MetodoColeta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
