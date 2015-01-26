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

import br.ufs.coleta.SISColeta.entities.Municipio;
/**
 *
 * @author danilo
 */
@Stateless
public class MunicipioDAO extends GenericDAO<Municipio, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioDAO() {
    	super(Municipio.class);
    }
    
    public List<Municipio> getMunicipioByEstado(Integer id) {
    	TypedQuery<Municipio> query = em.createNamedQuery("Municipio.findByIdEstado", Municipio.class);
    	query.setParameter("id", id);	
    	List<Municipio> results = query.getResultList();
    	return results;
	}
    
}
