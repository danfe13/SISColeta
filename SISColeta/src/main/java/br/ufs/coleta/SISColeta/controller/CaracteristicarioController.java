package br.ufs.coleta.SISColeta.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufs.coleta.SISColeta.entities.CaracRio;
import br.ufs.coleta.SISColeta.model.CaracRioDAO;

@ManagedBean(name = "caracrioController")
@SessionScoped
public class CaracteristicarioController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private CaracRioDAO caracrioDAO;
    private List<CaracRio> items = null;
    private CaracRio caracrio;

    public CaracteristicarioController() {
    }

    public CaracRio getCaracrio() {
        return caracrio;
    }

    public void setCaracrio(CaracRio selected) {
        this.caracrio = selected;
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
    
    public void cadastrar(){
    	getDAO().save(caracrio);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.caracrio);
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
