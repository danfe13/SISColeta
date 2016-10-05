package br.ufs.coleta.SISColeta.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import br.ufs.coleta.SISColeta.entities.CaracRio;
import br.ufs.coleta.SISColeta.model.CaracRioDAO;

@Path("/caracrio")
public class CaracRioService {
	
	@EJB
    private CaracRioDAO caracrioDAO;
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<CaracRio> getAllCaracRio()
    {
		return caracrioDAO.findAll();
    }
}
