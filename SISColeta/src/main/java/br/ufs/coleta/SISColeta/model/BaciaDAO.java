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

import br.ufs.coleta.SISColeta.entities.Bacia;
import br.ufs.coleta.SISColeta.entities.Coleta;

/**
 *
 * @author danilo
 */
@Stateless
public class BaciaDAO extends GenericDAO<Bacia, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public BaciaDAO() {
    	super(Bacia.class);
    }
    
    public Bacia getById(Integer id) {
    	
    	TypedQuery<Bacia> query = em.createNamedQuery("Bacia.findById", Bacia.class);
    	query.setParameter("id", id);
    	try{
    		Bacia results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
}
