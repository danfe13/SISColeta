package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.EspecieImagem;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.EspecieImagemDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "especieController")
@ViewScoped
public class EspecieController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private EspecieDAO especieDAO;
	@EJB
	private EspecieImagemDAO especieimagemDAO;
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
    		getDAO().save(especie);
    		RequestContext.getCurrentInstance().execute("PF('EspecieCreateDialog').hide()");
        	items = null;
    }
    
    public void editar(){
		getDAO().save(especie);
		RequestContext.getCurrentInstance().execute("PF('EspecieEditDialog').hide()");
    	items = null;
}
    
    public void remover(){
    	try{	
    		for(EspecieImagem imagem: especieimagemDAO.findByEspecies(this.especie.getId())){
    			especieimagemDAO.remove(imagem);
    		}
    		getDAO().remove(this.especie);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
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
