package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbMetodoColeta;
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
    private List<TbMetodoColeta> items = null;
    private TbMetodoColeta metodoColeta;

    public MetodoColetaController() {
    }

    public TbMetodoColeta getMetodoColeta() {
        return metodoColeta;
    }

    public void setMetodoColeta(TbMetodoColeta selected) {
        this.metodoColeta = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MetodoColetaDAO getDAO() {
        return metodoColetaDAO;
    }

    public TbMetodoColeta prepareCreate() {
        metodoColeta = new TbMetodoColeta();
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

    public List<TbMetodoColeta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbMetodoColeta> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbMetodoColeta> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
