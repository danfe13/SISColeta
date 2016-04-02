package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Subfamilia;
import br.ufs.coleta.SISColeta.model.SubFamiliaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "subfamiliaController")
@ViewScoped
public class SubFamiliaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private SubFamiliaDAO subfamiliaDAO;
    private List<Subfamilia> items = null;
    private Subfamilia subfamilia;

    public SubFamiliaController() {
    }

    public Subfamilia getSubfamilia() {
        return subfamilia;
    }

    public void setSubfamilia(Subfamilia selected) {
        this.subfamilia = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubFamiliaDAO getDAO() {
        return subfamiliaDAO;
    }

    public Subfamilia prepareCreate() {
        subfamilia = new Subfamilia();
        initializeEmbeddableKey();
        return subfamilia;
    }
    
    public void cadastrar(){
    	getDAO().save(subfamilia);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('SubFamiliaCreateDialog').hide();");
    	items = null;
    }
    
    public void editar(){
    	getDAO().save(subfamilia);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('SubFamiliaEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{
    		getDAO().remove(this.subfamilia);
    	}
    	catch(Exception sqlex){
    		this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
    	}
    	items = null;
    	subfamilia = null;
    }

    public List<Subfamilia> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Subfamilia> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Subfamilia> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
