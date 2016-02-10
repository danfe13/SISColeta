package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.ColecaoImagem;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Etiqueta;
import br.ufs.coleta.SISColeta.entities.Invoice;
import br.ufs.coleta.SISColeta.entities.Mar;
import br.ufs.coleta.SISColeta.entities.Rio;
import br.ufs.coleta.SISColeta.entities.Substrato;
import br.ufs.coleta.SISColeta.entities.Substratos;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.ColecaoImagemDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.MarDAO;
import br.ufs.coleta.SISColeta.model.RioDAO;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;


@ManagedBean(name = "coletaController")
@SessionScoped
public class ColetaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColetaDAO coletaDAO;
	@EJB
    private SubstratoDAO substratoDAO;
	@EJB
	private ColecaoDAO colecaoDAO;
	@EJB
	private ColecaoImagemDAO colecaoImagemDAO;
	@EJB
	private RioDAO rioDAO;
	@EJB
	private MarDAO marDAO;
    private List<Coleta> items = null;
    private Coleta coleta;
    private Integer local;
    private Integer tipo;
	private List<Substrato> substratos;
	private List<Integer> abundancia;
	private Usuario usuario;
	private Integer id;
	private List<Substratos> substratosedit;
	private JasperPrint jasperPrint;
	private List<Etiqueta> etiqueta;
	private int quantidadeEtiqueta;
	private Colecao colecao;

    public ColetaController() {
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta selected) {
        this.coleta = selected;
    }
    
    private SubstratoDAO getDAOSubstrato() {
        return substratoDAO;
    }
    
    public List<Substrato> getSubstratos() {
    	substratos = getDAOSubstrato().findAll();
        return substratos;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public void etiquetaFormato(Colecao colecao){
    	Etiqueta e = new Etiqueta();
		e.setCodColecao(colecao.getCodCampo());
		e.setEspecie(colecao.getTbEspecie().getNomeCientifico());
		e.setQuantidade(colecao.getQuantidade());
		e.setFamilia(colecao.getTbEspecie().getTbSubfamilia().getDescricao());
		
		String coletores = "";
		int i = 0;
		
		for(Usuario coletor: coleta.getTbColetors()){
			if(i+1 == coleta.getTbColetors().size() && i !=0){
				coletores += " & ";
			}
			else if(!coletores.isEmpty() && i != coleta.getTbColetors().size()){
				coletores += "; ";
			}
			coletores += coletor.getTbPessoa().getAbreviacao();
			i++;
		}
		
		e.setColetor(coletores);
		e.setDeterminador(colecao.getDeterminador().getTbPessoa().getAbreviacao());
		e.setCodColeta(this.coleta.getCodColeta());
		
		String localidade = coleta.getTbMunicipio().getNome()+", "+coleta.getTbMunicipio().getTbEstado().getNome()+ ", Brasil";
		
		if(coleta.getTbAquatico().getTbTipoAquaticoLocal().getId() == 1){
			Rio rio = rioDAO.find(coleta.getTbAquatico().getIdLocalAquatico());
			localidade = rio.getDescricao()+", "+rio.getTbBacia().getDescricao()+", "+localidade;
		}
		else{
			Mar mar = marDAO.find(coleta.getTbAquatico().getIdLocalAquatico());
			localidade = mar.getDescricao()+", "+mar.getTbOceano().getDescricao()+", "+localidade;
		}

		e.setLocalidade(localidade);
		
		String coordenada1 = coleta.getLatitudeGrau()+"º"+coleta.getLatitudeMinuto()+"'"+coleta.getLatitudeSegundo()+"\""+coleta.getDirecaoLatitude();
		String coordenada2 = coleta.getLongitudeGrau()+"º"+coleta.getLongitudeMinuto()+"'"+coleta.getLongitudeSegundo()+"\""+coleta.getDirecaoLongitude();
		e.setCoordenada(coordenada1+"/"+coordenada2);
		
		etiqueta.add(e);
    }
    
    public void gerarEtiquetaByColeta() throws JRException {  
    	etiqueta = new ArrayList<Etiqueta>();
    	coleta.setTbColetors(getDAO().getByColetor(coleta.getId()));
    	coleta.setTbAquatico(getDAO().getByAquatico(coleta.getId()));
    	List<Colecao> colecaos = colecaoDAO.getColecaoByColeta(coleta.getId());
    	
    	
    	for(Colecao colecao: colecaos){
    		this.etiquetaFormato(colecao);
    	}
    	
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(etiqueta);  
        String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorio/etiqueta/etiqueta.jasper");     
        jasperPrint=JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);  
    }  
    
    public void gerarEtiquetaByColecao() throws JRException {  
    	etiqueta = new ArrayList<Etiqueta>();
    	coleta.setTbColetors(getDAO().getByColetor(coleta.getId()));
    	coleta.setTbAquatico(getDAO().getByAquatico(coleta.getId()));
    	
    	for(int i=0; i<quantidadeEtiqueta; i++ ){
    		this.etiquetaFormato(colecao);
    	}
    	
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(etiqueta);  
        String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorio/etiqueta/etiqueta.jasper");     
        jasperPrint=JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);  
    } 
    
    public StreamedContent PDFEtiquetaColeta(Coleta coleta) throws JRException, IOException{  
    	this.coleta = coleta;
        gerarEtiquetaByColeta();  
        
        InputStream relatorio = null;   
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=etiqueta.pdf");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
        
        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        
        relatorio = new ByteArrayInputStream(Teste.toByteArray());
        
        return new DefaultStreamedContent(relatorio);
        
    } 
    
    public StreamedContent PDFEtiquetaColecao() throws JRException, IOException{  
        gerarEtiquetaByColecao();  
        
        InputStream relatorio = null;   
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=etiqueta.pdf");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
        
        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        
        relatorio = new ByteArrayInputStream(Teste.toByteArray());
        
        return new DefaultStreamedContent(relatorio);
        
    } 

    private ColetaDAO getDAO() {
        return coletaDAO;
    }

    public Coleta prepareCreate() {
        coleta = new Coleta();
        getSubstratos();
        abundancia = new ArrayList<Integer>();
        for (Substrato substrato: substratos){
        	abundancia.add(0);
        }
        
        initializeEmbeddableKey();
        return coleta;
    }
    
    public Coleta prepareEdit() {
        coleta = getDAO().getById(id);
        coleta.setTbColetors(getDAO().getByColetor(id));
        coleta.setTbCaracRios2(getDAO().getByCaracRio(id));
        coleta.setTbMetodoColetas(getDAO().getByMetodo(id));
        coleta.setTbSubstratos(getDAO().getBySubstratos(id));
        coleta.setTbAquatico(getDAO().getByAquatico(id));
        
        this.setSubstratosedit(new ArrayList<Substratos>(coleta.getTbSubstratos()));
    	
        initializeEmbeddableKey();
        return coleta;
    }
    
    public void update(){
    	getDAO().save(coleta);
    	coleta.setTbSubstratos(this.substratosedit);
    	for (Substratos substrato: substratosedit){
    		getDAO().updateSubstratos(substrato);
        }
    	getDAO().updateLocal(coleta.getTbAquatico());
    }
    
    public void cadastrar(){
    	
    	try {
    		coleta.setUsuario(usuario);
        	Coleta novacoleta = getDAO().save(coleta);
        	int i = 0;
        	for (Substrato substrato: substratos){
        		getDAO().insertSubstratos(novacoleta.getId().intValue(), substrato.getId().intValue(), abundancia.get(i++).intValue());
            }
        	getDAO().insertLocal(novacoleta.getId().intValue(), local, tipo);
        	
        	
        	FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	items = null;
    }
    
    public void remover(){
	    try{	
    		List<Colecao> colecaos = colecaoDAO.getColecaoByColeta(coleta.getId());
	    	for(Colecao colecao: colecaos){
	    		for(ColecaoImagem imagem: colecaoImagemDAO.findByColecaos(colecao.getId()))
	    		colecaoImagemDAO.remove(imagem);
	    	}
	    	getDAO().deleteColecao(this.coleta.getId());
	    	getDAO().deleteColetores(this.coleta.getId());
	    	getDAO().deleteSubstratos(this.coleta.getId());
	    	getDAO().deleteCarac(this.coleta.getId());
	    	getDAO().deleteMetodoColetas(this.coleta.getId());
	    	getDAO().deleteAquatico(this.coleta.getId());
	    	getDAO().remove(this.coleta);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	coleta = null;
    }

    public List<Coleta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }
    
    public List<Coleta> getItemsAlunos(Usuario usuario) {
        if (items == null) {
    		items = getDAO().getColetaAluno(usuario.getId());
        } 
        return items;
    }

    public List<Coleta> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Coleta> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

	public List<Integer> getAbundancia() {
		return abundancia;
	}

	public void setAbundancia(List<Integer> abundancia) {
		this.abundancia = abundancia;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
  	
	public void insertUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Substratos> getSubstratosedit() {
		return substratosedit;
	}

	public void setSubstratosedit(List<Substratos> substratosedit) {
		this.substratosedit = substratosedit;
	}
  	
	public Object getParticipacao(Usuario usuario){
		Object obj = colecaoDAO.participacaoColetaCount(usuario.getId());
		return obj;
	}
	
	public Object getDeterminador(Usuario usuario){
		Object obj = colecaoDAO.determinadorColetaCount(usuario.getId());
		return obj;
	}
	
	public Boolean validarAlunoColeta(int usuario, int coleta){
		return coletaDAO.alunoColeta(usuario, coleta);
	}

	public int getQuantidadeEtiqueta() {
		return quantidadeEtiqueta;
	}

	public void setQuantidadeEtiqueta(int quantidadeEtiqueta) {
		this.quantidadeEtiqueta = quantidadeEtiqueta;
	}

	public Colecao getColecao() {
		return colecao;
	}

	public void setColecao(Colecao colecao) {
		this.colecao = colecao;
	}
	
	

}
