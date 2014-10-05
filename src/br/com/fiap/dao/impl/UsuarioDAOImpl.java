package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;
import br.com.fiap.utils.CriptografiaUtils;

public class UsuarioDAOImpl 
	extends DAOImpl<Usuario, Integer> 
				implements UsuarioDAO {

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Usuario buscar(String email, String senha) {
		try{
			TypedQuery<Usuario> query = 
					em.createQuery("from Usuario u where u.email = :p1 and u.senha = :p2",Usuario.class);
			query.setParameter("p1", email);
			query.setParameter("p2", senha);
			return query.getSingleResult();
		}catch(Exception e){
			//Não encontrou ninguem ou mais de uma ocorrência			
			return null;
		}
	}

	
}


