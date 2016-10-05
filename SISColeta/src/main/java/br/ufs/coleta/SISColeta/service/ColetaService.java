package br.ufs.coleta.SISColeta.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.google.gson.JsonObject;

import br.ufs.coleta.SISColeta.entities.Bacia;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.model.MunicipioDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.ColetaTempDAO;
import br.ufs.coleta.SISColeta.model.RioDAO;
import br.ufs.coleta.SISColeta.model.UsuarioDAO;

@Path("/coleta")
public class ColetaService {
	
	@EJB
    private ColetaTempDAO coletaTempDAO;
	@EJB
    private MunicipioDAO municipioDAO;
	@EJB
    private UsuarioDAO usuarioDAO;
	@EJB
    private RioDAO rioDAO;
	
	@POST
	@Path("/receive")
	@Consumes("application/json")
    public Response setColetaTemp(String json)
    {
		ColetaTemp coletaTemp = mappingColeta(json);
		if(coletaTemp != null)
			coletaTempDAO.save(coletaTemp);
		return Response.status(201).entity("Enviado com Sucesso!").build();
    }
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<ColetaTemp> getAll()
    {
		return coletaTempDAO.findAll();
    }
	
	public ColetaTemp mappingColeta(String json){
		try {
			ColetaTemp ct = new ColetaTemp();
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			JSONObject jsonObject = new JSONObject(json);
			
			if(coletaTempDAO.getByCod(jsonObject.getString("codColeta"))!= null){
			
				JSONObject rio = new JSONObject(jsonObject.getString("rio"));
				JSONObject municipio = new JSONObject(jsonObject.getString("municipio"));
				
				ct.setTbMunicipio(municipioDAO.find(municipio.getInt("id")));
				ct.setRio(rioDAO.find(rio.getInt("id")));
				ct.setUsuario(usuarioDAO.getUsuarioFone(jsonObject.getString("usuario")));
				
				ct.setDataInicio(format.parse(jsonObject.getString("dataInicio")));
				ct.setDataFim(format.parse(jsonObject.getString("dataFim")));
				
				ct.setAltitude(jsonObject.getDouble("altitude"));
				ct.setCodColeta(jsonObject.getString("codColeta"));
				ct.setClima(jsonObject.getString("clima"));
				ct.setCondutividade(jsonObject.getDouble("condutividade"));
				ct.setLatitudeGrau(jsonObject.getDouble("latitudeGrau"));
				ct.setLatitudeMinuto(jsonObject.getDouble("latitudeMinuto"));
				ct.setLatitudeSegundo(jsonObject.getDouble("latitudeSegundo"));
				ct.setLongitudeGrau(jsonObject.getDouble("longitudeGrau"));
				ct.setLongitudeMinuto(jsonObject.getDouble("longitudeMinuto"));
				ct.setLongitudeSegundo(jsonObject.getDouble("longitudeSegundo"));
				ct.setDirecaoLatitude(jsonObject.getString("direcaoLatitude").charAt(0));
				ct.setDirecaoLongitude(jsonObject.getString("direcaoLongitude").charAt(0));
				ct.setTAr(jsonObject.getDouble("TAr"));
				ct.setTAgua(jsonObject.getDouble("TAgua"));
				ct.setVelAgua(jsonObject.getDouble("velAgua"));
				ct.setPhAgua(jsonObject.getDouble("phAgua"));
				ct.setNome(jsonObject.getString("nome"));
				ct.setReferencia(jsonObject.getString("referencia"));
				ct.setTransparencia(jsonObject.getString("transparencia"));
				ct.setSalinidade(jsonObject.getDouble("salinidade"));
				ct.setLargura(jsonObject.getDouble("largura"));
				ct.setOxiDissolvido(jsonObject.getDouble("oxiDissolvido"));
				ct.setMataCiliarMd(jsonObject.getInt("mataCiliarMd"));
				ct.setMataCiliarMe(jsonObject.getInt("mataCiliarMe"));
				ct.setVegetacaoRiparianaMd(jsonObject.getInt("vegetacaoRiparianaMd"));
				ct.setVegetacaoRiparianaMe(jsonObject.getInt("vegetacaoRiparianaMe"));
				ct.setObservacao(jsonObject.getString("observacao"));
				ct.setTbCaracRios(jsonObject.getString("tbCaracRios"));
				ct.setTbMetodoColetas(jsonObject.getString("tbSubstratos"));
				ct.setTbSubstratos(jsonObject.getString("tbMetodoColetas"));
				return ct;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
