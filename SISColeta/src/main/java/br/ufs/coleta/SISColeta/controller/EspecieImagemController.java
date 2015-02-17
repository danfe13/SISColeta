package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.EspecieImagem;
import br.ufs.coleta.SISColeta.model.EspecieImagemDAO;
import br.ufs.coleta.SISColeta.util.HashGenerator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
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
	private static final String diretorio = "imagens_especies/";
	@EJB
	private EspecieImagemDAO especieimagemDAO;
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
	    	
		    //Cria um arquivo UploadFile, para receber o arquivo do evento
		    UploadedFile arq = event.getFile();
		    String nomeArquivo = arq.getFileName();
		    String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());
		    nomeArquivo = HashGenerator.gerar(arq.getFileName()+String.valueOf(getDAO().count()));
		    
		    FacesContext context = FacesContext.getCurrentInstance();
		   /* ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		    String diretorio = servletContext.getRealPath("/resources/");
		    diretorio = diretorio+"/especie/"+nomeArquivo; */
		    //Essa parte comentada deve ser usada caso queira salvar
		    //o arquivo em um local do servidor.
		    
		    InputStream in = new BufferedInputStream(arq.getInputstream());
		    File file = new File(diretorio+nomeArquivo);
		    //O método file.getAbsolutePath() fornece o caminho do arquivo criado
		    //Pode ser usado para ligar algum objeto do banco ao arquivo enviado
		    String caminho = file.getAbsolutePath();
		    FileOutputStream fout = new FileOutputStream(file);
		    while(in.available() != 0)
		    {
		    	fout.write(in.read());
		    }
		    fout.close();
		   /* especieimagem.setTbEspecie(getItems().get(0).getTbEspecie());
		    
		    especieimagemDAO.save(especieimagem);
		    */
		    FacesMessage msg = new FacesMessage("O Arquivo ", arq.getFileName() + " salvo em banco de dados.");
		    FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
    }
    
    public StreamedContent getFoto() throws FileNotFoundException {
        String fotoNome = "baeabee0771b6b990d1d9f4d2ca58eb6";
 
        if(FacesContext.getCurrentInstance().getRenderResponse() || fotoNome == null)
            return new DefaultStreamedContent();
 
        else
            return recuperarFotoDisco(fotoNome);
    }
    
    public static StreamedContent recuperarFotoDisco(String fotoNome) throws FileNotFoundException{
    	FacesContext context = FacesContext.getCurrentInstance();
	 /*   ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
	    String diretorio = servletContext.getRealPath("/resources/");
	    diretorio = diretorio+"/especie/"+fotoNome; */
	    
        return new DefaultStreamedContent(new FileInputStream(new File(diretorio+fotoNome)), "image/jpeg");
    }

}
