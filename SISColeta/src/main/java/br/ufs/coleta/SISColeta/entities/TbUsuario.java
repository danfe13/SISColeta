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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbUsuario generated by hbm2java
 */
@Entity
@Table(name = "tb_usuario", schema = "public")
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM TbUsuario u "),
	@NamedQuery(name="Usuario.findExistente", query="SELECT u FROM TbUsuario u JOIN FETCH u.tbPessoa WHERE 1 = 1 AND u.id <> :idUsuario AND ( UPPER(u.login) = UPPER(:login) OR u.tbPessoa.cpf = :cpf OR UPPER(u.tbPessoa.email) = UPPER(:email) )"),
	@NamedQuery(name="Usuario.findByLogon", query="SELECT u FROM TbUsuario u JOIN FETCH u.tbPessoa WHERE ( UPPER(u.login) = UPPER(:login) OR u.tbPessoa.cpf = :login OR UPPER(u.tbPessoa.email) = UPPER(:login) )"),
})
public class TbUsuario implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private TbPessoa tbPessoa;
	private TbPerfil tbPerfil;
	private String login;
	private String senha;
	private Boolean ativo;
	private Set<TbColeta> tbColetas = new HashSet<TbColeta>(0);
	private Set<TbColecao> tbColecaos = new HashSet<TbColecao>(0);

	public TbUsuario() {
	}

	public TbUsuario(int idtbUsuario, TbPessoa tbPessoa, TbPerfil tbPerfil) {
		this.id = idtbUsuario;
		this.tbPessoa = tbPessoa;
		this.tbPerfil = tbPerfil;
	}

	public TbUsuario(int idtbUsuario, TbPessoa tbPessoa, TbPerfil tbPerfil,
			String login, String senha, Boolean ativo, Set<TbColeta> tbColetas,
			Set<TbColecao> tbColecaos) {
		this.id = idtbUsuario;
		this.tbPessoa = tbPessoa;
		this.tbPerfil = tbPerfil;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.tbColetas = tbColetas;
		this.tbColecaos = tbColecaos;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idtb_usuario", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "tb_pessoa_id", nullable = false)
	public TbPessoa getTbPessoa() {
		return this.tbPessoa;
	}

	public void setTbPessoa(TbPessoa tbPessoa) {
		this.tbPessoa = tbPessoa;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tb_perfil_id", referencedColumnName = "idtb_perfil",  nullable=false)
	public TbPerfil getTbPerfil() {
		return this.tbPerfil;
	}

	public void setTbPerfil(TbPerfil tbPerfil) {
		this.tbPerfil = tbPerfil;
	}

	@Column(name = "login", length = 45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "senha")
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "ativo")
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_coletor", schema = "public", joinColumns = { @JoinColumn(name = "tb_usuario_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tb_coleta_id", nullable = false, updatable = false) })
	public Set<TbColeta> getTbColetas() {
		return this.tbColetas;
	}

	public void setTbColetas(Set<TbColeta> tbColetas) {
		this.tbColetas = tbColetas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbUsuario")
	public Set<TbColecao> getTbColecaos() {
		return this.tbColecaos;
	}

	public void setTbColecaos(Set<TbColecao> tbColecaos) {
		this.tbColecaos = tbColecaos;
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
        if (!(object instanceof TbUsuario)) {
            return false;
        }
        TbUsuario other = (TbUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
	
	@Override
    public String toString() {
        return "ufs.entity.Usuario[ idUsuario=" + id + " ]";
    }

}
