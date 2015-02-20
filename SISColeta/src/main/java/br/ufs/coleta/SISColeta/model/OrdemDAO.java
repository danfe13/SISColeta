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

import br.ufs.coleta.SISColeta.entities.Ordem;

/**
 *
 * @author danilo
 */
@Stateless
public class OrdemDAO extends GenericDAO<Ordem, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public OrdemDAO() {
    	super(Ordem.class);
    }
    
    public List<Ordem> getbyClasse(Integer id) {
    	TypedQuery<Ordem> query = em.createNamedQuery("Ordem.findByClasse", Ordem.class);
    	query.setParameter("id", id);	
    	List<Ordem> results = query.getResultList();
    	return results;
	}
    
}
