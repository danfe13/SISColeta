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
import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.ColecaoTemp;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.entities.Usuario;
import br.ufs.coleta.SISColeta.model.ColecaoTempDAO;
import br.ufs.coleta.SISColeta.model.EspecieDAO;
import br.ufs.coleta.SISColeta.model.MunicipioDAO;
import br.ufs.coleta.SISColeta.model.ColetaDAO;
import br.ufs.coleta.SISColeta.model.ColetaTempDAO;
import br.ufs.coleta.SISColeta.model.RioDAO;
import br.ufs.coleta.SISColeta.model.UsuarioDAO;

@Path("/colecao")
public class ColecaoService {
	
	@EJB
    private ColecaoTempDAO colecaoTempDAO;
	@EJB
    private ColetaTempDAO coletaTempDAO;
	@EJB
    private EspecieDAO especieDAO;
	@EJB
    private UsuarioDAO usuarioDAO;
	
	@POST
	@Path("/receive")
	@Consumes("application/json")
    public Response setColecaoTemp(String json)
    {
		ColecaoTemp colecaoTemp = mappingColecao(json);
		colecaoTempDAO.save(colecaoTemp);
		return Response.status(201).entity("Enviado com Sucesso!").build();
    }
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<ColecaoTemp> getAll()
    {
		return colecaoTempDAO.findAll();
    }
	
	public ColecaoTemp mappingColecao(String json){
		try {
			ColecaoTemp ct = new ColecaoTemp();
			
			JSONObject jsonObject = new JSONObject(json);
			ct.setObservacao(jsonObject.getString("observacao"));
			ct.setQuantidade(jsonObject.getInt("quantidade"));
			ct.setTbEspecie(especieDAO.find(jsonObject.getInt("especie")));
			ct.setTbColeta(coletaTempDAO.getByCod(jsonObject.getString("coleta")));
			Usuario user = usuarioDAO.getUsuarioFone(jsonObject.getString("usuario"));
			ct.setTbUsuario(user);
			ct.setDeterminador(user);

			return ct;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
