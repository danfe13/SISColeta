package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbEstado;
import br.ufs.coleta.SISColeta.model.EstadoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "estadoController")
@SessionScoped
public class EstadoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private EstadoDAO estadoDAO;
    private List<TbEstado> items = null;
    private TbEstado estado;

    public EstadoController() {
    }

    public TbEstado getEstado() {
        return estado;
    }

    public void setEstado(TbEstado selected) {
        this.estado = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoDAO getDAO() {
        return estadoDAO;
    }

    public TbEstado prepareCreate() {
        estado = new TbEstado();
        initializeEmbeddableKey();
        return estado;
    }
    
    public void cadastrar(){
    	getDAO().save(estado);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.estado);
    	items = null;
    	estado = null;
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
