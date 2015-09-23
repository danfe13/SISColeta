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

import br.ufs.coleta.SISColeta.entities.Especie;

/**
 *
 * @author danilo
 */
@Stateless
public class EspecieDAO extends GenericDAO<Especie, Integer> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public EspecieDAO() {
    	super(Especie.class);
    }
    
    public List<Especie> getEspecies(String nome) {
        
    	TypedQuery<Especie> query = em.createNamedQuery("Especie.findByNome", Especie.class);
    	query.setParameter("nome", "%"+nome.toUpperCase()+"%");
    	try{
    		List<Especie> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Object[]> getMaisColetadas(){
    	try{
    		return em.createNamedQuery("Especie.findMaisColetada").setMaxResults(5).getResultList();
    	}catch(NoResultException e){
    		return null;
    	}	
    }
    
    public List<Object[]> getUltimasEspecies(){
    	try{
    		return em.createNativeQuery("SELECT jo.nome, m.nome as cidade, es.uf, ct.cod_coleta FROM tb_colecao c INNER JOIN ( SELECT e.nome_cientifico as nome, MAX(c.idtb_colecao) as id FROM tb_colecao c INNER JOIN tb_especie e ON e.idtb_especie = c.tb_especie_id GROUP BY e.nome_cientifico ORDER BY id DESC) jo ON jo.id = c.idtb_colecao INNER JOIN tb_coleta ct ON ct.idtb_coleta = c.tb_coleta_id INNER JOIN tb_municipio m ON m.idtb_municipio = ct.tb_municipio_id INNER JOIN tb_estado es ON es.idtb_estado = m.tb_estado_id").setMaxResults(5).getResultList();
    	}catch(NoResultException e){
    		return null;
    	}	
    }
}
