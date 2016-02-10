package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Familia;
import br.ufs.coleta.SISColeta.entities.Ordem;
import br.ufs.coleta.SISColeta.entities.Subfamilia;
import br.ufs.coleta.SISColeta.model.FamiliaDAO;
import br.ufs.coleta.SISColeta.model.SubFamiliaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "familiaController")
@SessionScoped
public class FamiliaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private FamiliaDAO familiaDAO;
	@EJB
    private SubFamiliaDAO subfamiliaDAO;
    private List<Familia> items = null;
    private List<Subfamilia> subfamilia = null;
    private Familia familia;

    public FamiliaController() {
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia selected) {
        this.familia = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FamiliaDAO getDAO() {
        return familiaDAO;
    }

    public Familia prepareCreate() {
        familia = new Familia();
        initializeEmbeddableKey();
        return familia;
    }
    
    public void cadastrar(){
    	getDAO().save(familia);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('FamiliaCreateDialog').hide();");
    	items = null;
    }
    
    public void editar(){
    	getDAO().save(familia);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('FamiliaEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.familia);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	familia = null;
    }

    public List<Familia> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Familia> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Familia> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public List<Subfamilia> getSubfamilia() {
    	if(this.familia != null){
    		this.subfamilia = subfamiliaDAO.getbyFamilia(this.familia.getId());
    	}
    	return this.subfamilia;
    } 

}
