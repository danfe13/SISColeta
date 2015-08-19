package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Substrato;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "substratoController")
@SessionScoped
public class SubstratoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private SubstratoDAO substratoDAO;
    private List<Substrato> items = null;
    private Substrato substrato;

    public SubstratoController() {
    }

    public Substrato getSubstrato() {
        return substrato;
    }

    public void setSubstrato(Substrato selected) {
        this.substrato = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubstratoDAO getDAO() {
        return substratoDAO;
    }

    public Substrato prepareCreate() {
        substrato = new Substrato();
        initializeEmbeddableKey();
        return substrato;
    }
    
    public void cadastrar(){
    	getDAO().save(substrato);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.substrato);
    	items = null;
    	substrato = null;
    }

    public List<Substrato> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Substrato> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Substrato> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
