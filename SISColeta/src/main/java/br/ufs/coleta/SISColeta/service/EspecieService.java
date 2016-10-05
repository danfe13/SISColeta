package br.ufs.coleta.SISColeta.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.model.EspecieDAO;

@Path("/especie")
public class EspecieService {
	
	@EJB
    private EspecieDAO especieDAO;
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<EspecieAdapter> getAllEspecie()
    {
		List<Especie> especies = especieDAO.findAll();
		List<EspecieAdapter> eAdapter = new ArrayList<EspecieAdapter>();
		
		for(Especie especie: especies){
			eAdapter.add(new EspecieAdapter(especie));
		}
		return eAdapter;
    }
}
