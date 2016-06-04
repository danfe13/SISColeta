package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.DestinoDAO;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.RetiradaDAO;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;
import br.ufs.coleta.SISColeta.model.UnidadeDAO;
import br.ufs.coleta.SISColeta.model.UsuarioDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "colecaoController")
@ViewScoped
public class ColecaoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColecaoDAO colecaoDAO;
	@EJB
    private RetiradaDAO retiradaDAO;
	@EJB
    private UsuarioDAO usuarioDAO;
	@EJB
    private EspecieDAO especieDAO;	
	@EJB
    private UnidadeDAO unidadeDAO;
	@EJB
    private DestinoDAO destinoDAO;
    private List<Colecao> items = null;
    private Colecao colecao;
    private Coleta coleta;
    private Usuario usuario;
    private String quantidade;
    private Integer lastCOD;

    public ColecaoController() {
    }

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao selected) {
        this.colecao = selected;
    }

	public Integer getLastCOD() {
		return lastCOD;
	}

	public void setLastCOD(Integer lastCOD) {
		this.lastCOD = lastCOD;
	}

	protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ColecaoDAO getDAO() {
        return colecaoDAO;
    }

    public Colecao prepareCreate() {
        colecao = new Colecao();
        initializeEmbeddableKey();
        String cod = String.valueOf(colecaoDAO.lastCOD());
        this.lastCOD = Integer.valueOf(cod.substring(5))+1;
        return colecao;
    }
    
    public void cadastrar(){
    	colecao.setTbColeta(coleta);
    	this.colecao.setTbUsuario(usuario);
    	this.colecao.setDestino(destinoDAO.findFirst());
    	this.colecao.setUnidade(unidadeDAO.findFirst());
    	this.colecao.setCodCampo("CIUFS"+this.lastCOD);
    	getDAO().save(colecao);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('ColecaoDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.colecao);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	colecao = null;
    }

    public List<Colecao> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Colecao> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Colecao> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

	public Coleta getColeta() {
		return coleta;
	}

	public void setColeta(Coleta coleta) {
		this.coleta = coleta;
	}
    
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
	}
	
	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public void insertUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void insertColeta(){
		this.colecao.setTbColeta(coleta);
	}

	public List<Usuario> getColetores(){
		return usuarioDAO.getUsuarios(coleta);
	}
	
	public List<Especie> autoCompleteEspecies(String nome){
		return especieDAO.getEspecies(nome);
	}
	
	public List<Colecao> getColecaoByColeta(Coleta coleta){
		return getDAO().getColecaoByColeta(coleta.getId());
	}
	
	public String getQuantidadeReal(int quantidade, int idcolecao){
		String doacoes = String.valueOf(retiradaDAO.doacoesByColecao(idcolecao));
		String emprestimos = String.valueOf(retiradaDAO.emprestimosByColecao(idcolecao));
		return "C="+quantidade+" E="+emprestimos+" D="+doacoes+" T="+(quantidade-(Integer.parseInt(emprestimos)+Integer.parseInt(doacoes)));
	}
	
	
}
