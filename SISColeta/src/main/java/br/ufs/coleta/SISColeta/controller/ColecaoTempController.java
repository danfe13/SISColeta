package br.ufs.coleta.SISColeta.controller;

import java.util.List;

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.ColecaoTemp;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.model.ColecaoTempDAO;
import br.ufs.coleta.SISColeta.model.ColetaTempDAO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "colecaoTempController")
@ViewScoped
public class ColecaoTempController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColecaoTempDAO colecaoDAO;
	private List<ColecaoTemp> items = null;
	private ColecaoTemp colecao;

    public ColecaoTempController() {
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public ColecaoTemp getColecao() {
        return colecao;
    }

    public void setColecao(ColecaoTemp selected) {
        this.colecao = selected;
    }
    
    public List<ColecaoTemp> getItems() {
        if (items == null) {
    		items = colecaoDAO.findAll();
        } 
        return items;
    }
    
    public void remover(){
	    try{	
	    	colecaoDAO.remove(this.colecao);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	colecao = null;
    }
    
	public List<ColecaoTemp> getColecaoByColeta(ColetaTemp coleta){
		return colecaoDAO.getColecaoByColeta(coleta.getId());
	}

}
