package br.ufs.coleta.SISColeta.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufs.coleta.SISColeta.entities.Bacia;
import br.ufs.coleta.SISColeta.model.BaciaDAO;

@Path("/bacia")
public class BaciaService {
	
	@EJB
    private BaciaDAO baciaDAO;
	
	@GET
	@Path("/all/")
	@Produces("application/json")
    public List<Bacia> getAllBacia()
    {
		return baciaDAO.findAll();
    }
}
