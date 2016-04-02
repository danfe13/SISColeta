package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Oceano;
import br.ufs.coleta.SISColeta.model.OceanoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "oceanoController")
@ViewScoped
public class OceanoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private OceanoDAO oceanoDAO;
    private List<Oceano> items = null;
    private Oceano oceano;

    public OceanoController() {
    }

    public Oceano getOceano() {
        return oceano;
    }

    public void setOceano(Oceano selected) {
        this.oceano = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OceanoDAO getDAO() {
        return oceanoDAO;
    }

    public Oceano prepareCreate() {
        oceano = new Oceano();
        initializeEmbeddableKey();
        return oceano;
    }
    
    public void cadastrar(){
    	getDAO().save(oceano);
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.oceano);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	oceano = null;
    }

    public List<Oceano> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Oceano> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Oceano> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
