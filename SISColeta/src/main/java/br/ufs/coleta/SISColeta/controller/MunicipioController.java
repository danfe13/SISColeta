package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbMunicipio;
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
    private List<TbMunicipio> items = null;
    private TbMunicipio municipio;

    public MunicipioController() {
    }

    public TbMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(TbMunicipio selected) {
        this.municipio = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MunicipioDAO getDAO() {
        return municipioDAO;
    }

    public TbMunicipio prepareCreate() {
        municipio = new TbMunicipio();
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

    public List<TbMunicipio> getItems() {
        return items;
    }

    public List<TbMunicipio> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbMunicipio> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
