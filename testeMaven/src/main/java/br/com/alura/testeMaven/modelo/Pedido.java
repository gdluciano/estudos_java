package br.com.alura.testeMaven.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	
	//Quanto há relacionamento @...ToOne o hibernate fará a consulta puxando a entidade relacionada, carregado automaticamente,
	// mesmo se não estiver consultando-a
		//Fetch altera a forma de busca (@...ToMany = default é LAZY (join apenas quando solicitado dados) // @...ToOne = default é EAGER (join sempre)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	//Relacionamento bidirecional
	//O lado com @...Many deve fazer o mappedBy para não gerar nova tabela (nome do atributo do outro relacionado);
		//Cascade funciona para propagar operações da entidade para a relacionada.
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Pedido() {
	}
	
	//adiciona o pedido na lista e na entidade ItemPedido
	public void adicionaItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValorTotal());
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	
}
