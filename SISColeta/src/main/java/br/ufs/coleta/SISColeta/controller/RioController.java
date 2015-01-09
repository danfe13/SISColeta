package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbRio;
import br.ufs.coleta.SISColeta.model.RioDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "rioController")
@SessionScoped
public class RioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RioDAO rioDAO;
    private List<TbRio> items = null;
    private TbRio rio;

    public RioController() {
    }

    public TbRio getRio() {
        return rio;
    }

    public void setRio(TbRio selected) {
        this.rio = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RioDAO getDAO() {
        return rioDAO;
    }

    public TbRio prepareCreate() {
        rio = new TbRio();
        initializeEmbeddableKey();
        return rio;
    }
    
    public void cadastrar(){
    	getDAO().save(rio);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.rio);
    	items = null;
    	rio = null;
    }

    public List<TbRio> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbRio> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbRio> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
