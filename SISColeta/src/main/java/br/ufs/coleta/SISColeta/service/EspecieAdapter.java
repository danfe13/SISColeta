package br.ufs.coleta.SISColeta.service;

import br.ufs.coleta.SISColeta.entities.Especie;

public class EspecieAdapter{
	
	private static final long serialVersionUID = 1L;
	Integer id;
	String cientifico;
	String popular;
	String subfamilia;
	String familia;
	String ordem;
	String classe;
	
	public EspecieAdapter(Especie especie){
		this.id = especie.getId();
		this.cientifico = especie.getNomeCientifico();
		this.popular = especie.getNomePopular();
		this.subfamilia = especie.getTbSubfamilia().getDescricao();
		this.familia = especie.getTbSubfamilia().getTbFamilia().getDescricao();
		this.ordem = especie.getTbSubfamilia().getTbFamilia().getTbOrdem().getDescricao();
		this.classe = especie.getTbSubfamilia().getTbFamilia().getTbOrdem().getTbClasse().getDescricao();
	}
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCientifico() {
		return cientifico;
	}



	public void setCientifico(String cientifico) {
		this.cientifico = cientifico;
	}



	public String getPopular() {
		return popular;
	}



	public void setPopular(String popular) {
		this.popular = popular;
	}



	public String getSubfamilia() {
		return subfamilia;
	}



	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
	}



	public String getFamilia() {
		return familia;
	}



	public void setFamilia(String familia) {
		this.familia = familia;
	}



	public String getOrdem() {
		return ordem;
	}



	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}



	public String getClasse() {
		return classe;
	}



	public void setClasse(String classe) {
		this.classe = classe;
	}



	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecieAdapter)) {
            return false;
        }
        EspecieAdapter other = (EspecieAdapter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}