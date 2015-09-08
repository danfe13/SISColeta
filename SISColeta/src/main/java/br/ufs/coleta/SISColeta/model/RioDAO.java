/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.coleta.SISColeta.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.coleta.SISColeta.entities.Rio;

/**
 *
 * @author danilo
 */
@Stateless
public class RioDAO extends GenericDAO<Rio, Integer> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public RioDAO() {
    	super(Rio.class);
    }
    
    public List<Rio> getRioByBacia(Integer id) {
    	TypedQuery<Rio> query = em.createNamedQuery("Rio.findByBacia", Rio.class);
    	query.setParameter("id", id);	
    	List<Rio> results = query.getResultList();
    	return results;
	}
    
}
