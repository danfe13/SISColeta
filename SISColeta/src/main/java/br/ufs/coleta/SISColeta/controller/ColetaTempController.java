package br.ufs.coleta.SISColeta.controller;

import java.util.List;

import br.ufs.coleta.SISColeta.entities.CaracRio;
import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.ColecaoTemp;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.entities.Substrato;
import br.ufs.coleta.SISColeta.model.CaracRioDAO;
import br.ufs.coleta.SISColeta.model.ColecaoDAO;
import br.ufs.coleta.SISColeta.model.ColecaoTempDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.ColetaTempDAO;
import br.ufs.coleta.SISColeta.model.DestinoDAO;
import br.ufs.coleta.SISColeta.model.MetodoColetaDAO;
import br.ufs.coleta.SISColeta.model.SubstratoDAO;
import br.ufs.coleta.SISColeta.model.UnidadeDAO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "coletaTempController")
@ViewScoped
public class ColetaTempController extends GenericController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
    private ColecaoTempDAO colecaoTempDAO;
	@EJB
    private ColecaoDAO colecaoDAO;
	@EJB
    private ColetaTempDAO coletaTempDAO;
	@EJB
    private ColetaDAO coletaDAO;
	@EJB
    private MetodoColetaDAO metodoDAO;
	@EJB
    private CaracRioDAO caracRioDAO;
	@EJB
    private SubstratoDAO substratoDAO;
	@EJB
    private UnidadeDAO unidadeDAO;
	@EJB
    private DestinoDAO destinoDAO;
	private List<ColetaTemp> items = null;
	private ColetaTemp coleta;

    public ColetaTempController() {
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public ColetaTemp getColeta() {
        return coleta;
    }

    public void setColeta(ColetaTemp selected) {
        this.coleta = selected;
    }
    
    public List<ColetaTemp> getItems() {
        if (items == null) {
    		items = coletaTempDAO.findAll();
        } 
        return items;
    }
    
    public void remover(){
	    try{	
	    	coletaTempDAO.deleteColecao(this.coleta.getId());
	    	coletaTempDAO.remove(this.coleta);
	    }
		catch(Exception sqlex){
			this.adicionarMensagemAlerta("O item está em uso e não pode ser excluido!");
		}
    	items = null;
    	coleta = null;
    }
    
    public void carregarColetas(){
    	for(ColetaTemp coletaTemp :items){
    		adapterTemp(coletaTemp);
    	}
    }
    
    public void adapterTemp(ColetaTemp temp){
    	Coleta coleta = new Coleta();
    	coleta.setAltitude(temp.getAltitude());
    	coleta.setClima(temp.getClima());
    	coleta.setCodColeta(temp.getCodColeta());
    	coleta.setCondutividade(temp.getCondutividade());
    	coleta.setDataFim(temp.getDataFim());
    	coleta.setDataInicio(temp.getDataInicio());
    	coleta.setDatum("SAD-69");
    	coleta.setDirecaoLatitude(temp.getDirecaoLatitude());
    	coleta.setDirecaoLongitude(temp.getDirecaoLongitude());
    	coleta.setLargura(temp.getLargura());
    	coleta.setLatitudeGrau(temp.getLatitudeGrau());
    	coleta.setLatitudeMinuto(temp.getLatitudeMinuto());
    	coleta.setLatitudeSegundo(temp.getLatitudeSegundo());
    	
    	coleta.setLongitudeGrau(temp.getLongitudeGrau());
    	coleta.setLongitudeMinuto(temp.getLongitudeMinuto());
    	coleta.setLongitudeSegundo(temp.getLatitudeSegundo());
    	coleta.setMataCiliarMd(temp.getMataCiliarMd());
    	coleta.setMataCiliarMe(temp.getMataCiliarMe());
    	coleta.setNome(temp.getNome());
    	coleta.setObservacao(temp.getObservacao());
    	coleta.setOxiDissolvido(temp.getOxiDissolvido());
    	coleta.setPhAgua(temp.getPhAgua());
    	coleta.setReferencia(temp.getReferencia());
    	coleta.setSalinidade(temp.getSalinidade());
    	coleta.setVegetacaoRiparianaMd(temp.getVegetacaoRiparianaMd());
    	coleta.setVegetacaoRiparianaMe(temp.getVegetacaoRiparianaMe());
    	coleta.setVelAgua(temp.getVelAgua());
    	coleta.setTAgua(temp.getTAgua());
    	coleta.setTAr(temp.getTAr());
    	coleta.setTransparencia(temp.getTransparencia());
    	coleta.setUsuario(temp.getUsuario());
    	coleta.setTbMunicipio(temp.getTbMunicipio());
    	
    	List<CaracRio> caracrios = caracRioDAO.findAll();
    	List<MetodoColeta> metodocoletas = metodoDAO.findAll();
    	List<Substrato> substratos = substratoDAO.findAll();
    	
    	String[] carac = temp.getTbCaracRios().split(",");
    	String[] metodo = temp.getTbMetodoColetas().split(",");
    	String[] subs = temp.getTbSubstratos().split(",");
    	
    	for(int i=0; i<caracrios.size();i++){
    		boolean teste = false;
    		for(int y=0;y<carac.length; y++){
    			if(caracrios.get(i).getDescricao().equalsIgnoreCase(carac[y])){
    				teste = true;
    			}
    		}
    		if(!teste)
    			caracrios.remove(i);
    	}
    	
    	for(int i=0; i<metodocoletas.size();i++){
    		boolean teste = false;
    		for(int y=0;y<metodo.length; y++){
    			if(metodocoletas.get(i).getDescricao().equalsIgnoreCase(metodo[y])){
    				teste = true;
    			}
    		}
    		if(!teste)
    			metodocoletas.remove(i);
    	}
    	
    	for(int i=0; i<substratos.size();i++){
    		boolean teste = false;
    		for(int y=0;y<subs.length; y++){
    			if(substratos.get(i).getDescricao().equalsIgnoreCase(subs[y])){
    				teste = true;
    			}
    		}
    		if(!teste)
    			substratos.remove(i);
    	}
    	
    	coleta.setTbCaracRios2(caracrios);
    	coleta.setTbMetodoColetas(metodocoletas);
    	
    	Coleta novacoleta = coletaDAO.save(coleta);
    	
		
    	for (Substrato substrato: substratos){
    		coletaDAO.insertSubstratos(novacoleta.getId().intValue(), substrato.getId().intValue(), 0);
        }
    	coletaDAO.insertLocal(novacoleta.getId().intValue(), temp.getRio().getId(), 1);
    	
    	List<ColecaoTemp> colecoestemps = colecaoTempDAO.getColecaoByColeta(temp.getId());
    	
    	for(ColecaoTemp colecaotemp: colecoestemps){
    		Colecao colecao = new Colecao();
    		colecao.setCodCampo("CIUFS"+colecaoDAO.lastCOD());
    		colecao.setDestino(destinoDAO.findFirst());
        	colecao.setUnidade(unidadeDAO.findFirst());
        	colecao.setTbUsuario(colecaotemp.getTbUsuario());
        	colecao.setQuantidade(colecaotemp.getQuantidade());
        	colecao.setTbEspecie(colecaotemp.getTbEspecie());
        	colecao.setTbColeta(novacoleta);
        	colecaoDAO.save(colecao);
    	}
    	coletaTempDAO.deleteColecao(temp.getId());
    	coletaTempDAO.remove(temp);
    }

}
