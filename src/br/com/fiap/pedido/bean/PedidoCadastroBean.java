package br.com.fiap.pedido.bean;


import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.dao.EntityManagerFactorySingleton;
import br.com.fiap.dao.PedidoDAO;
import br.com.fiap.dao.impl.PedidoDAOImpl;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.StatusPedido;

@ManagedBean
@RequestScoped
public class PedidoCadastroBean {

	private Pedido pedido;
	private PedidoDAO pDAO;
	
	@PostConstruct
	public void init(){
		setPedido(new Pedido());
		pedido.setDataCadastro(Calendar.getInstance());
		EntityManager em = 
				EntityManagerFactorySingleton
					.getInstance().createEntityManager();
		pDAO = new PedidoDAOImpl(em);
		
	}
	
	public void cadastrar(){
		pedido.setStatus(StatusPedido.ABERTO);
		pDAO.insert(getPedido());
		String msg = "Pedido cadastrado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
