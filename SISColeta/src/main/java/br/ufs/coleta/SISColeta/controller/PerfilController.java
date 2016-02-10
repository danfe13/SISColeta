package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Perfil;
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
    private List<Perfil> items = null;
    private Perfil perfil;

    public PerfilController() {
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil selected) {
        this.perfil = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerfilDAO getDAO() {
        return perfilDAO;
    }

    public Perfil prepareCreate() {
        perfil = new Perfil();
        initializeEmbeddableKey();
        return perfil;
    }
    
    public void cadastrar(){
    	getDAO().save(perfil);
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.perfil);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	perfil = null;
    }

    public List<Perfil> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Perfil> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Perfil> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
