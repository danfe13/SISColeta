package br.ufs.coleta.SISColeta.entities;

// Generated Jul 29, 2015 1:49:01 PM by Hibernate Tools 4.3.1

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
 * TbSubstrato generated by hbm2java
 */
@Entity
@Table(name = "tb_substrato", schema = "public")
public class Substrato implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Set<Substratos> tbSubstratoses = new HashSet<Substratos>(0);

	public Substrato() {
	}

	public Substrato(Integer idtbSubstrato) {
		this.id = idtbSubstrato;
	}

	public Substrato(Integer idtbSubstrato, String descricao,
			Set<Substratos> tbSubstratoses) {
		this.id = idtbSubstrato;
		this.descricao = descricao;
		this.tbSubstratoses = tbSubstratoses;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_substrato", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idtbSubstrato) {
		this.id = idtbSubstrato;
	}

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbSubstrato")
	public Set<Substratos> getTbSubstratoses() {
		return this.tbSubstratoses;
	}

	public void setTbSubstratoses(Set<Substratos> tbSubstratoses) {
		this.tbSubstratoses = tbSubstratoses;
	}

}
