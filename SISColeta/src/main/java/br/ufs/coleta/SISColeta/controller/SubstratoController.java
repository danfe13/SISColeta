package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Substrato;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

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
    private Substrato substrato2;

    public SubstratoController() {
    }

    public Substrato getSubstrato() {
        return substrato;
    }

    public void setSubstrato(Substrato selected) {
        this.substrato = selected;
    }
    
    public Substrato getSubstrato2() {
        return substrato2;
    }

    public void setSubstrato2(Substrato selected) {
        this.substrato2 = selected;
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
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('SubstratoCreateDialog').hide();");
    	items = null;
    }
    
    public Substrato prepareCreate2() {
        substrato2 = new Substrato();
        initializeEmbeddableKey();
        return substrato2;
    }
    
    public void cadastrar2(){
    	getDAO().save(substrato2);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('SubstratoCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(substrato);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('SubstratoEditDialog').hide();");
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
