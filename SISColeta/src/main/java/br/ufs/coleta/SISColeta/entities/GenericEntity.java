package br.ufs.coleta.SISColeta.entities;

import java.io.Serializable;

public interface GenericEntity extends Serializable {
	/**
	 * @return A referência para a chave primária (Primary Key) de cada objeto persistido.
	 * 		   Caso o objeto ainda não tenha sido persistido, deve retornar <code>null</code>.
	 */
	public Number getId();
}
