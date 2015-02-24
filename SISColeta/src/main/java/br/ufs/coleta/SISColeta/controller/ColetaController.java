package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.model.ColetaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "coletaController")
@SessionScoped
public class ColetaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColetaDAO coletaDAO;
    private List<Coleta> items = null;
    private Coleta coleta;

    public ColetaController() {
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta selected) {
        this.coleta = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ColetaDAO getDAO() {
        return coletaDAO;
    }

    public Coleta prepareCreate() {
        coleta = new Coleta();
        initializeEmbeddableKey();
        return coleta;
    }
    
    public void cadastrar(){
    	getDAO().save(coleta);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.coleta);
    	items = null;
    	coleta = null;
    }

    public List<Coleta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Coleta> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Coleta> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
