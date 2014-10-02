package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import utils.CriptografiaUtils;
import dao.UsuarioDAO;
import entity.Usuario;

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
			//Não encontrou ninguem ou mais de uma ocorrência			
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

	//Sobrescrever o método de inserir padrão
	@Override
	public void insert(Usuario entity) {
		//Recupera a senha informada pelo usuário
		String senha = entity.getSenha();
		try {
			String senhaCript = CriptografiaUtils.criptografar(senha);
			//Setar a senha criptografada no usuário
			entity.setSenha(senhaCript);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.insert(entity);
	}

	
}


