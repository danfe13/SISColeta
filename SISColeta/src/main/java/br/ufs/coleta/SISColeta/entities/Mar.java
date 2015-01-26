package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

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
 * TbMar generated by hbm2java
 */
@Entity
@Table(name = "tb_mar", schema = "public")
public class Mar implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Oceano oceano;
	private String descricao;
	private Set<Aquatico> aquaticos = new HashSet<Aquatico>(0);

	public Mar() {
	}

	public Mar(int idtbMar) {
		this.id = idtbMar;
	}

	public Mar(int idtbMar, Oceano oceano, String descricao,
			Set<Aquatico> aquaticos) {
		this.id = idtbMar;
		this.oceano = oceano;
		this.descricao = descricao;
		this.aquaticos = aquaticos;
	}

	@Id
	@Column(name = "idtb_mar", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_oceano_id")
	public Oceano getTbOceano() {
		return this.oceano;
	}

	public void setTbOceano(Oceano oceano) {
		this.oceano = oceano;
	}

	@Column(name = "descricao", length = 75)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbMar")
	public Set<Aquatico> getTbAquaticos() {
		return this.aquaticos;
	}

	public void setTbAquaticos(Set<Aquatico> aquaticos) {
		this.aquaticos = aquaticos;
	}

}
