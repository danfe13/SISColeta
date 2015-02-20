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

import br.ufs.coleta.SISColeta.entities.Familia;
import br.ufs.coleta.SISColeta.entities.Ordem;

/**
 *
 * @author danilo
 */
@Stateless
public class FamiliaDAO extends GenericDAO<Familia, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public FamiliaDAO() {
    	super(Familia.class);
    }
    
    public List<Familia> getbyOrdem(Integer id) {
    	TypedQuery<Familia> query = em.createNamedQuery("Ordem.findByOrdem", Familia.class);
    	query.setParameter("id", id);	
    	List<Familia> results = query.getResultList();
    	return results;
	}
    
}
