package br.ufs.coleta.SISColeta.entities;

// Generated 02/01/2015 16:46:54 by Hibernate Tools 4.0.0

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
 * TbFamilia generated by hbm2java
 */
@Entity
@Table(name = "tb_familia", schema = "public")
public class TbFamilia implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private Set<TbEspecie> tbEspecies = new HashSet<TbEspecie>(0);

	public TbFamilia() {
	}

	public TbFamilia(int idtbFamilia) {
		this.id = idtbFamilia;
	}

	public TbFamilia(int idtbFamilia, String descricao,
			Set<TbEspecie> tbEspecies) {
		this.id = idtbFamilia;
		this.descricao = descricao;
		this.tbEspecies = tbEspecies;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idtb_familia", unique = true, nullable = false)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbFamilia")
	public Set<TbEspecie> getTbEspecies() {
		return this.tbEspecies;
	}

	public void setTbEspecies(Set<TbEspecie> tbEspecies) {
		this.tbEspecies = tbEspecies;
	}

}
