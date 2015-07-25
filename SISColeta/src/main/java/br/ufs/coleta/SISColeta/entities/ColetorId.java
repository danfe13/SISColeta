package br.ufs.coleta.SISColeta.entities;

// Generated Jul 24, 2015 11:11:15 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbColetorId generated by hbm2java
 */
@Embeddable
public class ColetorId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int coletaId;
	private int usuarioId;

	public ColetorId() {
	}

	public ColetorId(int tbColetaId, int tbUsuarioId) {
		this.coletaId = tbColetaId;
		this.usuarioId = tbUsuarioId;
	}

	@Column(name = "tb_coleta_id", nullable = false)
	public int getColetaId() {
		return this.coletaId;
	}

	public void setColetaId(int tbColetaId) {
		this.coletaId = tbColetaId;
	}

	@Column(name = "tb_usuario_id", nullable = false)
	public int getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(int tbUsuarioId) {
		this.usuarioId = tbUsuarioId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ColetorId))
			return false;
		ColetorId castOther = (ColetorId) other;

		return (this.getColetaId() == castOther.getColetaId())
				&& (this.getUsuarioId() == castOther.getUsuarioId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getColetaId();
		result = 37 * result + this.getUsuarioId();
		return result;
	}

}
