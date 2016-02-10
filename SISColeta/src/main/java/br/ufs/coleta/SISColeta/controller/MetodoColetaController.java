package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.model.MetodoColetaDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "metodoColetaController")
@SessionScoped
public class MetodoColetaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private MetodoColetaDAO metodoColetaDAO;
    private List<MetodoColeta> items = null;
    private MetodoColeta metodoColeta;
    private MetodoColeta metodoColeta2;

    public MetodoColetaController() {
    }

    public MetodoColeta getMetodoColeta() {
        return metodoColeta;
    }

    public void setMetodoColeta(MetodoColeta selected) {
        this.metodoColeta = selected;
    }
    
    public MetodoColeta getMetodoColeta2() {
        return metodoColeta2;
    }

    public void setMetodoColeta2(MetodoColeta selected) {
        this.metodoColeta2 = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MetodoColetaDAO getDAO() {
        return metodoColetaDAO;
    }

    public MetodoColeta prepareCreate() {
        metodoColeta = new MetodoColeta();
        initializeEmbeddableKey();
        return metodoColeta;
    }
    
    public MetodoColeta prepareCreate2() {
        metodoColeta2 = new MetodoColeta();
        initializeEmbeddableKey();
        return metodoColeta2;
    }
    
    public void cadastrar(){
    	getDAO().save(metodoColeta);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('MetodoColetaCreateDialog').hide();");
    	items = null;
    }
    
    public void cadastrar2(){
    	getDAO().save(metodoColeta2);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('MetodoColetaCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(metodoColeta);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('MetodoColetaEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.metodoColeta);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	metodoColeta = null;
    }

    public List<MetodoColeta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<MetodoColeta> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<MetodoColeta> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
