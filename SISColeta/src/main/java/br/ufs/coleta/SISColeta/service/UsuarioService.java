package br.ufs.coleta.SISColeta.service;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import br.ufs.coleta.SISColeta.model.UsuarioDAO;

@Path("/auth")
public class UsuarioService {
	
	@EJB
    private UsuarioDAO usuarioDAO;
	
	@PermitAll
    @GET
    @Path("/{telefone}")
    public Response auth(@PathParam("telefone") String telefone, @Context Request req)
    {
		Response.ResponseBuilder rb = Response.ok(usuarioDAO.getUsuarioFone(telefone));
		return rb.build();
    }
	
}
