package br.com.fiap.main;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.PedidoDAOImpl;
import br.com.fiap.dao.impl.UsuarioDAOImpl;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.StatusPedido;
import br.com.fiap.entity.Usuario;

public class Teste {

	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		PedidoDAO dao = new PedidoDAOImpl(em);
		
		//Cadastro de Pedido
		Pedido pedido = new Pedido(10,"Batata Frita",Calendar.getInstance(),20.0, StatusPedido.ABERTO);
		
		dao.insert(pedido);
		
		//Cadastra Usuario
		Usuario usuario = new Usuario("amanda", "123");
		
		UsuarioDAO uDAO = new UsuarioDAOImpl(em);
		uDAO.insert(usuario);
	}
}
