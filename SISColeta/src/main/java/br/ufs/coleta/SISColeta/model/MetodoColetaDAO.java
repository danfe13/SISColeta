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

import br.ufs.coleta.SISColeta.entities.MetodoColeta;

/**
 *
 * @author danilo
 */
@Stateless
public class MetodoColetaDAO extends GenericDAO<MetodoColeta, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public MetodoColetaDAO() {
    	super(MetodoColeta.class);
    }
    
    public List<MetodoColeta> getMetodosApartirDe(int id) {
    	TypedQuery<MetodoColeta> query = em.createNamedQuery("MetodoColeta.findApartirDe", MetodoColeta.class);
    	query.setParameter("id", id);
    	try{
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	}	
	}
}
