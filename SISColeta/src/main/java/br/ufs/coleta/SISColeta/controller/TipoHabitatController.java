package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TipoHabitat;
import br.ufs.coleta.SISColeta.model.TipoHabitatDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoHabitatController")
@SessionScoped
public class TipoHabitatController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private TipoHabitatDAO tipoHabitatDAO;
    private List<TipoHabitat> items = null;
    private TipoHabitat tipoHabitat;

    public TipoHabitatController() {
    }

    public TipoHabitat getTipoHabitat() {
        return tipoHabitat;
    }

    public void setTipoHabitat(TipoHabitat selected) {
        this.tipoHabitat = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoHabitatDAO getDAO() {
        return tipoHabitatDAO;
    }

    public TipoHabitat prepareCreate() {
        tipoHabitat = new TipoHabitat();
        initializeEmbeddableKey();
        return tipoHabitat;
    }
    
    public void cadastrar(){
    	getDAO().save(tipoHabitat);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.tipoHabitat);
    	items = null;
    	tipoHabitat = null;
    }

    public List<TipoHabitat> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TipoHabitat> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TipoHabitat> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
