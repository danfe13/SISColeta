package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Bacia;
import br.ufs.coleta.SISColeta.model.BaciaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "baciaController")
@SessionScoped
public class BaciaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private BaciaDAO baciaDAO;
    private List<Bacia> items = null;
    private Bacia bacia;

    public BaciaController() {
    }

    public Bacia getBacia() {
        return bacia;
    }

    public void setBacia(Bacia selected) {
        this.bacia = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BaciaDAO getDAO() {
        return baciaDAO;
    }

    public Bacia prepareCreate() {
        bacia = new Bacia();
        initializeEmbeddableKey();
        return bacia;
    }
    
    public void cadastrar(){
    	getDAO().save(bacia);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.bacia);
    	items = null;
    	bacia = null;
    }

    public List<Bacia> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Bacia> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Bacia> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
