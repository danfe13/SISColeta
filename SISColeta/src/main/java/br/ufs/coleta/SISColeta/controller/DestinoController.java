package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Destino;
import br.ufs.coleta.SISColeta.model.DestinoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "destinoController")
@SessionScoped
public class DestinoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private DestinoDAO destinoDAO;
    private List<Destino> items = null;
    private Destino destino;

    public DestinoController() {
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino selected) {
        this.destino = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DestinoDAO getDAO() {
        return destinoDAO;
    }

    public Destino prepareCreate() {
        destino = new Destino();
        initializeEmbeddableKey();
        return destino;
    }
    
    public void cadastrar(){
    	getDAO().save(destino);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.destino);
    	items = null;
    	destino = null;
    }

    public List<Destino> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Destino> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Destino> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
