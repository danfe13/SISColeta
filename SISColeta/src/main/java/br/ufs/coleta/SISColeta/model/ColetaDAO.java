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
import br.ufs.coleta.SISColeta.entities.CaracRio;
import br.ufs.coleta.SISColeta.entities.Coleta;
import br.ufs.coleta.SISColeta.entities.Mar;
import br.ufs.coleta.SISColeta.entities.MetodoColeta;
import br.ufs.coleta.SISColeta.entities.Pessoa;
import br.ufs.coleta.SISColeta.entities.Rio;
import br.ufs.coleta.SISColeta.entities.Substratos;
import br.ufs.coleta.SISColeta.entities.TipoAquaticoLocal;
import br.ufs.coleta.SISColeta.entities.Usuario;

/**
 *
 * @author danilo
 */
@Stateless
public class ColetaDAO extends GenericDAO<Coleta, Long> {
	@PersistenceContext
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
        return em;
    }

    public ColetaDAO() {
    	super(Coleta.class);
    }
    
    public Coleta getById(Integer id) {
    	
    	TypedQuery<Coleta> query = em.createNamedQuery("Coleta.findById", Coleta.class);
    	query.setParameter("id", id);
    	try{
    		Coleta results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public TipoAquaticoLocal getByTipoAquatico(Integer id) {
    	
    	TypedQuery<TipoAquaticoLocal> query = em.createNamedQuery("Coleta.tipoaquatico", TipoAquaticoLocal.class);
    	query.setParameter("id", id);
    	try{
    		TipoAquaticoLocal results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public Aquatico getByAquatico(Integer id) {
    	
    	TypedQuery<Aquatico> query = em.createNamedQuery("Coleta.aquatico", Aquatico.class);
    	query.setParameter("id", id);
    	try{
    		Aquatico results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public Rio getByAquaticoRio(Integer id) {
    	
    	TypedQuery<Rio> query = em.createNamedQuery("Coleta.aquaticorio", Rio.class);
    	query.setParameter("id", id);
    	try{
    		Rio results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public Mar getByAquaticoMar(Integer id) {
    	
    	TypedQuery<Mar> query = em.createNamedQuery("Coleta.aquaticomar", Mar.class);
    	query.setParameter("id", id);
    	try{
    		Mar results = query.getSingleResult();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Usuario> getByColetor(Integer id) {
    	
    	TypedQuery<Usuario> query = em.createNamedQuery("Coleta.usuario", Usuario.class);
    	query.setParameter("id", id);
    	try{
    		List<Usuario> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}

    public List<MetodoColeta> getByMetodo(Integer id) {
    	
    	TypedQuery<MetodoColeta> query = em.createNamedQuery("Coleta.metodo", MetodoColeta.class);
    	query.setParameter("id", id);
    	try{
    		List<MetodoColeta> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<Substratos> getBySubstratos(Integer id) {
    	
    	TypedQuery<Substratos> query = em.createNamedQuery("Coleta.substrato", Substratos.class);
    	query.setParameter("id", id);
    	try{
    		List<Substratos> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public List<CaracRio> getByCaracRio(Integer id) {
    	
    	TypedQuery<CaracRio> query = em.createNamedQuery("Coleta.carac", CaracRio.class);
    	query.setParameter("id", id);
    	try{
    		List<CaracRio> results = query.getResultList();
    		return results;
    	}catch(NoResultException e){
    		return null;
    	}	
	}
    
    public void insertSubstratos(int id_coleta, int id_substrato, int abundante){
    	Query query = em.createNativeQuery("INSERT INTO tb_substratos (tb_substrato_id, tb_coleta_id, abundancia) " +
                " VALUES(?,?,?)");
            query.setParameter(1, id_substrato);
            query.setParameter(2, id_coleta);
            query.setParameter(3, abundante);
            query.executeUpdate();
    }
    
    public void updateSubstratos(Substratos substrato){
    	Query query = em.createNativeQuery("UPDATE tb_substratos SET abundancia=? WHERE tb_substrato_id=? AND tb_coleta_id=?");
            query.setParameter(1, substrato.getAbundancia());
            query.setParameter(2, substrato.getTbSubstrato().getId());
            query.setParameter(3, substrato.getColeta().getId());
            query.executeUpdate();
    }
    
    public void insertLocal(int id_coleta, int id_localAquatico, int tipo){
    	Query query = em.createNativeQuery("INSERT INTO tb_aquatico (idtb_coleta, idlocal_aquatico, tb_tipo_aquatico_local_id) " +
                " VALUES(?,?,?)");
            query.setParameter(1, id_coleta);
            query.setParameter(2, id_localAquatico);
            query.setParameter(3, tipo);
            query.executeUpdate();
    }
    
    public void updateLocal(Aquatico aquatico){
    	Query query = em.createNativeQuery("UPDATE tb_aquatico SET idlocal_aquatico=?, tb_tipo_aquatico_local_id =? WHERE idtb_coleta=?");
            query.setParameter(1, aquatico.getIdLocalAquatico());
            query.setParameter(2, aquatico.getTbTipoAquaticoLocal().getId());
            query.setParameter(3, aquatico.getTbColeta().getId());
            query.executeUpdate();
    }
    
    public void insertColetor(int id_coleta, int id_usuario, boolean determinador){
    	Query query = em.createNativeQuery("INSERT INTO tb_coletor (tb_coleta_id, tb_usuario_id, determinador) " +
                " VALUES(?,?,?)");
            query.setParameter(1, id_coleta);
            query.setParameter(2, id_usuario);
            query.setParameter(3, determinador);
            query.executeUpdate();
    }
}
