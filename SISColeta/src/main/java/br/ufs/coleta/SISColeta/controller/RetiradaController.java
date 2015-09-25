package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.Retirada;
import br.ufs.coleta.SISColeta.entities.RetiradaColecao;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.RetiradaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "retiradaController")
@SessionScoped
public class RetiradaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RetiradaDAO retiradaDAO;
	@EJB
	private ColecaoDAO colecaoDAO;
    private List<Retirada> items = null;
    private Retirada retirada;
    private RetiradaColecao retiradaColecao;
    private Colecao colecao;
    private String obs;
    private Integer qntd;
    private Usuario usuario;

    public RetiradaController() {
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public void setRetirada(Retirada selected) {
        this.retirada = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RetiradaDAO getDAO() {
        return retiradaDAO;
    }

    public Retirada prepareCreate() {
        retirada = new Retirada();
        initializeEmbeddableKey();
        return retirada;
    }
    
    public void cadastrar(){
    	retirada.setTbUsuario(usuario);
    	getDAO().save(retirada);
    	items = null;
    }
    
    public void cadastrarRetirar(){
    	getDAO().insertRetirada(colecao.getId(), retirada.getId(), obs, qntd);
    }
    
    public void remover(){
    	getDAO().remove(this.retirada);
    	items = null;
    	retirada = null;
    }
    
    public void removerRetiradaColecao(){
    	getDAO().deleteRetiradaColecao(retiradaColecao.getTbColecao().getId(), retirada.getId());
    	retiradaColecao = null;
    }

    public List<Retirada> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Retirada> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Retirada> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

	public RetiradaColecao getRetiradaColecao() {
		return retiradaColecao;
	}

	public void setRetiradaColecao(RetiradaColecao retiradaColecao) {
		this.retiradaColecao = retiradaColecao;
	}
	
	public List<Colecao> autoCompleteEspecies(String nome){
		return colecaoDAO.getColecaoByEspecie(nome);
	}

	public Colecao getColecao() {
		return colecao;
	}

	public void setColecao(Colecao colecao) {
		this.colecao = colecao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Integer getQntd() {
		return qntd;
	}

	public void setQntd(Integer qntd) {
		this.qntd = qntd;
	}

	public List<RetiradaColecao> getRetiradaColecaoByRetirada(Retirada retirada){
		this.retirada = retirada;
		return getDAO().getRetiradaColecaoByRetirada(retirada.getId());
	}
	
	public void insertUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
}
