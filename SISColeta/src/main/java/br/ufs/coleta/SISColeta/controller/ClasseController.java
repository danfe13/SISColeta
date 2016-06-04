package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Classe;
import br.ufs.coleta.SISColeta.entities.Ordem;
import br.ufs.coleta.SISColeta.model.ClasseDAO;
import br.ufs.coleta.SISColeta.model.OrdemDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "classeController")
@ViewScoped
public class ClasseController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ClasseDAO classeDAO;
	@EJB
	private OrdemDAO ordemDAO;
    private List<Classe> items = null;
    private List<Ordem> ordem = null;
    private Classe classe;

    public ClasseController() {
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe selected) {
        this.classe = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClasseDAO getDAO() {
        return classeDAO;
    }

    public Classe prepareCreate() {
        classe = new Classe();
        initializeEmbeddableKey();
        return classe;
    }
    
    public void cadastrar(){
    	getDAO().save(classe);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('ClasseCreateDialog').hide();");
    	items = null;
    }
    
    public void editar(){
    	getDAO().save(classe);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('ClasseEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.classe);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	classe = null;
    }

    public List<Classe> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Classe> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Classe> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public List<Ordem> getOrdem() {
    	return this.ordem;
    } 
    
    public void setOrdem(Classe classe){
    	if(classe != null){
    		this.classe = new Classe();
	    	this.classe.setId(classe.getId());
	    	this.classe.setDescricao(classe.getDescricao());
	    	this.classe.setTbOrdems(classe.getTbOrdems());
	    	this.ordem = ordemDAO.getbyClasse(classe.getId());
    	}
    }
    
    public void setOrdem2(List<Ordem> ordem){
    	this.ordem = ordem;
    }

}
