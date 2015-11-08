package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Rio;
import br.ufs.coleta.SISColeta.model.RioDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "rioController")
@SessionScoped
public class RioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RioDAO rioDAO;
    private List<Rio> items = null;
    private Rio rio;
    private Rio rio2;

    public RioController() {
    }

    public Rio getRio() {
        return rio;
    }

    public void setRio(Rio selected) {
        this.rio = selected;
    }

    public Rio getRio2() {
        return rio2;
    }

    public void setRio2(Rio selected) {
        this.rio2 = selected;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RioDAO getDAO() {
        return rioDAO;
    }

    public Rio prepareCreate() {
        rio = new Rio();
        initializeEmbeddableKey();
        return rio;
    }
    
    public Rio prepareCreate2() {
        rio2 = new Rio();
        initializeEmbeddableKey();
        return rio2;
    }
    
    public void cadastrar(){
    	getDAO().save(rio);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('RioCreateDialog').hide();");
    	items = null;
    }
    
    public void cadastrar2(){
    	getDAO().save(rio2);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('ColetaRioCadastroDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(rio);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('RioEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.rio);
    	items = null;
    	rio = null;
    }

    public List<Rio> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Rio> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Rio> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
