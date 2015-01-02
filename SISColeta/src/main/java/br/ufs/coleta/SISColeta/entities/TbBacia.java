package br.ufs.coleta.SISColeta.entities;

// Generated 02/01/2015 16:46:54 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbBacia generated by hbm2java
 */
@Entity
@Table(name = "tb_bacia", schema = "public")
public class TbBacia implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private Set<TbRio> tbRios = new HashSet<TbRio>(0);

	public TbBacia() {
	}

	public TbBacia(int idtbBacia) {
		this.id = idtbBacia;
	}

	public TbBacia(int idtbBacia, String descricao, Set<TbRio> tbRios) {
		this.id = idtbBacia;
		this.descricao = descricao;
		this.tbRios = tbRios;
	}

	@Id
	@Column(name = "idtb_bacia", unique = true, nullable = false)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbBacia")
	public Set<TbRio> getTbRios() {
		return this.tbRios;
	}

	public void setTbRios(Set<TbRio> tbRios) {
		this.tbRios = tbRios;
	}

}
