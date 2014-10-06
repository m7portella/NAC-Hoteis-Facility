package br.com.fiap.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.StatusPedido;

public class PedidoDAOImpl extends DAOImpl<Pedido, Integer> implements PedidoDAO {

	public PedidoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Pedido> buscarPorQuarto(int quarto) {
		TypedQuery<Pedido> query = em.createQuery("from Pedido p where p.numeroQuarto = :p1",Pedido.class);
		query.setParameter("p1", quarto);
		return query.getResultList();	}

	@Override
	public void finalizarPedido(int id) {
		Pedido p = searchByID(id);
		em.getTransaction().begin();
		p.setStatus(StatusPedido.FINALIZADO);
		em.getTransaction().commit();
	}
	
	@Override
	public void insert(Pedido entity) {
		entity.setDataCadastro(Calendar.getInstance());
		super.insert(entity);
	}

}
