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

import br.ufs.coleta.SISColeta.entities.Perfil;
import br.ufs.coleta.SISColeta.entities.Recebimento;
import br.ufs.coleta.SISColeta.entities.RetiradaColecao;

/**
 *
 * @author danilo
 */
@Stateless
public class RecebimentoDAO extends GenericDAO<Recebimento, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public RecebimentoDAO() {
    	super(Recebimento.class);
    }
    
    public List<Recebimento> getRecebimentoByRetirada(Integer idretirada, Integer idcolecao) {
        
    	TypedQuery<Recebimento> query = em.createNamedQuery("Recebimento.findByRetirada", Recebimento.class);
    	query.setParameter("idretirada", idretirada);
    	query.setParameter("idcolecao", idcolecao);
    	try{
    		List<Recebimento> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}

}
