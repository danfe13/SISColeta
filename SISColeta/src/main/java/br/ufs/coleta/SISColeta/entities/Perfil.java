package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbPerfil generated by hbm2java
 */
@Entity
@Table(name = "tb_perfil", schema = "public")
public class Perfil implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);

	public Perfil() {
	}

	public Perfil(int idtbPerfil) {
		this.id = idtbPerfil;
	}

	public Perfil(int idtbPerfil, String descricao, Set<Usuario> usuarios) {
		this.id = idtbPerfil;
		this.descricao = descricao;
		this.usuarios = usuarios;
	}

	@Id
	@Column(name = "idtb_perfil", unique = true, nullable = false)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbPerfil")
	public Set<Usuario> getTbUsuarios() {
		return this.usuarios;
	}

	public void setTbUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
