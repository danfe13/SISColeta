package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbEstado;
import br.ufs.coleta.SISColeta.model.EstadoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "municipioController")
@SessionScoped
public class MunicipioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private EstadoDAO municipioDAO;
    private List<TbEstado> items = null;
    private TbEstado municipio;

    public MunicipioController() {
    }

    public TbEstado getEstado() {
        return municipio;
    }

    public void setEstado(TbEstado selected) {
        this.municipio = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoDAO getDAO() {
        return municipioDAO;
    }

    public TbEstado prepareCreate() {
        municipio = new TbEstado();
        initializeEmbeddableKey();
        return municipio;
    }
    
    public void cadastrar(){
    	getDAO().save(municipio);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.municipio);
    	items = null;
    	municipio = null;
    }

    public List<TbEstado> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbEstado> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbEstado> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
