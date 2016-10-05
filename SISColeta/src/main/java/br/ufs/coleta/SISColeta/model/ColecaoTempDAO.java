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
import br.ufs.coleta.SISColeta.entities.ColecaoTemp;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.entities.Instituicao;

/**
 *
 * @author danilo
 */
@Stateless
public class ColecaoTempDAO extends GenericDAO<ColecaoTemp, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public ColecaoTempDAO() {
    	super(ColecaoTemp.class);
    }
    
    public List<ColecaoTemp> getColecaoByColeta(Integer id) {
        
    	TypedQuery<ColecaoTemp> query = em.createNamedQuery("ColecaoTemp.findByColeta", ColecaoTemp.class);
    	query.setParameter("idcoleta", id);
    	try{
    		List<ColecaoTemp> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
}
