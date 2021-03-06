package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbRecebimento generated by hbm2java
 */
@Entity
@Table(name = "tb_recebimento", schema = "public")
@NamedQueries({
	@NamedQuery(name="Recebimento.findByRetirada", query="SELECT r FROM Recebimento r WHERE r.tbRetiradaColecao.tbColecao.id = :idcolecao AND r.tbRetiradaColecao.tbRetirada.id = :idretirada"),
})
public class Recebimento implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private RetiradaColecao retiradaColecao;
	private Date dataRecebimento;
	private String observacao;
	private Integer quantdExemplares;

	public Recebimento() {
	}

	public Recebimento(int idtbRecebimento,
			RetiradaColecao retiradaColecao) {
		this.id = idtbRecebimento;
		this.retiradaColecao = retiradaColecao;
	}

	public Recebimento(int idtbRecebimento,
			RetiradaColecao retiradaColecao, Date dataRecebimento,
			String observacao) {
		this.id = idtbRecebimento;
		this.retiradaColecao = retiradaColecao;
		this.dataRecebimento = dataRecebimento;
		this.observacao = observacao;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_recebimento", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "tb_colecao_id", referencedColumnName = "tb_colecao_id", nullable = false),
			@JoinColumn(name = "tb_retirada_id", referencedColumnName = "tb_retirada_id", nullable = false) })
	public RetiradaColecao getTbRetiradaColecao() {
		return this.retiradaColecao;
	}

	public void setTbRetiradaColecao(RetiradaColecao retiradaColecao) {
		this.retiradaColecao = retiradaColecao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_recebimento", length = 13)
	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
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
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recebimento)) {
            return false;
        }
        Recebimento other = (Recebimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
