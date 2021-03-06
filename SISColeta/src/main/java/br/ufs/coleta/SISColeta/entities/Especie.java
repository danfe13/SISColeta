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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.NullArgumentException;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * TbEspecie generated by hbm2java
 */
@Entity
@Table(name = "tb_especie", schema = "public")
@NamedQueries({
	@NamedQuery(name="Especie.findByNome", query="SELECT e FROM Especie e WHERE UPPER(e.nomeCientifico) LIKE :nome OR UPPER(e.nomePopular) LIKE :nome "),
	@NamedQuery(name="Especie.findMaisColetada", query="SELECT SUM(c.quantidade) as total, e.nomeCientifico FROM Especie e INNER JOIN e.tbColecaos c GROUP BY e ORDER BY total DESC"),
})
public class Especie implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Subfamilia subfamilia;
	private String nomeCientifico;
	private String nomePopular;
	private Set<EspecieImagem> especieImagems = new HashSet<EspecieImagem>(
			0);
	private Set<Colecao> colecaos = new HashSet<Colecao>(0);

	public Especie() {
	}

	public Especie(int idtbEspecie, Subfamilia subfamilia) {
		this.id = idtbEspecie;
		this.subfamilia = subfamilia;
	}

	public Especie(int idtbEspecie, Subfamilia subfamilia,
			String nomeCientifico, String nomePopular,
			Set<EspecieImagem> especieImagems, Set<Colecao> colecaos) {
		this.id = idtbEspecie;
		this.subfamilia = subfamilia;
		this.nomeCientifico = nomeCientifico;
		this.nomePopular = nomePopular;
		this.especieImagems = especieImagems;
		this.colecaos = colecaos;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_especie", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_subfamilia_id",referencedColumnName = "idtb_subfamilia", nullable = true)
	public Subfamilia getTbSubfamilia() {
		return this.subfamilia;
	}

	public void setTbSubfamilia(Subfamilia subfamilia) {
		this.subfamilia = subfamilia;
	}

	@Column(name = "nome_cientifico", length = 75)
	@NotNull(message = "Campo não pode ser vázio!")
	@NotEmpty(message = "Não pode ser vázio!")
	public String getNomeCientifico() {
		return this.nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	@Column(name = "nome_popular", length = 75)
	@NotNull(message = "Campo não pode ser vázio!")
	@NotEmpty(message = "Não pode ser vázio!")
	public String getNomePopular() {
		return this.nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbEspecie")
	public Set<EspecieImagem> getTbEspecieImagems() {
		return this.especieImagems;
	}

	public void setTbEspecieImagems(Set<EspecieImagem> especieImagems) {
		this.especieImagems = especieImagems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbEspecie")
	public Set<Colecao> getTbColecaos() {
		return this.colecaos;
	}

	public void setTbColecaos(Set<Colecao> colecaos) {
		this.colecaos = colecaos;
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
        if (!(object instanceof Especie)) {
            return false;
        }
        Especie other = (Especie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
