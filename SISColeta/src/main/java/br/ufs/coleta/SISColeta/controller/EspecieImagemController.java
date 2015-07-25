package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.EspecieImagem;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.EspecieImagemDAO;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "especieimagemController")
@SessionScoped
public class EspecieImagemController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String diretorio = "imagens_especies";
	@EJB
	private EspecieImagemDAO especieimagemDAO;
	@EJB
	private EspecieDAO especieDAO;
    private List<EspecieImagem> items = null;
    private EspecieImagem especieimagem;
    private Integer idEspecie;

	public EspecieImagemController() {
    }
	
	public Integer getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

    public EspecieImagem getEspecieImagem() {
        return especieimagem;
    }

    public void setEspecieImagem(EspecieImagem selected) {
        this.especieimagem = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecieImagemDAO getDAO() {
        return especieimagemDAO;
    }

    public EspecieImagem prepareCreate() {
        especieimagem = new EspecieImagem();
        initializeEmbeddableKey();
        return especieimagem;
    }
    
    public void cadastrar(){
    	getDAO().save(especieimagem);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.especieimagem);
    	items = null;
    	especieimagem = null;
    }
    
    public List<EspecieImagem> getItems(){
    	return items;
    }

    public void checkList() {
        if (idEspecie == null) {
    		return;
        } 
        items = getDAO().findByEspecies(idEspecie);
    }

    public List<EspecieImagem> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<EspecieImagem> getItemsAvailableSelectOne() {
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
		    
		    EspecieImagem ei = new EspecieImagem();
		    
		    ei.setExtensao(extensao);
		    ei.setImagem(nomeArquivo);
		    ei.setTbEspecie(especieDAO.find(idEspecie));
		    especieimagemDAO.save(ei);
		    ei = null;
		    
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
