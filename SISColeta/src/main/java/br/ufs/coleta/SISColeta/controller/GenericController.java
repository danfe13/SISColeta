package br.ufs.coleta.SISColeta.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public abstract class GenericController implements Serializable {
	
	
	private void adicionarMensagem(FacesMessage.Severity tipo, String titulo,
			String mensagem) {
		FacesContext.getCurrentInstance().
				addMessage(null, new FacesMessage(tipo, titulo, mensagem));
	}
	
	public void adicionarMensagemErroComponente(UIComponent component, String message){
		FacesContext.getCurrentInstance().addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message));
	}
	
	public void adicionarMensagemAviso(String titulo, String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_INFO, titulo, mensagem);
	}
	
	public void adicionarMensagemAlerta(String titulo, String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_WARN, titulo, mensagem);
	}
	
	public void adicionarMensagemErro(String titulo, String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
	}
	
	public void adicionarMensagemFatal(String titulo, String mensagem) {
		adicionarMensagem(FacesMessage.SEVERITY_FATAL, titulo, mensagem);
	}
	
	public void adicionarMensagemAviso(String mensagem) {
		adicionarMensagemAviso("", mensagem);
	}
	
	public void adicionarMensagemAlerta(String mensagem) {
		adicionarMensagemAlerta("", mensagem);
	}
	
	public void adicionarMensagemErro(String mensagem) {
		adicionarMensagemErro("", mensagem);
	}
	
	public void adicionarMensagemFatal(String mensagem) {
		adicionarMensagemFatal("", mensagem);
	}
}
