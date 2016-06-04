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

import br.ufs.coleta.SISColeta.entities.Colecao;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Especie;

/**
 *
 * @author danilo
 */
@Stateless
public class ColecaoDAO extends GenericDAO<Colecao, Integer> {
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
    
    public Object especieCount(){
    	Query query = em.createNativeQuery("SELECT COUNT(DISTINCT tb_especie_id) FROM tb_colecao");
    	return query.getResultList().get(0);
    }
    
    public Object participacaoColetaCount(Integer id){
    	Query query = em.createNativeQuery("SELECT COUNT(*) FROM tb_coleta ct INNER JOIN tb_coletor co ON co.tb_coleta_id = ct.idtb_coleta WHERE co.tb_usuario_id = :id");
    	query.setParameter("id", id);
    	return query.getResultList().get(0);
    }
    
    public Object determinadorColetaCount(Integer id){
    	Query query = em.createNativeQuery("SELECT COUNT(*) FROM tb_colecao as c WHERE c.id_determinador = :id");
    	query.setParameter("id", id);
    	return query.getResultList().get(0);
    }
    
    public Object lastCOD(){
    	Query query = em.createNativeQuery("SELECT c.cod_campo FROM tb_colecao c order by idtb_colecao desc");
    	if(!query.getResultList().isEmpty())
    		return query.getResultList().get(0);
    	else
    		return "CIUFS1";
    }
    
    public Coleta getColetabyColecao(Integer id){
    	TypedQuery<Coleta> query = em.createNamedQuery("Colecao.getColeta", Coleta.class);
    	query.setParameter("id", id);
    	try{
    		return query.getSingleResult();
    	}catch(NoResultException e){
    		return null;
    	}	
    }
}
