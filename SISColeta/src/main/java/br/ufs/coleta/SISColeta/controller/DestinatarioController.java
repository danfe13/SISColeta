package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Destinatario;
import br.ufs.coleta.SISColeta.model.DestinatarioDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.destinatario);
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
