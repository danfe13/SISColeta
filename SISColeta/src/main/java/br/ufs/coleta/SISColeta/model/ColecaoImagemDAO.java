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

import br.ufs.coleta.SISColeta.entities.ColecaoImagem;
import br.ufs.coleta.SISColeta.entities.EspecieImagem;

/**
 *
 * @author danilo
 */
@Stateless
public class ColecaoImagemDAO extends GenericDAO<ColecaoImagem, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public ColecaoImagemDAO() {
    	super(ColecaoImagem.class);
    }
    
    public List<ColecaoImagem> findByColecaos(Integer id){
    	TypedQuery<ColecaoImagem> query = em.createNamedQuery("ColecaoImagem.findByColecao", ColecaoImagem.class);
    	query.setParameter("id", id);
    	List<ColecaoImagem> results = query.getResultList();
    	return results;
    }
    
}
