package br.com.fiap.usuario.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.UsuarioDAOImpl;
import br.com.fiap.entity.Usuario;
import br.com.fiap.ldap.LDAPManager;


@ManagedBean
@RequestScoped
public class UsuarioBean implements Serializable{

	private Usuario usuario;
	
	private String repetirSenha;

	private UsuarioDAO usuarioDao;
	
	@PostConstruct
	private void init(){
		
		EntityManager em = 
				EntityManagerFactorySingleton
					.getInstance().createEntityManager();

		usuarioDao = new UsuarioDAOImpl(em);
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





