package br.ufs.coleta.SISColeta.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.ufs.coleta.SISColeta.entities.CaracRio;
import br.ufs.coleta.SISColeta.model.CaracRioDAO;

@ManagedBean(name = "caracrioController")
@ViewScoped
public class CaracteristicarioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private CaracRioDAO caracrioDAO;
    private List<CaracRio> items = null;
    private CaracRio caracrio;
    private CaracRio caracrio2;

    public CaracteristicarioController() {
    }

    public CaracRio getCaracrio() {
        return caracrio;
    }

    public void setCaracrio(CaracRio selected) {
        this.caracrio = selected;
    }
    
    public CaracRio getCaracrio2() {
        return caracrio2;
    }

    public void setCaracrio2(CaracRio selected) {
        this.caracrio2 = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CaracRioDAO getDAO() {
        return caracrioDAO;
    }

    public CaracRio prepareCreate() {
        caracrio = new CaracRio();
        initializeEmbeddableKey();
        return caracrio;
    }
    
    public CaracRio prepareCreate2() {
        caracrio2 = new CaracRio();
        initializeEmbeddableKey();
        return caracrio2;
    }
    
    public void cadastrar(){
    	getDAO().save(caracrio);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('CaracteristicarioCreateDialog').hide();");
    	items = null;
    }
    
    public void cadastrar2(){
    	getDAO().save(caracrio2);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('CaracteristicarioCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	getDAO().save(caracrio);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('CaracteristicarioEditDialog').hide();");
    	items = null;
    }
    
    public void remover(){
    	try{	
    		getDAO().remove(this.caracrio);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	caracrio = null;
    }

    public List<CaracRio> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<CaracRio> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<CaracRio> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

}
