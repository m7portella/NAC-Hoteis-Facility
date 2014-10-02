package br.com.fiap.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import ldap.LDAPManager;
import dao.EntityManagerFactorySingleton;
import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import entity.Usuario;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{

	private String email;
	private String senha;
	
	private UsuarioDAO dao;
	
	@PostConstruct
	private void init(){
		EntityManager em = 
				EntityManagerFactorySingleton
					.getInstance().createEntityManager();
		dao = new UsuarioDAOImpl(em);
	}
	
	public String deslogar(){
		//Recuperar a sess�o do usu�rio
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)
				context.getExternalContext().getSession(false);
		//Invalidar a sess�o
		session.invalidate();
		//Ir para a p�gina de login
		return "/xhtml/login/login";
	}
	
	public String logar(){
		//Busca o usu�rio no banco relacional
		//Usuario user = dao.buscar(email, senha);
		
		//Buscar o usu�rio no LDAP
		LDAPManager ldap = new LDAPManager();
		Usuario user = ldap.buscar(email, senha);
		
		if (user != null){
			//Logou!
			//Coloca informa��o na sess�o do usu�rio
			FacesContext context = FacesContext.getCurrentInstance();
			//Recupera a sess�o do usu�rio... true -> cria uma sess�o se n�o existe
			HttpSession session = (HttpSession)
					context.getExternalContext().getSession(true);
			//Adiciona informa��o na sess�o -> (chave, valor)
//			session.setAttribute("usuario", user.getEmail());
//			session.setAttribute("categoria", user.getCategoria());
			session.setAttribute("usuario", user);

			
			return "/xhtml/index";
		}else{
			//Adiciona uma mensagem de erro
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Usu�rio ou senha inv�lidos"));
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



