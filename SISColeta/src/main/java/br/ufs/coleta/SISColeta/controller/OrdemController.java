package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Ordem;
import br.ufs.coleta.SISColeta.model.OrdemDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ordemController")
@SessionScoped
public class OrdemController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private OrdemDAO ordemDAO;
    private List<Ordem> items = null;
    private Ordem ordem;

    public OrdemController() {
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem selected) {
        this.ordem = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrdemDAO getDAO() {
        return ordemDAO;
    }

    public Ordem prepareCreate() {
        ordem = new Ordem();
        initializeEmbeddableKey();
        return ordem;
    }
    
    public void cadastrar(){
    	getDAO().save(ordem);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.ordem);
    	items = null;
    	ordem = null;
    }

    public List<Ordem> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Ordem> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Ordem> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
