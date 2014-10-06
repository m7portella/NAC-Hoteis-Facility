package br.com.fiap.dao;

import br.com.fiap.entity.Usuario;


public interface UsuarioDAO 
					extends DAO<Usuario,Integer>{

	Usuario buscar(String email, String senha);
	
	boolean existeEmail(String email);
	
}
