package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Pedido;

public interface PedidoDAO extends DAO<Pedido,Integer>{

	List<Pedido> buscarPorQuarto(int quarto);
	
	void finalizarPedido(int id);
	
}
