package br.ufs.coleta.SISColeta.entities;

// Generated Jul 29, 2015 1:49:01 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TbSubstratos generated by hbm2java
 */
@Entity
@Table(name = "tb_substratos", schema = "public")
public class Substratos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SubstratosId id;
	private Coleta coleta;
	private Substrato substrato;
	private Integer abundancia;

	public Substratos() {
	}

	public Substratos(SubstratosId id, Coleta tbColeta,
			Substrato tbSubstrato) {
		this.id = id;
		this.coleta = tbColeta;
		this.substrato = tbSubstrato;
	}

	public Substratos(SubstratosId id, Coleta tbColeta,
			Substrato tbSubstrato, Integer abundancia) {
		this.id = id;
		this.coleta = tbColeta;
		this.substrato = tbSubstrato;
		this.abundancia = abundancia;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "tbSubstratoId", column = @Column(name = "tb_substrato_id", nullable = false)),
			@AttributeOverride(name = "tbColetaId", column = @Column(name = "tb_coleta_id", nullable = false)) })
	public SubstratosId getId() {
		return this.id;
	}

	public void setId(SubstratosId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_coleta_id", nullable = false, insertable = false, updatable = false)
	public Coleta getColeta() {
		return this.coleta;
	}

	public void setColeta(Coleta tbColeta) {
		this.coleta = tbColeta;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_substrato_id", nullable = false, insertable = false, updatable = false)
	public Substrato getTbSubstrato() {
		return this.substrato;
	}

	public void setTbSubstrato(Substrato tbSubstrato) {
		this.substrato = tbSubstrato;
	}

	@Column(name = "abundancia")
	public Integer getAbundancia() {
		return this.abundancia;
	}

	public void setAbundancia(Integer abundancia) {
		this.abundancia = abundancia;
	}

}
