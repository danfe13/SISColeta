package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.model.EspecieDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "especieController")
@SessionScoped
public class EspecieController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private EspecieDAO especieDAO;
    private List<Especie> items = null;
    private Especie especie;

    public EspecieController() {
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie selected) {
        this.especie = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecieDAO getDAO() {
        return especieDAO;
    }

    public Especie prepareCreate() {
        especie = new Especie();
        initializeEmbeddableKey();
        return especie;
    }
    
    public void cadastrar(){
    	try{
    		getDAO().save(especie);
    		RequestContext.getCurrentInstance().execute("PF('EspecieCreateDialog').hide()");
        	items = null;
    	}catch(Exception e){
    		this.adicionarMensagemErro(e.getMessage());
    	}
    }
    
    public void remover(){
    	getDAO().remove(this.especie);
    	items = null;
    	especie = null;
    }

    public List<Especie> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Especie> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Especie> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public List<Object[]> getUltimasEspecies(){
    	return especieDAO.getUltimasEspecies();
    }

}
