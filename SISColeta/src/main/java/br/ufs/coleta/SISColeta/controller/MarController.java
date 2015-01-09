package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbMar;
import br.ufs.coleta.SISColeta.model.MarDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "marController")
@SessionScoped
public class MarController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private MarDAO marDAO;
    private List<TbMar> items = null;
    private TbMar mar;

    public MarController() {
    }

    public TbMar getMar() {
        return mar;
    }

    public void setMar(TbMar selected) {
        this.mar = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MarDAO getDAO() {
        return marDAO;
    }

    public TbMar prepareCreate() {
        mar = new TbMar();
        initializeEmbeddableKey();
        return mar;
    }
    
    public void cadastrar(){
    	getDAO().save(mar);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.mar);
    	items = null;
    	mar = null;
    }

    public List<TbMar> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbMar> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbMar> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
