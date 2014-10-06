package br.com.fiap.login.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.entity.Usuario;
import br.com.fiap.ldap.LDAPManager;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3740995259955824639L;
	private String email;
	private String senha;
	
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)
				context.getExternalContext().getSession(false);

		session.invalidate();

		return "/xhtml/login/login";
	}
	
	public String logar(){
		
		
		//Buscar o usuário no LDAP
		LDAPManager ldap = new LDAPManager();
		Usuario user = ldap.buscar(email, senha);
	
		if (user != null){
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)
					context.getExternalContext().getSession(true);
			
			session.setAttribute("usuario", user);
			
			return "/xhtml/index";
		}else{
			//Adiciona uma mensagem de erro
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário ou senha inválido", "Usuáio ou Senha inválido"));
			return "/xhtml/login/login";
		}
		
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}