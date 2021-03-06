package br.ufs.coleta.SISColeta.entities;

// Generated 25/01/2015 11:00:24 by Hibernate Tools 4.3.1

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TbUsuario generated by hbm2java
 */
@Entity
@Table(name = "tb_usuario", schema = "public")
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u "),
	@NamedQuery(name="Usuario.findUsuario", query="SELECT u FROM Usuario u WHERE u.tbPerfil is not null"),
	@NamedQuery(name="Usuario.findColetores", query="SELECT u FROM Usuario u INNER JOIN u.tbColetas c WHERE c.id = :idcoleta"),
	@NamedQuery(name="Usuario.findExistente", query="SELECT u FROM Usuario u JOIN FETCH u.tbPessoa WHERE 1 = 1 AND u.id <> :idUsuario AND ( UPPER(u.login) = UPPER(:login) OR u.tbPessoa.cpf = :cpf OR UPPER(u.tbPessoa.email) = UPPER(:email) )"),
	@NamedQuery(name="Usuario.findFone", query="SELECT u FROM Usuario u JOIN FETCH u.tbPessoa WHERE 1 = 1 AND ( UPPER(u.tbPessoa.telefone) = UPPER(:telefone) )"),
})
public class Usuario implements GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Perfil perfil;
	private String login;
	private String senha;
	private Boolean ativo;
	private Set<Coleta> coletas = new HashSet<Coleta>(0);
	private Pessoa pessoa;
	private Set<Colecao> colecaos = new HashSet<Colecao>(0);

	public Usuario() {
	}

	public Usuario(int idtbUsuario, Perfil perfil) {
		this.id = idtbUsuario;
		this.perfil = perfil;
	}

	public Usuario(int idtbUsuario, Perfil perfil, String login,
			String senha, Boolean ativo, Set<Coleta> coletas,
			Pessoa pessoa, Set<Colecao> colecaos) {
		this.id = idtbUsuario;
		this.perfil = perfil;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.coletas = coletas;
		this.pessoa = pessoa;
		this.colecaos = colecaos;
	}

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name = "idtb_usuario", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_perfil_id", nullable = false)
	public Perfil getTbPerfil() {
		return this.perfil;
	}

	public void setTbPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Column(name = "login", length = 45)
	@NotNull(message = "Campo não pode ser vázio!")
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "senha")
	@NotNull(message = "Campo não pode ser vázio!")
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

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_coletor", schema = "public", joinColumns = { @JoinColumn(name = "tb_usuario_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tb_coleta_id", nullable = false, updatable = false) })
	public Set<Coleta> getTbColetas() {
		return this.coletas;
	}

	public void setTbColetas(Set<Coleta> coletas) {
		this.coletas = coletas;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "tbUsuario", orphanRemoval=true)
	public Pessoa getTbPessoa() {
		return this.pessoa;
	}

	public void setTbPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbUsuario")
	public Set<Colecao> getTbColecaos() {
		return this.colecaos;
	}

	public void setTbColecaos(Set<Colecao> colecaos) {
		this.colecaos = colecaos;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
