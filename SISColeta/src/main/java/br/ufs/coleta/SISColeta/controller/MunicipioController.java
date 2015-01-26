package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Municipio;
import br.ufs.coleta.SISColeta.model.MunicipioDAO;

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
    private MunicipioDAO municipioDAO;
    private List<Municipio> items = null;
    private Municipio municipio;

    public MunicipioController() {
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio selected) {
        this.municipio = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MunicipioDAO getDAO() {
        return municipioDAO;
    }

    public Municipio prepareCreate() {
        municipio = new Municipio();
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

    public List<Municipio> getItems() {
        return items;
    }

    public List<Municipio> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Municipio> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
