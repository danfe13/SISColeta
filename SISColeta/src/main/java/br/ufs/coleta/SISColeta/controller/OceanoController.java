package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbOceano;
import br.ufs.coleta.SISColeta.model.OceanoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "oceanoController")
@SessionScoped
public class OceanoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private OceanoDAO oceanoDAO;
    private List<TbOceano> items = null;
    private TbOceano oceano;

    public OceanoController() {
    }

    public TbOceano getOceano() {
        return oceano;
    }

    public void setOceano(TbOceano selected) {
        this.oceano = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OceanoDAO getDAO() {
        return oceanoDAO;
    }

    public TbOceano prepareCreate() {
        oceano = new TbOceano();
        initializeEmbeddableKey();
        return oceano;
    }
    
    public void cadastrar(){
    	getDAO().save(oceano);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.oceano);
    	items = null;
    	oceano = null;
    }

    public List<TbOceano> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbOceano> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbOceano> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
