package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.ufs.coleta.SISColeta.entities.GenericEntity;

/**
 * TbAquatico generated by hbm2java
 */
@Entity
@Table(name = "tb_aquatico", schema = "public")
public class Aquatico implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Coleta coleta;
	private Integer idlocal_aquatico;
	private TipoAquaticoLocal tipoAquaticoLocal;

	public Aquatico() {
	}

	public Aquatico(Coleta coleta,
			TipoAquaticoLocal tipoAquaticoLocal) {
		this.coleta = coleta;
		this.tipoAquaticoLocal = tipoAquaticoLocal;
	}

	@GenericGenerator(name = "gerador", strategy = "foreign", parameters = @Parameter(name = "property", value = "tbColeta"))
	@Id
	@GeneratedValue(generator = "gerador")
	@Column(name = "idtb_coleta", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Coleta getTbColeta() {
		return this.coleta;
	}

	public void setTbColeta(Coleta coleta) {
		this.coleta = coleta;
	}

	@Column(name = "idlocal_aquatico")
	public Integer getIdLocalAquatico() {
		return this.idlocal_aquatico;
	}

	public void setidLocalAquatico(Integer idaquaticolocal) {
		this.idlocal_aquatico = idaquaticolocal;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_tipo_aquatico_local_id", nullable = false)
	public TipoAquaticoLocal getTbTipoAquaticoLocal() {
		return this.tipoAquaticoLocal;
	}

	public void setTbTipoAquaticoLocal(TipoAquaticoLocal tipoAquaticoLocal) {
		this.tipoAquaticoLocal = tipoAquaticoLocal;
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
        if (!(object instanceof Aquatico)) {
            return false;
        }
        Aquatico other = (Aquatico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
