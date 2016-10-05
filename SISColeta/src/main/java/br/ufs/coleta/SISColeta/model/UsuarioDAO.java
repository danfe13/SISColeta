/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.coleta.SISColeta.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.coleta.SISColeta.entities.Coleta;
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
    
    public List<Usuario> getExistente(Usuario usuario, Pessoa pessoa) {
    	if(usuario.getId() == null){
    		usuario.setId(0);
    	}
    	TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findExistente", Usuario.class);
    	query.setParameter("idUsuario", usuario.getId());
    	query.setParameter("login", usuario.getLogin());
    	query.setParameter("cpf", pessoa.getCpf());
    	query.setParameter("email", pessoa.getEmail());
    	try{
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public Usuario getUsuarioFone(String telefone) {
    	TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findFone", Usuario.class);
    	query.setParameter("telefone", telefone);
    	try{
    		return query.getResultList().get(0);
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Usuario> getUsuarios(Coleta coleta) {
    
    	TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findColetores", Usuario.class);
    	query.setParameter("idcoleta", coleta.getId());
    	try{
    		List<Usuario> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Usuario> findUsuario() {
        
    	TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findUsuario", Usuario.class);
    	try{
    		List<Usuario> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
}
