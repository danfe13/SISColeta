package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Substrato;
import br.ufs.coleta.SISColeta.entities.Substratos;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


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
    private List<Coleta> items = null;
    private Coleta coleta;
    private Integer local;
    private Integer tipo;
	private List<Substrato> substratos;
	private List<Integer> abundancia;
	private Usuario usuario;
	private Integer id;
	private List<Substratos> substratosedit;
	//private JasperPrint jasperPrint;

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
        if (substratos == null) {
    		substratos = getDAOSubstrato().findAll();
        } 
        return substratos;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
 /*   public void init() throws JRException{  
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(items);  
        String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/etiqueta.jasper");        
        jasperPrint=JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);  
    }  
    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException{  
        init();  
        HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
       httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");  
        ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
        FacesContext.getCurrentInstance().responseComplete();  
    } */

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
        coleta.setTbCaracRios(getDAO().getByCaracRio(id));
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
    	coleta.setUsuario(usuario);
    	Coleta novacoleta = getDAO().save(coleta);
    	int i = 0;
    	for (Substrato substrato: substratos){
    		getDAO().insertSubstratos(novacoleta.getId().intValue(), substrato.getId().intValue(), abundancia.get(i++).intValue());
        }
    	getDAO().insertLocal(novacoleta.getId().intValue(), local, tipo);
    	items = null;
    }
    
    public void remover(){
    	getDAO().remove(this.coleta);
    	items = null;
    	coleta = null;
    }

    public List<Coleta> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
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
  	
	
	

}
