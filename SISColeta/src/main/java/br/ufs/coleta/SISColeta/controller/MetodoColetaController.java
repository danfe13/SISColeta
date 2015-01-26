package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.model.MetodoColetaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "metodoColetaController")
@SessionScoped
public class MetodoColetaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private MetodoColetaDAO metodoColetaDAO;
    private List<MetodoColeta> items = null;
    private MetodoColeta metodoColeta;

    public MetodoColetaController() {
    }

    public MetodoColeta getMetodoColeta() {
        return metodoColeta;
    }

    public void setMetodoColeta(MetodoColeta selected) {
        this.metodoColeta = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MetodoColetaDAO getDAO() {
        return metodoColetaDAO;
    }

    public MetodoColeta prepareCreate() {
        metodoColeta = new MetodoColeta();
        initializeEmbeddableKey();
        return metodoColeta;
    }
    
    public void cadastrar(){
    	getDAO().save(metodoColeta);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.metodoColeta);
    	items = null;
    	metodoColeta = null;
    }

    public List<MetodoColeta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<MetodoColeta> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<MetodoColeta> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
