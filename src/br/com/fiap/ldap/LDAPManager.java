package br.com.fiap.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import br.com.fiap.entity.Usuario;
import br.com.fiap.utils.CriptografiaUtils;

public class LDAPManager {

	private Hashtable<String, String> ambiente;
	
	public LDAPManager(){
		ambiente = new Hashtable<String, String>();
		ambiente.put(Context.INITIAL_CONTEXT_FACTORY, 
				"com.sun.jndi.ldap.LdapCtxFactory");
		//Configuração do host do LDAP
		ambiente.put(Context.PROVIDER_URL, 
				//"ldap://10.20.32.56:389");
				"ldap://localhost:389");
		ambiente.put(Context.SECURITY_AUTHENTICATION,
				"simple");		
	}
	
	public Usuario buscar(String login, String senha){
		try {
			ambiente.put(Context.SECURITY_PRINCIPAL, 
					"uid="+login+",ou=sp,dc=fiapldap,dc=com");
			ambiente.put(Context.SECURITY_CREDENTIALS, 
					CriptografiaUtils.criptografar(senha));
			
			DirContext ctx = new InitialDirContext(ambiente);
			//Logou!!
			Usuario user = new Usuario();
			//Busca informações do usuário
			Attributes atributos = 
					ctx.getAttributes("uid="+login+",ou=sp,dc=fiapldap,dc=com");
			user.setEmail(atributos.get("mail").get().toString());
			user.setCategoria(atributos.get("businessCategory").get().toString());
			
			return user;
		} catch (Exception e) {
			//Usuário ou senha inválidos...
			return null;
		}
		
	}
	
	public void cadastrar(Usuario user){
	
		ambiente.put(Context.SECURITY_PRINCIPAL, 
				"cn=root, dc=fiapldap,dc=com");
		ambiente.put(Context.SECURITY_CREDENTIALS, 
				"password");
		
		try {
			
			DirContext ctx = new InitialDirContext(ambiente);
			//Logou!!
			
			
			Attributes attributes = new BasicAttributes();
			
			Attribute attribute = new BasicAttribute("objectClass");
			
			attribute.add("inetOrgPerson");
			attributes.put(attribute);
			
			attributes.put("cn",user.getEmail());
			attributes.put("sn",user.getEmail());
			attributes.put("mail", user.getEmail());
			attributes.put("userPassword",CriptografiaUtils.criptografar(user.getSenha()));
			
			//Recupera a string inicial (até o @)
			String uid = user.getEmail().substring(0,user.getEmail().indexOf("@"));
			
			ctx.createSubcontext("uid="+uid+",ou=sp,dc=fiapldap,dc=com", attributes);
			//user.setEmail(attributes.get("mail").get().toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
