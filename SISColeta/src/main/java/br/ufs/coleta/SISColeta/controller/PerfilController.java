package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbPerfil;
import br.ufs.coleta.SISColeta.model.PerfilDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "perfilController")
@SessionScoped
public class PerfilController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private PerfilDAO perfilDAO;
    private List<TbPerfil> items = null;
    private TbPerfil perfil;

    public PerfilController() {
    }

    public TbPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(TbPerfil selected) {
        this.perfil = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerfilDAO getDAO() {
        return perfilDAO;
    }

    public TbPerfil prepareCreate() {
        perfil = new TbPerfil();
        initializeEmbeddableKey();
        return perfil;
    }
    
    public void cadastrar(){
    	getDAO().save(perfil);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.perfil);
    	items = null;
    	perfil = null;
    }

    public List<TbPerfil> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbPerfil> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbPerfil> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
