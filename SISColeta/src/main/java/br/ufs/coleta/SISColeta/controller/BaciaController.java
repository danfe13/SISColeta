package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbBacia;
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
    private List<TbBacia> items = null;
    private TbBacia bacia;

    public BaciaController() {
    }

    public TbBacia getBacia() {
        return bacia;
    }

    public void setBacia(TbBacia selected) {
        this.bacia = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BaciaDAO getDAO() {
        return baciaDAO;
    }

    public TbBacia prepareCreate() {
        bacia = new TbBacia();
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

    public List<TbBacia> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbBacia> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbBacia> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
