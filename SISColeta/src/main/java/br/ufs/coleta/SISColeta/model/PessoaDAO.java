/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.coleta.SISColeta.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufs.coleta.SISColeta.entities.TbPessoa;

/**
 *
 * @author danilo
 */
@Stateless
public class PessoaDAO extends GenericDAO<TbPessoa, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaDAO() {
    	super(TbPessoa.class);
    }
    
}
