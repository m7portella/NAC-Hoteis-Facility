package br.com.fiap.pedido.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.dao.impl.PedidoDAOImpl;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.StatusPedido;

@ManagedBean
@ViewScoped
public class PedidoBuscaBean {

	private List<Pedido> pedidos;
	private int quarto;
	private PedidoDAO pDAO;
	private int idPedidoFinalizar;
	
	@PostConstruct
	public void init(){
		setPedidos(new ArrayList<Pedido>());
		EntityManager em = 
				EntityManagerFactorySingleton
					.getInstance().createEntityManager();
		pDAO = new PedidoDAOImpl(em);
		idPedidoFinalizar = 0;
	}
	
	public void buscarPorQuarto(){
		setPedidos(pDAO.buscarPorQuarto(getQuarto()));
	}
	
	public void finalizar(){
		Pedido p = pDAO.searchByID(idPedidoFinalizar);
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
		return idPedidoFinalizar;
	}

	public void setIdPedidofinalizar(int idPedidofinalizar) {
		this.idPedidoFinalizar = idPedidofinalizar;
	}
	
}
