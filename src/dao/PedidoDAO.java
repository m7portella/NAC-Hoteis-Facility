package dao;

import java.util.List;

import entity.Pedido;

public interface PedidoDAO extends DAO<Pedido,Integer>{

	List<Pedido> buscarPorQuarto(int quarto);
	
	void finalizarPedido(int id);
	
}
