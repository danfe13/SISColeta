package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbRetiradaColecao generated by hbm2java
 */
@Entity
@Table(name = "tb_retirada_colecao", schema = "public")
public class RetiradaColecao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RetiradaColecaoId id;
	private Colecao colecao;
	private Retirada retirada;
	private String observacao;
	private Integer quantdExemplares;
	private Set<Recebimento> recebimentos = new HashSet<Recebimento>(0);

	public RetiradaColecao() {
	}

	public RetiradaColecao(RetiradaColecaoId id, Colecao colecao,
			Retirada retirada) {
		this.id = id;
		this.colecao = colecao;
		this.retirada = retirada;
	}

	public RetiradaColecao(RetiradaColecaoId id, Colecao colecao,
			Retirada retirada, String observacao,
			Set<Recebimento> recebimentos) {
		this.id = id;
		this.colecao = colecao;
		this.retirada = retirada;
		this.observacao = observacao;
		this.recebimentos = recebimentos;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "tbColecaoId", column = @Column(name = "tb_colecao_id", nullable = false)),
			@AttributeOverride(name = "tbRetiradaId", column = @Column(name = "tb_retirada_id", nullable = false)) })
	public RetiradaColecaoId getId() {
		return this.id;
	}

	public void setId(RetiradaColecaoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_colecao_id", nullable = false, insertable = false, updatable = false)
	public Colecao getTbColecao() {
		return this.colecao;
	}

	public void setTbColecao(Colecao colecao) {
		this.colecao = colecao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_retirada_id", nullable = false, insertable = false, updatable = false)
	public Retirada getTbRetirada() {
		return this.retirada;
	}

	public void setTbRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	@Column(name = "observacao")
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Column(name = "quantd_Exemplares")
	public Integer getQuantdExemplares() {
		return this.quantdExemplares;
	}

	public void setQuantdExemplares(Integer quantdExemplares) {
		this.quantdExemplares = quantdExemplares;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbRetiradaColecao")
	public Set<Recebimento> getTbRecebimentos() {
		return this.recebimentos;
	}

	public void setTbRecebimentos(Set<Recebimento> recebimentos) {
		this.recebimentos = recebimentos;
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
        if (!(object instanceof RetiradaColecao)) {
            return false;
        }
        RetiradaColecao other = (RetiradaColecao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
