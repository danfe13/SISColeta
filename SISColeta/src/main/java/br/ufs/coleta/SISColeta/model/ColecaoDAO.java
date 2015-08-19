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

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Especie;

/**
 *
 * @author danilo
 */
@Stateless
public class ColecaoDAO extends GenericDAO<Colecao, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public ColecaoDAO() {
    	super(Colecao.class);
    }
    
    public List<Colecao> getColecaoByColeta(Integer id) {
        
    	TypedQuery<Colecao> query = em.createNamedQuery("Colecao.findByColeta", Colecao.class);
    	query.setParameter("idcoleta", id);
    	try{
    		List<Colecao> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Colecao> getColecaoByEspecie(String nome) {
        
    	TypedQuery<Colecao> query = em.createNamedQuery("Colecao.findByEspecie", Colecao.class);
    	query.setParameter("nome", "%"+nome.toUpperCase()+"%");
    	try{
    		List<Colecao> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
}
