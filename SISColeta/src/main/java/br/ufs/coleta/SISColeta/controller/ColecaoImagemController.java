package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.ColecaoImagem;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.ColecaoImagemDAO;
import br.ufs.coleta.SISColeta.util.HashGenerator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "colecaoimagemController")
@SessionScoped
public class ColecaoImagemController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String diretorio = "imagens_colecao";
	@EJB
	private ColecaoImagemDAO colecaoimagemDAO;
	@EJB
	private ColecaoDAO colecaoDAO;
    private List<ColecaoImagem> items = null;
    private ColecaoImagem colecaoimagem;
    private Integer id;

	public ColecaoImagemController() {
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public ColecaoImagem getColecaoImagem() {
        return colecaoimagem;
    }

    public void setColecaoImagem(ColecaoImagem selected) {
        this.colecaoimagem = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ColecaoImagemDAO getDAO() {
        return colecaoimagemDAO;
    }

    public ColecaoImagem prepareCreate() {
        colecaoimagem = new ColecaoImagem();
        initializeEmbeddableKey();
        return colecaoimagem;
    }
    
    public void cadastrar(){
    	getDAO().save(colecaoimagem);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.colecaoimagem);
    	items = null;
    	colecaoimagem = null;
    }
    
    public List<ColecaoImagem> getItems(){
    	return items;
    }

    public void checkList() {
        if (id == null) {
    		return;
        } 
        items = getDAO().findByColecaos(id);
    }

    public List<ColecaoImagem> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<ColecaoImagem> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }
    
    public void fileUpload(FileUploadEvent event) throws IOException
    {
	    try
	    {
	    	File dir = new File(diretorio);
	    	
	    	if(!dir.exists()){
	    		dir.mkdir();
	    	}
	    	
		    UploadedFile arq = event.getFile();
		    String nomeArquivo = arq.getFileName();
		    String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".")+1, nomeArquivo.length());
		    
		    Date data = new Date(System.currentTimeMillis());
		    nomeArquivo = HashGenerator.gerar(arq.getFileName()+data.getTime());
		    
		    InputStream in = new BufferedInputStream(arq.getInputstream());
		    File file = new File(diretorio+"/"+nomeArquivo);
		    
		    FileOutputStream fout = new FileOutputStream(file);
		    while(in.available() != 0)
		    {
		    	fout.write(in.read());
		    }
		    fout.close();
		    
		    ColecaoImagem ci = new ColecaoImagem();
		    
		    ci.setExtensao(extensao);
		    ci.setImagem(nomeArquivo);
		    ci.setTbColecao(colecaoDAO.find(id));
		    colecaoimagemDAO.save(ci);
		    ci = null;
		    
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
    }
    
    public StreamedContent getImagem() throws FileNotFoundException{  
    	  ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	  String nome = externalContext.getRequestParameterMap().get("nome");
    	  String extensao = externalContext.getRequestParameterMap().get("extensao");
    	  if(FacesContext.getCurrentInstance().getRenderResponse() || nome == null)
              return new DefaultStreamedContent();
    	  else
    		  return new DefaultStreamedContent(new FileInputStream(new File(diretorio+"/"+nome)), "image/"+extensao);
    }

}
