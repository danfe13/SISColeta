package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TipoAquaticoLocal;
import br.ufs.coleta.SISColeta.model.TipoAquaticoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoAquaticoController")
@SessionScoped
public class TipoAquaticoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private TipoAquaticoDAO tipoAquaticoDAO;
    private List<TipoAquaticoLocal> items = null;
    private TipoAquaticoLocal tipoAquatico;

    public TipoAquaticoController() {
    }

    public TipoAquaticoLocal getTipoAquaticoLocal() {
        return tipoAquatico;
    }

    public void setTipoAquaticoLocal(TipoAquaticoLocal selected) {
        this.tipoAquatico = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoAquaticoDAO getDAO() {
        return tipoAquaticoDAO;
    }

    public TipoAquaticoLocal prepareCreate() {
        tipoAquatico = new TipoAquaticoLocal();
        initializeEmbeddableKey();
        return tipoAquatico;
    }
    
    public void cadastrar(){
    	getDAO().save(tipoAquatico);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.tipoAquatico);
    	items = null;
    	tipoAquatico = null;
    }

    public List<TipoAquaticoLocal> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TipoAquaticoLocal> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TipoAquaticoLocal> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
