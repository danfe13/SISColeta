package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Instituicao;
import br.ufs.coleta.SISColeta.model.InstituicaoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

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
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('InstituicaoCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(instituicao);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('InstituicaoEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.instituicao);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
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
