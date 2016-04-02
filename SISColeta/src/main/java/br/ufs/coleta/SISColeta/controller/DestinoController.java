package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Destino;
import br.ufs.coleta.SISColeta.model.DestinoDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "destinoController")
@ViewScoped
public class DestinoController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private DestinoDAO destinoDAO;
    private List<Destino> items = null;
    private Destino destino;

    public DestinoController() {
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino selected) {
        this.destino = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DestinoDAO getDAO() {
        return destinoDAO;
    }

    public Destino prepareCreate() {
        destino = new Destino();
        initializeEmbeddableKey();
        return destino;
    }
    
    public void cadastrar(){
    	getDAO().save(destino);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('DestinoCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(destino);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('DestinoEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.destino);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	destino = null;
    }

    public List<Destino> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Destino> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Destino> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
