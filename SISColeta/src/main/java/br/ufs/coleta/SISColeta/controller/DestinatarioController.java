package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.TbDestinatario;
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
    private List<TbDestinatario> items = null;
    private TbDestinatario destinatario;

    public DestinatarioController() {
    }

    public TbDestinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(TbDestinatario selected) {
        this.destinatario = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DestinatarioDAO getDAO() {
        return destinatarioDAO;
    }

    public TbDestinatario prepareCreate() {
        destinatario = new TbDestinatario();
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

    public List<TbDestinatario> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<TbDestinatario> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<TbDestinatario> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
