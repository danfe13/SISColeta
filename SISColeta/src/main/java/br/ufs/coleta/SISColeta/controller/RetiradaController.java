package br.ufs.coleta.SISColeta.controller;

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.Invoice;
import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.entities.Retirada;
import br.ufs.coleta.SISColeta.entities.RetiradaColecao;
import br.ufs.coleta.SISColeta.entities.Rio;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.RetiradaDAO;
import br.ufs.coleta.SISColeta.model.RioDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "retiradaController")
@SessionScoped
public class RetiradaController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private RetiradaDAO retiradaDAO;
	@EJB
	private ColecaoDAO colecaoDAO;
	@EJB
    private ColetaDAO coletaDAO;
	@EJB
	private RioDAO rioDAO;
    private List<Retirada> items = null;
    private Retirada retirada;
    private RetiradaColecao retiradaColecao;
    private Colecao colecao;
    private String obs;
    private Integer qntd;
    private Usuario usuario;
    private JasperPrint jasperPrint;

    public RetiradaController() {
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public void setRetirada(Retirada selected) {
        this.retirada = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RetiradaDAO getDAO() {
        return retiradaDAO;
    }

    public Retirada prepareCreate() {
        retirada = new Retirada();
        initializeEmbeddableKey();
        return retirada;
    }
    
    public void cadastrar(){
    	retirada.setTbUsuario(usuario);
    	getDAO().save(retirada);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('RetiradaCreateDialog').hide();");
    	items = null;
    }
    
    public void update(){
    	retirada.setTbUsuario(usuario);
    	getDAO().save(retirada);
    	RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('RetiradaEditDialog').hide();");
    	items = null;
    }
    
    public void cadastrarRetirar(){
    	try{
    		getDAO().insertRetirada(colecao.getId(), retirada.getId(), obs, qntd);
    		RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('RetiradaColecaoCreateDialog').hide();");
    	}catch(Exception e){
    		if(colecao == null){
    			this.adicionarMensagemErro("Informe uma espécie!");
    		}
    		if(qntd == null){
    			this.adicionarMensagemErro("Informe a Quantidade!");
    		}
    		else if(qntd == (int)qntd){
    			this.adicionarMensagemErro("A Quantidade deve ser um número inteiro!");
    		}
    		else if(qntd > colecao.getQuantidade()){
    			this.adicionarMensagemErro("Quantidade superior ao disponível!");
    		}
    	}
    	
    }
    
    public void remover(){
    	try{	
    		getDAO().deleteRetiradaColecao(retirada.getId());
    		getDAO().remove(this.retirada);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	retirada = null;
    }
    
    public void removerRetiradaColecao(){
    	getDAO().deleteRetiradaColecao(retiradaColecao.getTbColecao().getId(), retirada.getId());
    	retiradaColecao = null;
    }

    public List<Retirada> getItems() {
        if (items == null) {
    		items = getDAO().findAll();
        } 
        return items;
    }

    public List<Retirada> getItemsAvailableSelectMany() {
        return getDAO().findAll();
    }

    public List<Retirada> getItemsAvailableSelectOne() {
        return getDAO().findAll();
    }

	public RetiradaColecao getRetiradaColecao() {
		return retiradaColecao;
	}

	public void setRetiradaColecao(RetiradaColecao retiradaColecao) {
		this.retiradaColecao = retiradaColecao;
	}
	
	public List<Colecao> autoCompleteEspecies(String nome){
		return colecaoDAO.getColecaoByEspecie(nome);
	}
	
	public void onSelectItem(){
		this.colecao.setTbColeta(colecaoDAO.getColetabyColecao(this.colecao.getId()));
	}

	public Colecao getColecao() {
		return colecao;
	}

	public void setColecao(Colecao colecao) {
		this.colecao = colecao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Integer getQntd() {
		return qntd;
	}

	public void setQntd(Integer qntd) {
		this.qntd = qntd;
	}

	public List<RetiradaColecao> getRetiradaColecaoByRetirada(Retirada retirada){
		this.retirada = retirada;
		return getDAO().getRetiradaColecaoByRetirada(retirada.getId());
	}
	
	public void insertUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void init(Retirada retirada) throws JRException {  
    	
    	List<Invoice> li =new ArrayList<Invoice>();
    	
    	List<RetiradaColecao> rc = this.getRetiradaColecaoByRetirada(retirada);
    	
    	for(RetiradaColecao retiradacolecao: rc){
    		Invoice i = new Invoice();
    		i.setCep(retirada.getTbDestinatario().getTbInstituicao().getCep());
    		i.setCentro(retirada.getTbDestinatario().getCentro());
    		i.setDepartamento(retirada.getTbDestinatario().getDepartamento()); 
    		i.setDestinatario(retirada.getTbDestinatario().getNome());
    		i.setUniversidade(retirada.getTbDestinatario().getTbInstituicao().getNome());
    		
    		String data = new SimpleDateFormat("yyyy-MM-dd").format(retirada.getDataRetirada());
    		i.setDataEmprestimo(data);
    		
    		i.setCodEmprestimo(retirada.getId().toString());
    		i.setEstado(retirada.getTbDestinatario().getTbInstituicao().getTbMunicipio().getNome()+"-"+retirada.getTbDestinatario().getTbInstituicao().getTbMunicipio().getTbEstado().getUf());
    		
    		i.setRemetente(retirada.getTbUsuario().getTbPessoa().getNome());
    		i.setNomeResponsavel(retirada.getTbUsuario().getTbPessoa().getNome());
    		i.setPacote(retirada.getNumeroPacote().toString());
    		i.setMetodo(retirada.getModoEnvio());
    		if(retirada.getTipoRetirada() == 1)
    			i.setTipoEnvio("Emprestimo");
    		else
    			i.setTipoEnvio("Doação");
    		
    		i.setEspecie(retiradacolecao.getTbColecao().getTbEspecie().getNomeCientifico());
     		i.setCodColeta(retiradacolecao.getTbColecao().getCodCampo());
    		i.setQuantidade("("+retiradacolecao.getQuantdExemplares().toString()+" exemplares)");

    		Coleta coleta = colecaoDAO.getColetabyColecao(retiradacolecao.getTbColecao().getId());
    		coleta.setTbColetors(coletaDAO.getByColetor(coleta.getId()));
    		coleta.setTbAquatico(coletaDAO.getByAquatico(coleta.getId()));
    		coleta.setTbMetodoColetas(coletaDAO.getByMetodo(retiradacolecao.getTbColecao().getId()));
    		
    		String coletores = "";
    		int indice = 0;
    		
    		for(Usuario coletor: coleta.getTbColetors()){
    			if(indice+1 == coleta.getTbColetors().size() && indice !=0){
    				coletores += " & ";
    			}
    			else if(!coletores.isEmpty() && indice != coleta.getTbColetors().size()){
    				coletores += "; ";
    			}
    			coletores += coletor.getTbPessoa().getAbreviacao();
    			indice++;
    		}
    		
    		String localidade = coleta.getTbMunicipio().getNome()+", "+coleta.getTbMunicipio().getTbEstado().getNome();
    		
    		if(coleta.getTbAquatico().getTbTipoAquaticoLocal().getId() == 1){
    			Rio rio = rioDAO.find(coleta.getTbAquatico().getIdLocalAquatico());
    			localidade = rio.getDescricao()+". "+rio.getTbBacia().getDescricao()+". "+localidade;
    		}
    		
    		String datacoleta = new SimpleDateFormat("dd-MM-yyyy").format(coleta.getDataInicio());
    		
    		String coordenada1 = coleta.getLatitudeGrau()+"º"+coleta.getLatitudeMinuto()+"'"+coleta.getLatitudeSegundo()+"\""+coleta.getDirecaoLatitude();
    		String coordenada2 = coleta.getLongitudeGrau()+"º"+coleta.getLongitudeMinuto()+"'"+coleta.getLongitudeSegundo()+"\""+coleta.getDirecaoLongitude();
    	
    		String metodo = "";
    		indice = 0;
    		
    		coleta.setTbMetodoColetas(coletaDAO.getByMetodo(coleta.getId()));
    		
    		for(MetodoColeta metodocoleta: coleta.getTbMetodoColetas()){
    			if(indice+1 == coleta.getTbMetodoColetas().size() && indice !=0){
    				metodo += " & ";
    			}
    			else if(indice+1 != coleta.getTbMetodoColetas().size() && indice != 0){
    				metodo += ", ";
    			}
    			metodo += metodocoleta.getDescricao();
    			indice++;
    		}
    		if(metodo != ""){
    			metodo = "Coletados com "+metodo+". ";
    		}
    		
    		String descricao = localidade+". "+datacoleta+". "+metodo+"Coletores: "+coletores+". "+coordenada1+" "+coordenada2;
    		
    		i.setDescricao(descricao);
    		
    		li.add(i);
    	}
    	
        JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(li);  
        String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorio/invoice/invoice3.jasper");     
        jasperPrint=JasperFillManager.fillReport(reportPath, new HashMap(),beanCollectionDataSource);  
    }  
    
    public StreamedContent PDF(Retirada retirada) throws JRException, IOException{  
        init(retirada);  
        
        InputStream relatorio = null;   
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=invoice.pdf");  
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
        
        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        
        relatorio = new ByteArrayInputStream(Teste.toByteArray());
        
        return new DefaultStreamedContent(relatorio);
        
    }
	
}
