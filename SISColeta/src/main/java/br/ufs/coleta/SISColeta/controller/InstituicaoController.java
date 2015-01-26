package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Instituicao;
import br.ufs.coleta.SISColeta.model.InstituicaoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "instituicaoController")
@SessionScoped
public class InstituicaoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private InstituicaoDAO instituicaoDAO;
    private List<Instituicao> items = null;
    private Instituicao instituicao;

    public InstituicaoController() {
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao selected) {
        this.instituicao = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstituicaoDAO getDAO() {
        return instituicaoDAO;
    }

    public Instituicao prepareCreate() {
        instituicao = new Instituicao();
        initializeEmbeddableKey();
        return instituicao;
    }
    
    public void cadastrar(){
    	getDAO().save(instituicao);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.instituicao);
    	items = null;
    	instituicao = null;
    }

    public List<Instituicao> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Instituicao> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Instituicao> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
