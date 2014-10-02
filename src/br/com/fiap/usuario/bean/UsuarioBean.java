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

import ldap.LDAPManager;
import dao.EntityManagerFactorySingleton;
import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import entity.Usuario;


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
	
	public void validarEmail(FacesContext context, 
					UIComponent component, Object value)
			throws ValidatorException{
		
		String email = value.toString();
		//Procura pelo email no banco de dados
		boolean existe = usuarioDao.existeEmail(email);
		if (existe){
			throw 
				new ValidatorException(
					new FacesMessage("Email já cadastrado"));
		}
		
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





