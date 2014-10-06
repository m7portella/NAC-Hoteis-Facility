package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seqUsuario", sequenceName = "SEQ_USUARIO", allocationSize = 1)
public class Usuario implements Serializable {

	public Usuario() {
		super();
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
	private int id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, length = 100)
	private String senha;
	
	private String categoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
