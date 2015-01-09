package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbInstituicao;
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
    private List<TbInstituicao> items = null;
    private TbInstituicao instituicao;

    public InstituicaoController() {
    }

    public TbInstituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(TbInstituicao selected) {
        this.instituicao = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstituicaoDAO getDAO() {
        return instituicaoDAO;
    }

    public TbInstituicao prepareCreate() {
        instituicao = new TbInstituicao();
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

    public List<TbInstituicao> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbInstituicao> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbInstituicao> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
