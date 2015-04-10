package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Pessoa;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.PessoaDAO;
import br.ufs.coleta.SISColeta.model.UsuarioDAO;
import br.ufs.coleta.SISColeta.util.HashGenerator;

import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private UsuarioDAO usuarioDAO;
	@EJB
    private PessoaDAO pessoaDAO;
    private List<Usuario> items = null;
    private Usuario usuario;
    private Usuario usuarioLogado;
    private String confirmacaosenha;
	private Pessoa pessoa;

	public UsuarioController() {
    }
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Usuario getUsuarioLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario selected) {
        this.usuario = selected;
    }
    
    public String getConfirmacaosenha() {
		return confirmacaosenha;
	}

	public void setConfirmacaosenha(String confirmacaosenha) {
		this.confirmacaosenha = confirmacaosenha;
	}

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioDAO getDAOUser() {
        return usuarioDAO;
    }
    
    private PessoaDAO getDAOPessoa() {
        return pessoaDAO;
    }

    public void prepareCreate() {
        usuario = new Usuario();
        pessoa = new Pessoa();
        initializeEmbeddableKey();
    }
    
    public void prepareToNovaSenha(Usuario usuario){
    	this.usuario = usuario;
    	this.usuario.setSenha(null);
    }
    
    public void cadastrar(){
    	if(this.validarUsuario(usuario, pessoa) && this.validarSenha(usuario)){
	    	usuario.setSenha(HashGenerator.gerar(usuario.getSenha()));
	    	Usuario u = getDAOUser().save(usuario);
	    	pessoa.setTbUsuario(u);
	    	getDAOPessoa().save(pessoa);
	    	items = null;
	    	u = null;
    	}
    }
    
    public void editar(){
    	if(this.validarUsuario(usuario, usuario.getTbPessoa())){
	    	getDAOUser().save(usuario);
	    	getDAOPessoa().save(usuario.getTbPessoa());
	    	items = null;
    	}
    }
    
    public void editarSenha(){
    	if(this.validarSenha(usuario)){
    		usuario.setSenha(HashGenerator.gerar(usuario.getSenha()));
	    	getDAOUser().save(usuario);
	    	items = null;
    	}
    }
    
    public void remover(){
    	pessoa = this.usuario.getTbPessoa();
    	getDAOPessoa().remove(this.pessoa);
    	getDAOUser().remove(this.usuario);
    	items = null;
    	usuario = null;
    	pessoa = null;
    }

    public List<Usuario> getItems() {
        if (items == null) {
    		items = getDAOUser().findAll();
        } 
        return items;
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getDAOUser().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getDAOUser().findAll();
    }
    
    private boolean validarUsuario(Usuario usuario, Pessoa pessoa) {
		boolean resultado = true;
		Usuario usuarioExistente = getDAOUser().getExistente(usuario, pessoa);
		if (usuarioExistente != null) {
			if (usuarioExistente.getLogin().toLowerCase().equals(usuario.getLogin().toLowerCase())) {
				adicionarMensagemErro("Já existe um usuário com este login.");
			}
			if (usuarioExistente.getTbPessoa().getEmail().toLowerCase().equals(pessoa.getEmail().toLowerCase())) {
				adicionarMensagemErro("Já existe um usuário com este e-mail.");
			}
			if (usuarioExistente.getTbPessoa().getCpf().toLowerCase().equals(pessoa.getCpf())) {
				adicionarMensagemErro("Já existe um usuário com este CPF.");
			}
			resultado = false;
		}
		if (!isCPFValido(pessoa.getCpf())) {
			adicionarMensagemErro("O CPF informado é inválido.");
			resultado = false;
		}
		return resultado;
	}
    
    private boolean validarSenha(Usuario usuario){
    	if (!usuario.getSenha().equals(this.confirmacaosenha)) {
			adicionarMensagemErro("A senha digitada e sua confirmação estão diferentes.");
			return false;
		}
    	return true;
    }
    
    private boolean isCPFValido(String cpf) {
		if (cpf.isEmpty()) {
			return false;
		}
		if (!Pattern.matches("[0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]-[0-9][0-9]", cpf)) {
			return false;
		}
		cpf = cpf.replace(".", "").replace(".", "").replace("-", "");
		if ((cpf.equals("00000000000"))
				|| (cpf.equals("11111111111"))
				|| (cpf.equals("22222222222"))
				|| (cpf.equals("33333333333"))
				|| (cpf.equals("44444444444"))
				|| (cpf.equals("55555555555"))
				|| (cpf.equals("66666666666"))
				|| (cpf.equals("77777777777"))
				|| (cpf.equals("88888888888"))
				|| (cpf.equals("99999999999"))
				|| (cpf.length() != 11)) {
			return false;
		}
		int resto1 = 0;
		int resto2 = 0;
		
		String digitos = cpf.substring(0, cpf.length() - 2);
		String digitosVerificadores = cpf.substring(cpf.length() - 2, cpf.length());
		for (int i = 0, length = digitos.length(); i < length; i++) {
			int digito = Integer.parseInt(digitos.substring(i, i + 1));
			
			resto1 += (10 - i) * digito;
			resto2 += (11 - i) * digito;
		}
		
		resto1 = resto1 % 11;
		
		if (resto1 < 2) {
			resto1 = 0;
		} else {
			resto1 = 11 - resto1;
		}
		
		resto2 += resto1 * 2;
		
		resto2 = resto2 % 11;
		
		if (resto2 < 2) {
			resto2 = 0;
		} else {
			resto2 = 11 - resto2;
		}
		
		return digitosVerificadores.equals(String.valueOf(resto1) + String.valueOf(resto2));
	}

}
