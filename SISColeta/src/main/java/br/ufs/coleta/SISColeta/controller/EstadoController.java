package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Estado;
import br.ufs.coleta.SISColeta.entities.Municipio;
import br.ufs.coleta.SISColeta.model.EstadoDAO;
import br.ufs.coleta.SISColeta.model.MunicipioDAO;

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
	@EJB
	private MunicipioDAO municipioDAO;
    private List<Estado> items = null;
    private List<Municipio> municipios = null;
    private Estado estado;

    public EstadoController() {
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado selected) {
        this.estado = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoDAO getDAO() {
        return estadoDAO;
    }

    public Estado prepareCreate() {
        estado = new Estado();
        initializeEmbeddableKey();
        return estado;
    }
    
    public void cadastrar(){
    	getDAO().save(estado);
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.estado);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	estado = null;
    }

    public List<Estado> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }
    
    public List<Municipio> getMunicipios() {
    	if(this.estado != null){
    		this.municipios = municipioDAO.getMunicipioByEstado(this.estado.getId());
    	}
    	return municipios;
    } 

    public List<Estado> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Estado> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public void insertEstado(Estado estado){
    	this.estado = estado;
    }

}
