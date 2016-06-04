package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Familia;
import br.ufs.coleta.SISColeta.entities.Ordem;
import br.ufs.coleta.SISColeta.entities.Subfamilia;
import br.ufs.coleta.SISColeta.model.FamiliaDAO;
import br.ufs.coleta.SISColeta.model.OrdemDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "ordemController")
@ViewScoped
public class OrdemController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private OrdemDAO ordemDAO;
	@EJB
    private FamiliaDAO familiaDAO;
    private List<Ordem> items = null;
    private List<Familia> familia = null;
    private Ordem ordem;

    public OrdemController() {
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem selected) {
        this.ordem = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrdemDAO getDAO() {
        return ordemDAO;
    }

    public Ordem prepareCreate() {
        ordem = new Ordem();
        initializeEmbeddableKey();
        return ordem;
    }
    
    public void cadastrar(){
    	getDAO().save(ordem);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('OrdemCreateDialog').hide();");
    	items = null;
    }
    
    public void editar(){
    	getDAO().save(ordem);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('OrdemEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.ordem);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	ordem = null;
    }

    public List<Ordem> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Ordem> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Ordem> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public List<Familia> getFamilia() {
    	return this.familia;
    } 
    
    public void setFamilia(Ordem ordem){
    	if(ordem != null){
    		this.ordem = new Ordem();
    		this.ordem.setDescricao(ordem.getDescricao());
    		this.ordem.setTbClasse(ordem.getTbClasse());
    		this.ordem.setId(ordem.getId());
    		this.familia = familiaDAO.getbyOrdem(ordem.getId());
    	}
    }
    
    public void setFamilia2(List<Familia> familia){
    	this.familia = familia;
    }

}
