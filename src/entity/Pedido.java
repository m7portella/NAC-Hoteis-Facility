package entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seqPedido", sequenceName="SEQ_PEDIDO", allocationSize=1)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPedido")
	private int id;
	
	private int numeroQuarto;
	
	private String descricao;
	
	private Calendar dataCadastro;
	
	private double valor;
	
	private StatusPedido status;
	
	public Pedido(int numeroQuarto, String descricao,
			Calendar dataCadastro, double valor, StatusPedido status) {
		super();
		this.numeroQuarto = numeroQuarto;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.valor = valor;
		this.status = status;
	}

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(int numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
}
