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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.ufs.coleta.SISColeta.entities.Aquatico;
import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Retirada;
import br.ufs.coleta.SISColeta.entities.RetiradaColecao;
import br.ufs.coleta.SISColeta.entities.Substratos;

/**
 *
 * @author danilo
 */
@Stateless
public class RetiradaDAO extends GenericDAO<Retirada, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public RetiradaDAO() {
    	super(Retirada.class);
    }
    
    public List<RetiradaColecao> getRetiradaColecaoByRetirada(Integer id) {
        
    	TypedQuery<RetiradaColecao> query = em.createNamedQuery("Retirada.findByColecao", RetiradaColecao.class);
    	query.setParameter("id", id);
    	try{
    		List<RetiradaColecao> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public void insertRetirada(int id_colecao, int id_retirada, String obs){
    	Query query = em.createNativeQuery("INSERT INTO tb_retirada_colecao (tb_retirada_id, tb_colecao_id, observacao) " +
                " VALUES(?,?,?)");
            query.setParameter(1, id_retirada);
            query.setParameter(2, id_colecao);
            query.setParameter(3, obs);
            query.executeUpdate();
    }
    
    public void updateRetirada(int id_colecao, int id_retirada, String obs){
    	Query query = em.createNativeQuery("UPDATE tb_retirada_colecao SET observacao=? WHERE tb_retirada_id=? AND tb_coleta_id=?");
            query.setParameter(1, obs);
            query.setParameter(2, id_colecao);
            query.setParameter(3, id_retirada);
            query.executeUpdate();
    }
    
    public void deleteRetiradaColecao(int id_colecao, int id_retirada){
    	Query query = em.createNativeQuery("DELETE FROM tb_retirada_colecao WHERE tb_colecao_id=? AND tb_retirada_id=?");
            query.setParameter(1, id_colecao);
            query.setParameter(2, id_retirada);
            query.executeUpdate();
    }
    
}
