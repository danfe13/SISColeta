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

import br.ufs.coleta.SISColeta.entities.Especie;
import br.ufs.coleta.SISColeta.entities.EspecieImagem;

/**
 *
 * @author danilo
 */
@Stateless
public class EspecieImagemDAO extends GenericDAO<EspecieImagem, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public EspecieImagemDAO() {
    	super(EspecieImagem.class);
    }
    
    public List<EspecieImagem> findByEspecies(Integer id){
    	TypedQuery<EspecieImagem> query = em.createNamedQuery("EspecieImagem.findByEspecie", EspecieImagem.class);
    	query.setParameter("id", id);
    	List<EspecieImagem> results = query.getResultList();
    	return results;
    }
    
}
