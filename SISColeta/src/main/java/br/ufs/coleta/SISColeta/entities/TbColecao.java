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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbColecao generated by hbm2java
 */
@Entity
@Table(name = "tb_colecao", schema = "public")
public class TbColecao implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private TbUsuario tbUsuario;
	private TbColeta tbColeta;
	private TbEspecie tbEspecie;
	private Integer quantidade;
	private Integer unidade;
	private Integer destinacao;
	private String observacao;
	private Set<TbRetiradaColecao> tbRetiradaColecaos = new HashSet<TbRetiradaColecao>(
			0);
	private Set<TbColecaoImagem> tbColecaoImagems = new HashSet<TbColecaoImagem>(
			0);

	public TbColecao() {
	}

	public TbColecao(int idtbColecao, TbUsuario tbUsuario, TbColeta tbColeta,
			TbEspecie tbEspecie) {
		this.id = idtbColecao;
		this.tbUsuario = tbUsuario;
		this.tbColeta = tbColeta;
		this.tbEspecie = tbEspecie;
	}

	public TbColecao(int idtbColecao, TbUsuario tbUsuario, TbColeta tbColeta,
			TbEspecie tbEspecie, Integer quantidade, Integer unidade,
			Integer destinacao, String observacao,
			Set<TbRetiradaColecao> tbRetiradaColecaos,
			Set<TbColecaoImagem> tbColecaoImagems) {
		this.id = idtbColecao;
		this.tbUsuario = tbUsuario;
		this.tbColeta = tbColeta;
		this.tbEspecie = tbEspecie;
		this.quantidade = quantidade;
		this.unidade = unidade;
		this.destinacao = destinacao;
		this.observacao = observacao;
		this.tbRetiradaColecaos = tbRetiradaColecaos;
		this.tbColecaoImagems = tbColecaoImagems;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idtb_colecao", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_usuario_id", nullable = false)
	public TbUsuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_coleta_id", nullable = false)
	public TbColeta getTbColeta() {
		return this.tbColeta;
	}

	public void setTbColeta(TbColeta tbColeta) {
		this.tbColeta = tbColeta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_especie_id", nullable = false)
	public TbEspecie getTbEspecie() {
		return this.tbEspecie;
	}

	public void setTbEspecie(TbEspecie tbEspecie) {
		this.tbEspecie = tbEspecie;
	}

	@Column(name = "quantidade")
	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "unidade")
	public Integer getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	@Column(name = "destinacao")
	public Integer getDestinacao() {
		return this.destinacao;
	}

	public void setDestinacao(Integer destinacao) {
		this.destinacao = destinacao;
	}

	@Column(name = "observacao")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbColecao")
	public Set<TbRetiradaColecao> getTbRetiradaColecaos() {
		return this.tbRetiradaColecaos;
	}

	public void setTbRetiradaColecaos(Set<TbRetiradaColecao> tbRetiradaColecaos) {
		this.tbRetiradaColecaos = tbRetiradaColecaos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbColecao")
	public Set<TbColecaoImagem> getTbColecaoImagems() {
		return this.tbColecaoImagems;
	}

	public void setTbColecaoImagems(Set<TbColecaoImagem> tbColecaoImagems) {
		this.tbColecaoImagems = tbColecaoImagems;
	}

}
