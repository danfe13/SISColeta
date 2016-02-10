package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Destinatario;
import br.ufs.coleta.SISColeta.model.DestinatarioDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "destinatarioController")
@SessionScoped
public class DestinatarioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private DestinatarioDAO destinatarioDAO;
    private List<Destinatario> items = null;
    private Destinatario destinatario;

    public DestinatarioController() {
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario selected) {
        this.destinatario = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DestinatarioDAO getDAO() {
        return destinatarioDAO;
    }

    public Destinatario prepareCreate() {
        destinatario = new Destinatario();
        initializeEmbeddableKey();
        return destinatario;
    }
    
    public void cadastrar(){
    	getDAO().save(destinatario);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('DestinatarioCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(destinatario);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('DestinatarioEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.destinatario);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	destinatario = null;
    }

    public List<Destinatario> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Destinatario> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Destinatario> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
