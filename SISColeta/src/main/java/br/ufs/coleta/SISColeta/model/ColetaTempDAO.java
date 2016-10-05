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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.ColetaTemp;
import br.ufs.coleta.SISColeta.entities.Instituicao;

/**
 *
 * @author danilo
 */
@Stateless
public class ColetaTempDAO extends GenericDAO<ColetaTemp, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public ColetaTempDAO() {
    	super(ColetaTemp.class);
    }
    
    public ColetaTemp getByCod(String cod) {
    	
    	TypedQuery<ColetaTemp> query = em.createNamedQuery("ColetaTemp.findByCod", ColetaTemp.class);
    	query.setParameter("cod", cod);
    	try{
    		ColetaTemp results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public void deleteColecao(int id_coleta){
    	Query query = em.createNativeQuery("DELETE FROM temp_tb_colecao WHERE temp_tb_coleta_id=?");
            query.setParameter(1, id_coleta);
            query.executeUpdate();
    }
    
}
