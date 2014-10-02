package main;

import java.util.Calendar;

import javax.persistence.EntityManager;

import dao.EntityManagerFactorySingleton;
import dao.PedidoDAO;
import dao.impl.PedidoDAOImpl;
import entity.Pedido;
import entity.StatusPedido;

public class Teste {

	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		PedidoDAO dao = new PedidoDAOImpl(em);
		
		//Cadastro de Pedido
		Pedido pedido = new Pedido(10,"Batata Frita",Calendar.getInstance(),20.0, StatusPedido.ABERTO);
		
		dao.insert(pedido);
		
	}
}
