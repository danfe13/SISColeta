package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Unidade;
import br.ufs.coleta.SISColeta.model.UnidadeDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "unidadeController")
@SessionScoped
public class UnidadeController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private UnidadeDAO UnidadeDAO;
    private List<Unidade> items = null;
    private Unidade unidade;

    public UnidadeController() {
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade selected) {
        this.unidade = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UnidadeDAO getDAO() {
        return UnidadeDAO;
    }

    public Unidade prepareCreate() {
        unidade = new Unidade();
        initializeEmbeddableKey();
        return unidade;
    }
    
    public void cadastrar(){
    	getDAO().save(unidade);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('UnidadeCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(unidade);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('UnidadeEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.unidade);
    	items = null;
    	unidade = null;
    }

    public List<Unidade> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Unidade> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Unidade> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
