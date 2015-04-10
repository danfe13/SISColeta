/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.coleta.SISColeta.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.coleta.SISColeta.entities.Pessoa;
import br.ufs.coleta.SISColeta.entities.Usuario;

/**
 *
 * @author danilo
 */
@Stateless
public class UsuarioDAO extends GenericDAO<Usuario, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDAO() {
    	super(Usuario.class);
    }
    
    public Usuario getExistente(Usuario usuario, Pessoa pessoa) {
    	if(usuario.getId() == null){
    		usuario.setId(0);
    	}
    	TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findExistente", Usuario.class);
    	query.setParameter("idUsuario", usuario.getId());
    	query.setParameter("login", usuario.getLogin());
    	query.setParameter("cpf", pessoa.getCpf());
    	query.setParameter("email", pessoa.getEmail());
    	try{
    		Usuario results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
}
