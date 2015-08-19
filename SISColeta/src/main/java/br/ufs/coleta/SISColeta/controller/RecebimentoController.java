package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Recebimento;
import br.ufs.coleta.SISColeta.entities.Retirada;
import br.ufs.coleta.SISColeta.entities.RetiradaColecao;
import br.ufs.coleta.SISColeta.model.RecebimentoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "recebimentoController")
@SessionScoped
public class RecebimentoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RecebimentoDAO recebimentoDAO;
    private List<Recebimento> items = null;
    private Recebimento recebimento;
    private RetiradaColecao retiradaColecao;
    private Retirada retirada;

    public RecebimentoController() {
    }

    public Recebimento getRecebimento() {
        return recebimento;
    }

    public void setRecebimento(Recebimento selected) {
        this.recebimento = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecebimentoDAO getDAO() {
        return recebimentoDAO;
    }

    public Recebimento prepareCreate() {
        recebimento = new Recebimento();
        initializeEmbeddableKey();
        return recebimento;
    }
    
    public void cadastrar(){
    	retiradaColecao.setTbRetirada(retirada);
    	recebimento.setTbRetiradaColecao(retiradaColecao);
    	getDAO().save(recebimento);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.recebimento);
    	items = null;
    	recebimento = null;
    }

    public List<Recebimento> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Recebimento> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Recebimento> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public RetiradaColecao getRetiradaColecao() {
		return retiradaColecao;
	}

	public void setRetiradaColecao(RetiradaColecao retiradaColecao) {
		this.retiradaColecao = retiradaColecao;
	}

	public void insertRetiradaColecao(RetiradaColecao retiradaColecao){
		this.retiradaColecao = retiradaColecao;
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}
    
	public void insertRetirada(Retirada retirada){
		this.retirada = retirada;
	}
	
	public Boolean getRecebimentoByRetirada(Integer idretirada, Integer idcolecao){
		return getDAO().getRecebimentoByRetirada(idretirada, idcolecao).isEmpty();
	}
	
	public Recebimento getRecebimento(Integer idretirada, Integer idcolecao){
		return getDAO().getRecebimentoByRetirada(idretirada, idcolecao).get(0);
	}

}
