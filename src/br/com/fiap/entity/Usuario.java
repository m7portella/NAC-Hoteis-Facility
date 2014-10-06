package br.com.fiap.entity;

import java.io.Serializable;


public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2962544979307937384L;

	private String email;

	private String senha;
	
	private String categoria;

	public Usuario() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
