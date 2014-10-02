package br.com.fiap.pedido.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import dao.EntityManagerFactorySingleton;
import dao.PedidoDAO;
import dao.impl.PedidoDAOImpl;
import entity.Pedido;
import entity.StatusPedido;

@ManagedBean
@RequestScoped
public class PedidoBuscaBean {

	private List<Pedido> pedidos;
	private int quarto;
	private PedidoDAO pDAO;
	private int idPedidofinalizar;
	
	@PostConstruct
	public void init(){
		setPedidos(new ArrayList<Pedido>());
		EntityManager em = 
				EntityManagerFactorySingleton
					.getInstance().createEntityManager();
		pDAO = new PedidoDAOImpl(em);

	}
	
	public void buscarPorQuarto(){
		setPedidos(pDAO.buscarPorQuarto(getQuarto()));
	}
	
	public void finalizar(){
		Pedido p = pDAO.searchByID(idPedidofinalizar);
		p.setStatus(StatusPedido.FINALIZADO);
		pDAO.update(p);
		String msg = "Pedido finalizado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getQuarto() {
		return quarto;
	}

	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}

	public int getIdPedidofinalizar() {
		return idPedidofinalizar;
	}

	public void setIdPedidofinalizar(int idPedidofinalizar) {
		this.idPedidofinalizar = idPedidofinalizar;
	}
	
}
