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
			//Criptografa a senha antes de buscar no banco
			senha = CriptografiaUtils.criptografar(senha);
			query.setParameter("p2", senha);
			return query.getSingleResult();
		}catch(Exception e){
			//N�o encontrou ninguem ou mais de uma ocorr�ncia			
			return null;
		}
	}

	@Override
	public boolean existeEmail(String email) {
		TypedQuery<Usuario> query =
			em.createQuery("from Usuario u where u.email = :p1"
					,Usuario.class);
		query.setParameter("p1", email);
		try{
			query.getSingleResult();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	//Sobrescrever o m�todo de inserir padr�o
	@Override
	public void insert(Usuario entity) {
		//Recupera a senha informada pelo usu�rio
		String senha = entity.getSenha();
		try {
			String senhaCript = CriptografiaUtils.criptografar(senha);
			//Setar a senha criptografada no usu�rio
			entity.setSenha(senhaCript);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.insert(entity);
	}

	
}


