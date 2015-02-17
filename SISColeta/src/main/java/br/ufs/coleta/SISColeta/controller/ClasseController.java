package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Classe;
import br.ufs.coleta.SISColeta.model.ClasseDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "classeController")
@SessionScoped
public class ClasseController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ClasseDAO classeDAO;
    private List<Classe> items = null;
    private Classe classe;

    public ClasseController() {
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe selected) {
        this.classe = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClasseDAO getDAO() {
        return classeDAO;
    }

    public Classe prepareCreate() {
        classe = new Classe();
        initializeEmbeddableKey();
        return classe;
    }
    
    public void cadastrar(){
    	getDAO().save(classe);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.classe);
    	items = null;
    	classe = null;
    }

    public List<Classe> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Classe> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Classe> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
