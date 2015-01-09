package br.ufs.coleta.SISColeta.entities;

// Generated 02/01/2015 16:46:54 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbRio generated by hbm2java
 */
@Entity
@Table(name = "tb_rio", schema = "public")
public class TbRio implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private TbBacia tbBacia;
	private String descricao;
	private Set<TbAquatico> tbAquaticos = new HashSet<TbAquatico>(0);

	public TbRio() {
	}

	public TbRio(int idtbRio, TbBacia tbBacia) {
		this.id = idtbRio;
		this.tbBacia = tbBacia;
	}

	public TbRio(int idtbRio, TbBacia tbBacia, String descricao,
			Set<TbAquatico> tbAquaticos) {
		this.id = idtbRio;
		this.tbBacia = tbBacia;
		this.descricao = descricao;
		this.tbAquaticos = tbAquaticos;
	}

	@Id
	@Column(name = "idtb_rio", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_bacia_id", nullable = false)
	public TbBacia getTbBacia() {
		return this.tbBacia;
	}

	public void setTbBacia(TbBacia tbBacia) {
		this.tbBacia = tbBacia;
	}

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbRio")
	public Set<TbAquatico> getTbAquaticos() {
		return this.tbAquaticos;
	}

	public void setTbAquaticos(Set<TbAquatico> tbAquaticos) {
		this.tbAquaticos = tbAquaticos;
	}

}
