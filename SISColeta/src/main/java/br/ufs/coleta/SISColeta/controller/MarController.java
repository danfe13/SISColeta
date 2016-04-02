package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Mar;
import br.ufs.coleta.SISColeta.model.MarDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "marController")
@ViewScoped
public class MarController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private MarDAO marDAO;
    private List<Mar> items = null;
    private Mar mar;

    public MarController() {
    }

    public Mar getMar() {
        return mar;
    }

    public void setMar(Mar selected) {
        this.mar = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MarDAO getDAO() {
        return marDAO;
    }

    public Mar prepareCreate() {
        mar = new Mar();
        initializeEmbeddableKey();
        return mar;
    }
    
    public void cadastrar(){
    	getDAO().save(mar);
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.mar);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	mar = null;
    }

    public List<Mar> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Mar> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Mar> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
