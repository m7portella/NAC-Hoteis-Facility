package br.com.fiap.usuario.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.entity.Usuario;
import br.com.fiap.ldap.LDAPManager;


@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2117479333240067380L;

	private Usuario usuario;
	
	private String repetirSenha;
	
	@PostConstruct
	private void init(){
		
		usuario = new Usuario();
		
	}
	
	public String cadastrar(){
		//Validar se as senhas são iguais
		if (usuario.getSenha().equals(repetirSenha)){
			
			LDAPManager ldap = new LDAPManager();
			ldap.cadastrar(usuario);
			
			//usuarioDao.insert(usuario);
			//Adiciona uma mensagem de sucesso
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Usuário Cadastrado!"));
			//Manda para a página de login
			return "/xhtml/login/login";
		}else{
			//Mensagem de erro
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Senhas diferentes!"));
			return null;
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}
	
}