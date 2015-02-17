package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.EspecieImagem;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.EspecieImagemDAO;
import br.ufs.coleta.SISColeta.util.HashGenerator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "especieController")
@SessionScoped
public class EspecieController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String diretorio = "imagens_especies/";
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
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.especie);
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

}
