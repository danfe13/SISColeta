package br.ufs.coleta.SISColeta.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.model.MetodoColetaDAO;

@Path("/metodo")
public class MetodoService {
	
	@EJB
    private MetodoColetaDAO metodoDAO;
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<MetodoColeta> getAllMetodo()
    {
		return metodoDAO.findAll();
    }
}
